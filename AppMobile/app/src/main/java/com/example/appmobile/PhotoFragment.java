package com.example.appmobile;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmobile.ui.main.PageViewModel;
import com.example.appmobile.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class PhotoFragment extends Fragment {

    RequestQueue queue;
    ImageView viewPhoto;
    Button validerPhoto;
    int RESULT_LOAD_IMAGE;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflatedView = null;
        if(SaveSharedPreference.getUserName(getActivity().getBaseContext()).length() == 0) {
            inflatedView = inflater.inflate(R.layout.fragment_photo, container, false);
        }
        else {
            inflatedView = inflater.inflate(R.layout.fragment_photo2, container, false);


            validerPhoto = inflatedView.findViewById(R.id.btn_valider);
            viewPhoto = inflatedView.findViewById(R.id.imageView_photo);

            viewPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                2000);
                    } else {
                        try {
                            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(i, RESULT_LOAD_IMAGE);
                        } catch (Exception exp) {
                            Log.i("Error", exp.toString());
                        }
                    }
                }
            });

            validerPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
                    BitmapDrawable drawable = (BitmapDrawable) viewPhoto.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
                    byte[] image = stream.toByteArray();
                    String img_str = Base64.encodeToString(image, 0);
                    Log.d("IMAGE", "code : " + img_str);

                    String strToSend = "data," + img_str;

                    queue = Volley.newRequestQueue(getActivity().getBaseContext());
                    JSONObject jsonBody = null;


                    try {
                        jsonBody = new JSONObject();
                        jsonBody.put("image", strToSend);
                        jsonBody.put("pseudo", SaveSharedPreference.getUserName(getActivity()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String url = "http://" + getResources().getString(R.string.baseURL) + "/API/upload/send";

                    JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(JSONObject response) {
                            // display response
                            Log.d("Response", response.toString());

                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        }
                    },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d("Error.Response", error.toString());
                                }
                            }
                    );

                    getRequest.setRetryPolicy(new DefaultRetryPolicy(
                            25000,
                            0,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    MySingleton.getInstance(getActivity().getBaseContext()).addToRequestQueue(getRequest);
                }
            });
        }


        return inflatedView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super method removed
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri returnUri = data.getData();
            Bitmap bitmapImage = null;
            try {
                bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            viewPhoto.setImageBitmap(bitmapImage);
        }
    }
}





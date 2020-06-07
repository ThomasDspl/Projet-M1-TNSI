package com.example.appmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmobile.R;

import org.json.JSONException;
import org.json.JSONObject;

public class CompteFragment extends Fragment {

    private RequestQueue queue;
    Button inscrire;
    Button connexion;
    Button deconnexion;
    EditText editEmail;
    EditText editMdp;
    TextView helloUsername;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View inflatedView = null;
        if(SaveSharedPreference.getUserName(getActivity().getBaseContext()).length() == 0) {
            inflatedView = inflater.inflate(R.layout.fragment_compte, container, false);

            inscrire = inflatedView.findViewById(R.id.btn_inscrire);
            connexion  = inflatedView.findViewById(R.id.btn_connecter);

            editEmail  = inflatedView.findViewById(R.id.editmail_connexion);
            editMdp = inflatedView.findViewById(R.id.editmdp_connexion);




            inscrire.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), RegisterActivity.class);
                    startActivity(intent);
                }
            });

            connexion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String email = editEmail.getText().toString();
                    String password = editMdp.getText().toString();

                    queue = Volley.newRequestQueue(getActivity().getBaseContext());
                    JSONObject jsonBody = null;


                    try {
                        jsonBody = new JSONObject();
                        jsonBody.put("email", email);
                        jsonBody.put("password", password);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String url = "http://" + getResources().getString(R.string.baseURL) + "/API/users/logging";

                    JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(JSONObject response) {
                            // display response
                            Log.d("Response", response.toString());

                            try {
                                SaveSharedPreference.setUserName(getActivity(), response.getString("pseudo"));
                                Log.d("Response", "username : " + SaveSharedPreference.getUserName(getActivity()));
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
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
                            3000,
                            0,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


                    MySingleton.getInstance(getActivity().getBaseContext()).addToRequestQueue(getRequest);
                }
            });
        }

        else {

            inflatedView = inflater.inflate(R.layout.fragment_compte2, container, false);


            helloUsername = inflatedView.findViewById(R.id.text_helloUsername);
            deconnexion = inflatedView.findViewById(R.id.btn_deconnexion);

            helloUsername.setText("Bonjour " + SaveSharedPreference.getUserName(getActivity()));

            deconnexion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SaveSharedPreference.clearUserName(getActivity());
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            });


        }



        return inflatedView;
    }
}

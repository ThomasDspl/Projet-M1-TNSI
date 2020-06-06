package com.example.appmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom = ((EditText)findViewById(R.id.nom)).getText().toString();
                String prenom = ((EditText)findViewById(R.id.prenom)).getText().toString();
                String email = ((EditText)findViewById(R.id.email)).getText().toString();
                String passwd = ((EditText)findViewById(R.id.password)).getText().toString();
                final String pseudo = ((EditText)findViewById(R.id.pseudo)).getText().toString();

                queue = Volley.newRequestQueue(getBaseContext());
                JSONObject jsonBody = null;


                try {
                    jsonBody = new JSONObject();
                    jsonBody.put("pseudo", pseudo);
                    jsonBody.put("name", nom);
                    jsonBody.put("surname", prenom);
                    jsonBody.put("email", email);
                    jsonBody.put("password", passwd);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String url = "http://" + getResources().getString(R.string.baseURL) + "/API/users/registration";

                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response) {
                                // display response
                                Log.d("Response", response.toString());
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
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
                        0,
                        0,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


                MySingleton.getInstance(getBaseContext()).addToRequestQueue(getRequest);
            }
        });
    }
}

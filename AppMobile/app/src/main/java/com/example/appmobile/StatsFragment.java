package com.example.appmobile;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONException;
import org.json.JSONObject;

public class StatsFragment extends Fragment {

    RequestQueue queue;
    TextView statsText;
    TextView nombreImagesText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stats,container, false);
        statsText = rootView.findViewById(R.id.text_description3);
        nombreImagesText = rootView.findViewById(R.id.nombre_image);
        String url = "http://" + getResources().getString(R.string.baseURL) + "/API/users/stats";

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response) {
                // display response
                Log.d("Response", response.toString());
                int bouteilles = 0;
                int sacs = 0;
                int canettes = 0;
                int nbImages = 0;
                try {
                    nbImages = response.getInt("nb_image_analyser");
                    JSONObject statsObjets = response.getJSONObject("class");
                    bouteilles = statsObjets.getInt("nb_0");
                    sacs = statsObjets.getInt("nb_1");
                    canettes = statsObjets.getInt("nb_2");
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                statsText.setText(bouteilles + " bouteilles en plastique\n" +
                                  sacs + " sacs en plastique\n" +
                                  canettes + " canettes"
                );
                nombreImagesText.setText(nbImages + " images");

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
                10000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        MySingleton.getInstance(getActivity().getBaseContext()).addToRequestQueue(getRequest);


        return rootView;
    }
}

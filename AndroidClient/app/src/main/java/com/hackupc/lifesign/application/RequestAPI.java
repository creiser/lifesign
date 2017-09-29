package com.hackupc.lifesign.application;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hackupc.lifesign.activities.MainActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tvaisanen on 9/29/17.
 */

public class RequestAPI {

    private static final RequestAPI instance = new RequestAPI();
    private static final String TAG = "Lifesign.RequestAPI";


    //private constructor to avoid client applications to use constructor
    private RequestAPI() {
    }

    public static RequestAPI getInstance() {
        return instance;
    }

    private static StringRequest getPostRequest(String url) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.v(TAG, "Response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.v(TAG, "Response: " + error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "CHRIS");
                params.put("location", "BARCELONA");

                return params;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                HashMap<String, String> params2 = new HashMap<String, String>();
                params2.put("name", "Chris The Beardyman");
                params2.put("subject", "Still alive, no need to call the police.");
                return new JSONObject(params2).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        return postRequest;
    }

    public static void postStatus() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(MainActivity.appContext);
        String url = "http://10.0.2.2:5000/hello";

        StringRequest postRequest = getPostRequest(url);
        queue.add(postRequest);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.v(TAG, "Response: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v(TAG, "Response: ERROR");
                Log.e(TAG, error.toString());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}




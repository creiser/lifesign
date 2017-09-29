package com.hackupc.lifesign.application;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hackupc.lifesign.R;
import com.hackupc.lifesign.activities.MainActivity;
import com.hackupc.lifesign.intents.lifesignAlive;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tvaisanen on 9/18/17.
 */

public class PromptUserStatus extends BroadcastReceiver {

    private static final String TAG = "app.PromptUserStatus";
    private static final String NETWORKTAG = "app.NetworkResponse";
    private static final RequestAPI requestAPI = RequestAPI.getInstance();


    public void postStatus() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(MainActivity.appContext);
        String url = "http://10.0.2.2:5000/hello";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.v(NETWORKTAG, "Response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.v(NETWORKTAG, "Response: " + error);
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

    }

    ;
    queue.add(postRequest);

    // Request a string response from the provided URL.
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display the first 500 characters of the response string.
                    Log.v(NETWORKTAG, "Response: " + response);
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.v(NETWORKTAG, "Response: ERROR");
            Log.e(NETWORKTAG, error.toString());
        }
    });
    // Add the request to the RequestQueue.
    queue.add(stringRequest);
}


    @Override
    public void onReceive(Context context, Intent intent) {

        context = MainActivity.appContext;

        // Todo: separate AlertDialog and Notification builders to their own classes.

        if (Lifesign.isActivityVisible()) {
            Log.v(TAG, "Activity visible -> Use AlertDialog");
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);

            alertBuilder.setTitle("Hello Chris!");
            alertBuilder.setMessage("Are you dead yet?");

            alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    requestAPI.postStatus();
                }
            });

            alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    requestAPI.postStatus();
                }
            });

            AlertDialog alert = alertBuilder.create();
            alert.show();
        } else {
            Log.v(TAG, "Activity background -> Use Notification");

            NotificationManager nm = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context);

            notificationBuilder.setContentTitle("Chris, are you dead yet?");

            Intent aliveIntent = new Intent(context, lifesignAlive.class);
            // PendingIntent pAliveIntent = new PendingIntent.getActivity(context, 0, aliveIntent,0);

            notificationBuilder.setSmallIcon(R.drawable.ic_menu_send);
            notificationBuilder.setPriority(Notification.PRIORITY_MAX);
            notificationBuilder.setDefaults(Notification.DEFAULT_ALL);

            Notification notification = notificationBuilder.build();
            nm.notify(0, notification);
        }

    }
}

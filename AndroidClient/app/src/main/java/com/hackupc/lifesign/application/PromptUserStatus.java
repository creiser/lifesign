package com.hackupc.lifesign.application;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.hackupc.lifesign.R;
import com.hackupc.lifesign.activities.MainActivity;

/**
 * Created by tvaisanen on 9/18/17.
 */

public class PromptUserStatus extends BroadcastReceiver {

    private static final String TAG = "app.PromptUserStatus";


    @Override
    public void onReceive(Context context, Intent intent){

        context = MainActivity.appContext;

        if (Lifesign.isActivityVisible()){
            Log.v(TAG, "Activity visible -> Use AlertDialog");
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);

            alertBuilder.setTitle("Hello Chris!");
            alertBuilder.setMessage("Are you dead yet?");

            alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.dismiss();
                }
            });

            alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.dismiss();
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
            notificationBuilder.setSmallIcon(R.drawable.ic_menu_send);
            notificationBuilder.setPriority(Notification.PRIORITY_MAX);
            notificationBuilder.setDefaults(Notification.DEFAULT_ALL);

            Notification notification = notificationBuilder.build();
            nm.notify(0, notification);
        }

    }
}

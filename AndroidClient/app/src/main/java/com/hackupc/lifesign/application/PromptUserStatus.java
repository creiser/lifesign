package com.hackupc.lifesign.application;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.hackupc.lifesign.activities.MainActivity;

/**
 * Created by tvaisanen on 9/18/17.
 */

public class PromptUserStatus extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){

        System.out.println("hello from prompt!");
        context = MainActivity.appContext;
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
    }
}

package com.hackupc.lifesign.application;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.hackupc.lifesign.application.PromptUserStatus;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by tvaisanen on 9/18/17.
 */

public class TimerTask {

    private boolean taskRunning;

    public TimerTask(Context context, int promptInterval){
        System.out.println("TimerTask: " + promptInterval);
        startLifeSignTask(context, promptInterval);
    }

    public void startLifeSignTask(Context context, int promptInterval){
        setPromptInterval(context, promptInterval);
        promptUser(context);
        setPromptInterval(context, 1);
    }

    public void promptUser(Context context){
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

    public void setPromptInterval(Context context, int promptInterval){
        AlarmManager processTimer = (AlarmManager)context.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(context, PromptUserStatus.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        processTimer.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(), promptInterval, pendingIntent);
    }

}

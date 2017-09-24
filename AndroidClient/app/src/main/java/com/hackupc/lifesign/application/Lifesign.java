package com.hackupc.lifesign.application;

import android.app.Application;
import android.util.Log;

/**
 * Created by tvaisanen on 9/24/17.
 *
 * The application core
 *
 */

public class Lifesign extends Application {

    private static boolean activityVisible;
    private static final String TAG = "application.Lifesign";

    public static boolean isActivityVisible() {
        /*
        * Get the applications visibility state
        * */
        Log.v(TAG, "isActivityVisible(): " + activityVisible);
        return activityVisible;
    }

    public static void activityResumed() {
        Log.v(TAG, "activityResumed()");
        Log.v(TAG, "before " + activityVisible);
        activityVisible = true;
        Log.v(TAG, "after " + activityVisible);

    }

    public static void activityPaused() {
        Log.v(TAG, "activityPaused()");
        Log.v(TAG, "before " + activityVisible);
        activityVisible = false;
        Log.v(TAG, "after " + activityVisible);
    }
}

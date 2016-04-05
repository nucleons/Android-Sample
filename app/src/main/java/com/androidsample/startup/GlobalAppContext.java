package com.androidsample.startup;

import android.app.Application;
import android.content.Context;

/**
 * Created by Aditya on 04-Apr-16.
 */
public class GlobalAppContext extends Application {

    private static Context applicationContext;

    public void onCreate() {
        super.onCreate();
        applicationContext = this.getApplicationContext();
    }

    public static Context getContext() {
        return applicationContext;
    }
}

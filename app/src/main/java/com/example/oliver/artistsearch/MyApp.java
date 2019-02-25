package com.example.oliver.artistsearch;

import android.app.Application;
import android.content.Context;


public class MyApp extends Application {

    private static MyApp sInstance;
    private static Context context;

    public static MyApp getInstance() {
        if (sInstance == null) {
            sInstance = new MyApp();
        }
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }



    public Context getAppContext(){
        return context;
    }

}

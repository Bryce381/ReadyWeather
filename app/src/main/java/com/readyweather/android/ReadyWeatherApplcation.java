package com.readyweather.android;

import android.app.Application;
import android.content.Context;

public class ReadyWeatherApplcation extends Application {
    private static Context context;
    public static final String TOKEN = "7cChZMtT5rrUJUyQ";
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}

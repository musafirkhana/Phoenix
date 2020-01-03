package com.baf.musafir.phoenix;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;

public class Myapplication extends Application {
    private static Myapplication mInstance;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = this;
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public static Context getContext() {
        return mContext;
    }


}

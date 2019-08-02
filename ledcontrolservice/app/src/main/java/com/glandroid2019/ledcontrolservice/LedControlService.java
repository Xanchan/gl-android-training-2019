package com.glandroid2019.ledcontrolservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class LedControlService extends Service {
    private static final String LOG_TAG = "xan_ledcoservice";
    private LedControlServiceImpl mBinder = null;

    public LedControlService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(LOG_TAG, "OnCreate()");
        mBinder = new LedControlServiceImpl();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(LOG_TAG, "onStartCommand()");

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(LOG_TAG, "OnBind()");

        return mBinder;
    }
}


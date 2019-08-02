package com.glandroid2019.ledcontrolservice;
import android.os.RemoteException;
import android.util.Log;

import com.glandroid2019.ledcontrolservice.IMyLedControlService;

public class LedControlServiceImpl extends IMyLedControlService.Stub{
    private static final String LOG_TAG = "xan_ledcoserviceimpl";

    public int initializeLedControl() {
        Log.i(LOG_TAG, "init LedControl service");

        return 0;
    }

    public void setLedState(int led, int state) {
        Log.i(LOG_TAG, "setLedState: LED " + led + " state : " + state);
    }

    public int terminateLedControl() {
        Log.i(LOG_TAG, "terminateLedControl");

        return 0;
    }
}

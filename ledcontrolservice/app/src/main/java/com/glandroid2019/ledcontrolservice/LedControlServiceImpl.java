package com.glandroid2019.ledcontrolservice;
import android.os.RemoteException;
import android.util.Log;

import com.glandroid2019.ledcontrolservice.IMyLedControlService;
import vendor.globallogic.ledcontrol.V1_0.ILedControl;
import vendor.globallogic.ledcontrol.V1_0.Leds;
import vendor.globallogic.ledcontrol.V1_0.LedState;

public class LedControlServiceImpl extends IMyLedControlService.Stub{
    private static final String LOG_TAG = "xan_ledcoserviceimpl";
    private ILedControl ledService = null;

    public int initializeLedControl() {
        try {
            Log.i(LOG_TAG, "ILedControl get service");
            ledService = ILedControl.getService(true);
        } catch (RemoteException e) {
            Log.e(LOG_TAG, "can't getService. remote exception");
            return 1;
        }

        try {
            if(ledService != null) {
                ledService.initializeLedControl();
            } else {
                Log.e(LOG_TAG, "can't init LedControl. ledService == null");
                return 1;
            }
        } catch (RemoteException e) {
            Log.e(LOG_TAG, "can't init LedControl.");
            return 1;
        }

        return 0;
    }

    public void setLedState(int led, int state) {
        try {
            Log.i(LOG_TAG, "LED " + led + " state : " + state);
            ledService.setLedState((byte)led, (byte)state);
        } catch (RemoteException e) {
            Log.e(LOG_TAG, "can't setLedState");
        }
    }

    public int terminateLedControl() {
        try {
            Log.i(LOG_TAG, "terminateLedControl");
            ledService.terminateLedControl();
        } catch (RemoteException e) {
            Log.e(LOG_TAG, "can't terminateLedControl");
            return 1;
        }

        return 0;
    }
}

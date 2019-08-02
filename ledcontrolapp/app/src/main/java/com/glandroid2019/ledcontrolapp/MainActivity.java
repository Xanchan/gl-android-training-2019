package com.glandroid2019.ledcontrolapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.glandroid2019.ledcontrolservice.IMyLedControlService;

public class MainActivity extends AppCompatActivity {

    final static String LOG_TAG = "xan_ledcoapp";
    private static final String packageName = "com.glandroid2019.ledcontrolservice";
    private static final String serviceName = packageName + "." + "LedControlService";
    private IMyLedControlService mService = null;
    private boolean ledControlInitStatus = false;

    Button button;
    Switch swLed1, swLed2, swLed3, swLed4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "onCreate()");
        startSrv();
        button = findViewById(R.id.button);
        swLed1 = findViewById(R.id.Led1);
        swLed2 = findViewById(R.id.Led2);
        swLed3 = findViewById(R.id.Led3);
        swLed4 = findViewById(R.id.Led4);

        swLed1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                try {
                    if (isChecked) {
                        Log.i(LOG_TAG, "LED1 - Enabled");
                        mService.setLedState(1, 1);
                    } else {
                        Log.i(LOG_TAG, "LED1 - Disabled");
                        mService.setLedState(1, 0);
                    }
                } catch (RemoteException e) {
                    Log.e(LOG_TAG, "can't setLedState 1.");
                }
            }
        });

        swLed2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                try {
                    if (isChecked) {
                        Log.i(LOG_TAG, "LED2 - Enabled");
                        mService.setLedState(2, 1);
                    } else {
                        Log.i(LOG_TAG, "LED2 - Disabled");
                        mService.setLedState(2, 0);
                    }
                } catch (RemoteException e) {
                    Log.e(LOG_TAG, "can't setLedState 2.");
                }
            }
        });

        swLed3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                try {
                    if (isChecked) {
                        Log.i(LOG_TAG, "LED3 - Enabled");
                        mService.setLedState(3, 1);
                    } else {
                        Log.i(LOG_TAG, "LED3 - Disabled");
                        mService.setLedState(3, 0);
                    }
                } catch (RemoteException e) {
                    Log.e(LOG_TAG, "can't setLedState 3.");
                }
            }
        });

        swLed4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                try {
                    if (isChecked) {
                        Log.i(LOG_TAG, "LED4 - Enabled");
                        mService.setLedState(4, 1);
                    } else {
                        Log.i(LOG_TAG, "LED4 - Disabled");
                        mService.setLedState(4, 0);
                    }
                } catch (RemoteException e) {
                    Log.e(LOG_TAG, "can't setLedState 4.");
                }
            }
        });

        View.OnClickListener setButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    resetSwitches();
                    if(mService != null) {
                        if (ledControlInitStatus) {
                            if (mService.terminateLedControl() == 0) {
                                ledControlInitStatus = false;
                            }
                        } else {
                            if (mService.initializeLedControl() == 0) {
                                ledControlInitStatus = true;
                            }
                        }
                    } else {
                        Log.e(LOG_TAG, "error in onClick. mService == null");
                    }
                } catch (RemoteException e) {
                    Log.e(LOG_TAG, "initLedControl RemoteException");
                }
            }
        };

        button.setOnClickListener(setButton);
    }

    private void startSrv()  {
        Log.d(LOG_TAG, "onStartSrv");
        Intent intent = new Intent(serviceName);
        intent.setPackage(packageName);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(LOG_TAG, "onServiceConnected");
            mService = IMyLedControlService.Stub.asInterface(iBinder);
            try {
                if(mService.initializeLedControl() == 0){
                    ledControlInitStatus = true;
                    resetSwitches();
                }
            } catch (RemoteException e) {
                Log.e(LOG_TAG, "initLedControl RemoteException");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(LOG_TAG, "onServiceDisonnected");
            try {
                if(mService.terminateLedControl() == 0){
                    ledControlInitStatus = false;
                    resetSwitches();
                }
            } catch (RemoteException e) {
                Log.e(LOG_TAG, "terminateLedControl RemoteException");
            }
            mService = null;
        }
    };

    private void resetSwitches() {
        swLed1.setChecked(false);
        swLed2.setChecked(false);
        swLed3.setChecked(false);
        swLed4.setChecked(false);
    }

}

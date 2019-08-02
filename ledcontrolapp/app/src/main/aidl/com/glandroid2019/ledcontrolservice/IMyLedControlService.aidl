// IMyLedControlService.aidl
package com.glandroid2019.ledcontrolservice;

interface IMyLedControlService {
    int initializeLedControl();
    void setLedState(int led, int state);
    int terminateLedControl();
}

/*
 * Copyright (C) 2019 Globallogic
 *
 */
#define LOG_TAG "xan_LedControl_HAL"
#include "LedControl.h"
#include <fstream>
#include <sstream>
#include <iostream>
#include <vector>

namespace vendor {
namespace globallogic {
namespace ledcontrol {
namespace V1_0 {

using namespace std;

vector <string> backupTrigger;
vector <string> backupBrightness;

LedControl::LedControl()
{
    ALOGI("Constructor");
}

Return<int32_t> LedControl::initializeLedControl(void)
{
    ALOGI("initializeLedControl");
    saveLedState();
    for(int i = 1; i <= 4; i++){ 
        setLedBrightness(i, LED_OFF);
        setLedTrigger(i, LED_INIT_TRIGGER);
    }

    return 0;
}

Return<int32_t> LedControl::terminateLedControl(void)
{
    ALOGI("terminateLedControl");
    if(backupTrigger.size() != 4 || backupBrightness.size() != 4) {
        ALOGI("backup array size != 4 and trigger arr = %lu brightness arr = %lu", backupTrigger.size(), backupBrightness.size());
    }
    for(int i = 1; i <= 4; i++) {
        setLedBrightness(i, backupBrightness[i - 1]);
        setLedTrigger(i, backupTrigger[i - 1]);
    }

    return 0;
}

Return<int32_t> LedControl::setLedState(Leds led, LedState state)
{
    string brightness;

    ALOGI("setLedState:%d %d", (int)led, (int)state);
    if((int)state){
        brightness = LED_ON;
    } else {
        brightness = LED_OFF;
    }
    setLedBrightness((int)led, brightness);

    return 0;
}

Return<int32_t> LedControl::setLedTrigger(int led, string trigger) {
    string path;
    ofstream myFile;

    ALOGI("setLedTrigger:%d %s", led, trigger.c_str());
    path = getLedPath(led, LED_TRIGGER);
    myFile.open(path);
    if(myFile.is_open()){
        myFile << trigger;
        myFile.close();
    }

    return 0;
}

Return<int32_t> LedControl::setLedBrightness(int led, string brightness) {
    string path;
    ofstream myFile;

    ALOGI("setLedBrightness:%d %s", led, brightness.c_str());
    path = getLedPath(led, LED_BRIGHTNESS);
    myFile.open(path);
    if(myFile.is_open()){
        myFile << brightness;
        myFile.close();
    }

    return 0;
}

Return<string> LedControl::getLedPath(int num, string type) {
    stringstream ss;

    ss << LED_PATH << num << type;

    return ss.str();
}

Return<string> LedControl::readLineFromFile(string path) {
    ifstream myFile;
    string strBuf;

    if(path.length() != 0) {
        myFile.open(path);
        if (myFile.is_open()) {
            getline(myFile, strBuf);
            myFile.close();
        } else {
            ALOGI("init couldn't open file with path %s", path.c_str());
        }
    }

    return strBuf;
}

Return<bool> LedControl::saveLedState(void) {

    ALOGI("saveLedState");
    for (int i = 1; i <= 4; i++){
        string path, strBuff, triggerStr;
        int startPos = 0, endPos = 0;

        path = getLedPath(i, LED_TRIGGER);
        strBuff = readLineFromFile(path);
        startPos = strBuff.find("[") + 1;
        endPos = strBuff.find("]");

        if(startPos != 0 && endPos != 0) {
            triggerStr = strBuff.substr(startPos, endPos - startPos);
        } else {
            ALOGI("cant find trigger value. startPos = %d, endPos = %d", startPos, endPos);
            triggerStr = "none";
        }

        backupTrigger.push_back(triggerStr);

        path = getLedPath(i, LED_BRIGHTNESS);
        strBuff = readLineFromFile(path);

        backupBrightness.push_back(strBuff);
    }
    for(int i = 0; i < 4; i++) {
        ALOGI("led%d trigger = %s, brightness = %s", i + 1, backupTrigger[i].c_str(), backupBrightness[i].c_str());
    }

    return true;
}

}  // namespace V1_0
}  // namespace ledcontrol
}  // namespace globallogic
}  // namespace vendor

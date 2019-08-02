/*
 * Copyright (C) 2019 Globallogic
 *
 */

#ifndef vendor_globallogic_ledcontrol_V1_0_LedControl_H_
#define vendor_globallogic_ledcontrol_V1_0_LedControl_H_

//#define LOG_TAG "LedControl_HAL"
#include <vendor/globallogic/ledcontrol/1.0/ILedControl.h>
#include <log/log.h>
#include <string>

namespace vendor {
namespace globallogic {
namespace ledcontrol {
namespace V1_0 {

using namespace android::hardware;
using namespace std;

static const string LED_PATH = "/sys/class/leds/user_led";
static const string LED_TRIGGER = "/trigger";
static const string LED_INIT_TRIGGER = "none";
static const string LED_BRIGHTNESS = "/brightness";
static const string LED_ON = "255";
static const string LED_OFF = "0";


class LedControl: public ILedControl {
public:
    LedControl();
    Return<int32_t> initializeLedControl(void) override;
    Return<int32_t> terminateLedControl(void) override;
    Return<int32_t> setLedState(Leds led, LedState state) override;

private:
    Return<bool> saveLedState(void);
    Return<string> getLedPath(int num, string type);
    Return<string> readLineFromFile(string path);
    Return<int32_t> setLedTrigger (int led, string trigger);
    Return<int32_t> setLedBrightness(int led, string brightness); 
};
}  // namespace V1_0
}  // namespace ledcontrol
}  // namespace globallogic
}  // namespace vendor
#endif //vendor_globallogic_ledcontrol_V1_0_LedControl_H_

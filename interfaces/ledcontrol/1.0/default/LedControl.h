/*
 * Copyright (C) 2019 Globallogic
 *
 */

#ifndef vendor_globallogic_ledcontrol_V1_0_LedControl_H_
#define vendor_globallogic_ledcontrol_V1_0_LedControl_H_

//#define LOG_TAG "LedControl_HAL"
#include <vendor/globallogic/ledcontrol/1.0/ILedControl.h>
#include <log/log.h>

namespace vendor {
namespace globallogic {
namespace ledcontrol {
namespace V1_0 {

using namespace android::hardware;

class LedControl: public ILedControl {
public:
    LedControl();
    Return<int32_t> initializeLedControl(void) override;
    Return<int32_t> terminateLedControl(void) override;
    Return<int32_t> setLedState(Leds led, LedState state) override;

};
}  // namespace V1_0
}  // namespace ledcontrol
}  // namespace globallogic
}  // namespace vendor
#endif //vendor_globallogic_ledcontrol_V1_0_LedControl_H_

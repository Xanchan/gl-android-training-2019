/*
 * Copyright (C) 2019 Globallogic
 *
 */
#define LOG_TAG "xan_LedControl_HAL"
#include "LedControl.h"

namespace vendor {
namespace globallogic {
namespace ledcontrol {
namespace V1_0 {

LedControl::LedControl()
{
    ALOGI("Constructor");
}

Return<int32_t> LedControl::initializeLedControl(void)
{
    ALOGI("initializeLedControl");

    return 0;
}

Return<int32_t> LedControl::terminateLedControl(void)
{
    ALOGI("terminateLedControl");

    return 0;
}

Return<int32_t> LedControl::setLedState(Leds led, LedState state)
{
    ALOGI("setLedState:%d %d", (int)led, (int)state);
 
    return 0;
}

}  // namespace V1_0
}  // namespace ledcontrol
}  // namespace globallogic
}  // namespace vendor

# Copyright 2007-2011 The Android Open Source Project

LOCAL_PATH:= $(call my-dir)/app/src/main
include $(CLEAR_VARS)

LOCAL_PACKAGE_NAME := ledcontrolapp


LOCAL_STATIC_JAVA_LIBRARIES := android-support-v4
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v7-appcompat
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v13
LOCAL_STATIC_JAVA_LIBRARIES += android-support-constraint-layout
LOCAL_STATIC_JAVA_LIBRARIES += vendor.globallogic.ledcontrol-V1.0-java

LOCAL_USE_AAPT2 := true

LOCAL_SRC_FILES := $(call all-java-files-under, java)
LOCAL_SRC_FILES += $(call all-Iaidl-files-under, aidl)
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res
LOCAL_RESOURCE_DIR += prebuilts/sdk/current/support/v7/appcompat/res

LOCAL_CERTIFICATE := platform
LOCAL_PROGUARD_ENABLED := disabled
LOCAL_MODULE_TAGS := optional
LOCAL_PRIVATE_PLATFORM_APIS := true

include $(BUILD_PACKAGE)

# Copyright 2007-2011 The Android Open Source Project

LOCAL_PATH:= $(call my-dir)/app/src/main
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

#LOCAL_JAVA_LIBRARIES :=

LOCAL_STATIC_ANDROID_LIBRARIES := \
    androidx.appcompat_appcompat \
    androidx-constraintlayout_constraintlayout

LOCAL_STATIC_JAVA_LIBRARIES := android-support-v4
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v7-appcompat
#LOCAL_STATIC_JAVA_LIBRARIES += android-support-v7-gridlayout
#LOCAL_STATIC_JAVA_LIBRARIES += android-support-v13
LOCAL_USE_AAPT2 := true

LOCAL_SRC_FILES := $(call all-java-files-under, java)
LOCAL_SRC_FILES := $(call all-java-files-under, aidl)
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res
LOCAL_RESOURCE_DIR += prebuilts/sdk/current/support/v7/appcompat/res
#LOCAL_RESOURCE_DIR += prebuilts/sdk/current/support/v7/gridlayout/r
#LOCAL_AIDL_INCLUDES := $(LOCAL_PATH)/aidl

LOCAL_PACKAGE_NAME := SetterApp

LOCAL_PRIVATE_PLATFORM_APIS := true

#LOCAL_PROGUARD_FLAG_FILES := proguard.flags

include $(BUILD_PACKAGE)

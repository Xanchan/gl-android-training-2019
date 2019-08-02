## Globallogic Inc. 2019 All rughts received

PRODUCT_PACKAGES += \
   vendor.globallogic.ledcontrol@1.0-service.hikey960 \
   ledcontrolapp \
   ledcontrolservice

DEVICE_MANIFEST_FILE += \
   vendor/gl/interfaces/manifest.xml

BOARD_SEPOLICY_DIRS += \
   vendor/gl/sepolicy

TARGET_FS_CONFIG_GEN += \
   vendor/gl/config/config.fs

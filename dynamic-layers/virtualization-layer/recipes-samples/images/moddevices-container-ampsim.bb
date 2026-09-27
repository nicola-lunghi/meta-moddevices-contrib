SUMMARY = "Container image running aidadsp-lv2 plugin with moddevices software"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

require recipes-samples/images/moddevices-container-base.bb

IMAGE_INSTALL:append = " \
    aidadsp-lv2 \
    caps-lv2 \
    carla-plugins \
    lsp-plugins \
    x42-tinygain \
    x42-zconv \
    dragonfly-reverb \
    oneknob-series \
    guitarix-lv2 \
    gxplugins \
    container-browsepy-files \
"

export IMAGE_BASENAME = "moddevices-container-ampsim"

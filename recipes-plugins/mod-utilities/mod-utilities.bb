# Recipe to install mod-utilities software
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

S = "${WORKDIR}/git"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/mod-utilities.git;protocol=https;branch=master \
"
SRCREV="80ea3ea9f52fab7f191671f4810bf90fc955a046"

inherit pkgconfig

FXLIST1 = "\
    BandPassFilter \
    CrossOver2 \
    CrossOver3 \
    Gain \
    Gain2x2 \
    HighPassFilter \
    LowPassFilter \
    SwitchBox2 \
    SwitchTrigger4 \
    ToggleSwitch4 \
    HardwareBypass \
"

#FXLIST1 += "\
#    Note2Midi \
#    Peakmeter \
#"

FXLIST2 = "\
    inverted-switchbox \
    stereoswitchbox \
    stereoswitchbox2 \
"

EXTRA_OEMAKE = "NOOPT=true MOD=1"

do_install () {
    for fx in ${FXLIST1}; do
        install -d ${D}/${LV2_DIR}/${fx}.lv2
        cp -r ${S}/${fx}/*.so ${D}/${LV2_DIR}/${fx}.lv2
        cp -r ${S}/${fx}/ttl/*.ttl ${D}/${LV2_DIR}/${fx}.lv2
        cp -r ${S}/${fx}/ttl/modgui ${D}/${LV2_DIR}/${fx}.lv2
    done

    for fx in ${FXLIST2}; do
        install -d ${D}/${LV2_DIR}/${fx}.lv2
        cp -r ${S}/${fx}/${fx}.lv2/* ${D}/${LV2_DIR}/${fx}.lv2
    done
}

DEPENDS = " \
    alsa-lib \
    lv2 \
"

RDEPENDS:${PN} = "\
    libasound \
"

FILES:${PN} = "\
    ${LV2_DIR} \
"


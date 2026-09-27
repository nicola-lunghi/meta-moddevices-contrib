# Recipe to build and install mod midi utilities
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/mod-midi-utilities.git;protocol=https;branch=master \
"
SRCREV="ffa812ca034d0f6e5c924c29f59f78569246d958"

S = "${WORKDIR}/git"

FXLIST1 = "\
    midi-switchbox.lv2 \
    midi-inv-switchbox.lv2 \
    midi-switchbox3.lv2 \
    midi-switchbox4.lv2 \
"
FXLIST2 = "\
    peak-to-cc.lv2 \
"

EXTRA_OEMAKE = "'NOOPT=true'"

do_install () {
    for fx in ${FXLIST1}; do
        install -d ${D}/${LV2_DIR}/${fx}
        cp -r ${S}/${fx}/*.so ${D}/${LV2_DIR}/${fx}
        cp -r ${S}/${fx}/*.ttl ${D}/${LV2_DIR}/${fx}
        cp -r ${S}/${fx}/modgui ${D}/${LV2_DIR}/${fx}
    done

    for fx in ${FXLIST2}; do
        install -d ${D}/${LV2_DIR}/${fx}
        cp -r ${S}/${fx}/*.so ${D}/${LV2_DIR}/${fx}
        cp -r ${S}/${fx}/*.ttl ${D}/${LV2_DIR}/${fx}
    done

    chmod 755 -R ${D}/${LV2_DIR}/
}

DEPENDS += "\
    lv2 \
"

FILES:${PN} = "\
    ${LV2_DIR}/midi-inv-switchbox.lv2/* \
    ${LV2_DIR}/midi-switchbox.lv2/* \
    ${LV2_DIR}/midi-switchbox3.lv2/* \
    ${LV2_DIR}/midi-switchbox4.lv2/* \
    ${LV2_DIR}/peak-to-cc.lv2/* \
"

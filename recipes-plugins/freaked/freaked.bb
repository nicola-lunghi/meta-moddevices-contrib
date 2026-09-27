# Recipe to install Freaked software
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

S = "${WORKDIR}/git"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/pjotrompet/Freaked.git;protocol=https;branch=master \
"
SRCREV = "7f4fca70d7d424ddd353347bafb68e62aba0e043"

do_compile () {
    make -j 8 NOOPT=true
}

do_install () {
    install -d ${D}/${LV2_DIR}/Freakclip.lv2
    cp -r ${S}/LV2/Freakclip.lv2/Freakclip.so ${D}/${LV2_DIR}/Freakclip.lv2
    #cp -r ${S}/LV2/Freakclip.lv2/*.ttl ${D}/${LV2_DIR}/Freakclip.lv2
    #cp -r ${S}/LV2/Freakclip.lv2/modgui ${D}/${LV2_DIR}/Freakclip.lv2

    install -d ${D}/${LV2_DIR}/Freaktail.lv2
    cp -r ${S}/LV2/Freaktail.lv2/Freaktail.so ${D}/${LV2_DIR}/Freaktail.lv2
    cp -r ${S}/LV2/Freaktail.lv2/*.ttl ${D}/${LV2_DIR}/Freaktail.lv2
    cp -r ${S}/LV2/Freaktail.lv2/modgui ${D}/${LV2_DIR}/Freaktail.lv2

    install -d ${D}/${LV2_DIR}/Granulator.lv2
    cp -r ${S}/LV2/Granulator.lv2/Granulator.so ${D}/${LV2_DIR}/Granulator.lv2
    #cp -r ${S}/LV2/Granulator.lv2/*.ttl ${D}/${LV2_DIR}/Granulator.lv2
    #cp -r ${S}/LV2/Granulator.lv2/modgui ${D}/${LV2_DIR}/Granulator.lv2

    install -d ${D}/${LV2_DIR}/Prefreak.lv2
    cp -r ${S}/LV2/Prefreak.lv2/Prefreak.so ${D}/${LV2_DIR}/Prefreak.lv2
    cp -r ${S}/LV2/Prefreak.lv2/*.ttl ${D}/${LV2_DIR}/Prefreak.lv2
    cp -r ${S}/LV2/Prefreak.lv2/modgui ${D}/${LV2_DIR}/Prefreak.lv2

    chmod 755 -R ${D}/${LV2_DIR}/
}

DEPENDS = "\
    lilv \
    jack \
"

FILES:${PN} = "\
    ${LV2_DIR}/Freakclip.lv2/* \
    ${LV2_DIR}/Freaktail.lv2/* \
    ${LV2_DIR}/Granulator.lv2/* \
    ${LV2_DIR}/Prefreak.lv2/* \
"


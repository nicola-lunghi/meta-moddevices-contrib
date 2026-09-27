SUMMARY = "Bolliedelay lv2 plugin"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/MrBollie/bolliedelay.lv2.git;protocol=https;branch=master \
"
SRCREV = "e00d63e5011ac209de3a70bd9a83c7cbb896383a"

S = "${WORKDIR}/git"

inherit pkgconfig

do_compile () {
    oe_runmake MOD=1 OPTIMIZATIONS=""
}

do_install () {
    install -d ${D}/${LV2_DIR}/bolliedelay.lv2
    cp -r ${S}/build/bolliedelay.lv2/*.so ${D}/${LV2_DIR}/bolliedelay.lv2
    cp -r ${S}/build/bolliedelay.lv2/*.ttl ${D}/${LV2_DIR}/bolliedelay.lv2
    cp -r ${S}/build/bolliedelay.lv2/modgui ${D}/${LV2_DIR}/bolliedelay.lv2

    chmod 755 -R ${D}/${LV2_DIR}/bolliedelay.lv2
}

DEPENDS = "\
    lv2 \
"

RDEPENDS:${PN} = "\
"

FILES:${PN} = "\
    ${LV2_DIR}/bolliedelay.lv2 \
"

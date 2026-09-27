SUMMARY = "Loopor lv2 plugin"
DESCRIPTION = ""
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/stevie67/loopor.git;protocol=https;branch=master \
"
SRCREV="f89802c87db3b360c20af50ef2adedf60dcc9171"

S = "${WORKDIR}/git/loopor-lv2/source"

do_compile () {
    oe_runmake
}

do_install () {
    install -d ${D}/${LV2_DIR_BAD}/loopor.lv2
    cp -r ${S}/loopor.lv2/*.so ${D}/${LV2_DIR_BAD}/loopor.lv2
    cp -r ${S}/loopor.lv2/*.ttl ${D}/${LV2_DIR_BAD}/loopor.lv2
    cp -r ${S}/loopor.lv2/*.ttl.in ${D}/${LV2_DIR_BAD}/loopor.lv2
    cp -r ${S}/loopor.lv2/modgui ${D}/${LV2_DIR_BAD}/loopor.lv2

    chmod 755 -R ${D}/${LV2_DIR_BAD}/loopor.lv2
}

DEPENDS += "\
    lv2 \
"

FILES:${PN} = "\
    ${LV2_DIR_BAD}/loopor.lv2 \
"

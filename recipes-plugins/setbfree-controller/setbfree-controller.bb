SUMMARY = "Virtual controller based on LV2 to tune setBfree synthesizer"
DESCRIPTION = ""
SECTION = "lv2/unstable"
HOMEPAGE = "https://github.com/vallsv/setbfree-controller.lv2"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

#INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/vallsv/setbfree-controller.lv2;protocol=https;branch=master \
"
SRCREV="f38a874143814b92a7ca81f2274cc5a76c80bf6f"

S = "${WORKDIR}/git"

do_compile () {
    oe_runmake MOD_BUILD=1 OPTIMIZATIONS= PREFIX=/usr build
}

do_install () {
    install -d ${D}/${LV2_DIR_BAD}/${PN}.lv2

    cp -r ${S}/*.so ${D}/${LV2_DIR_BAD}/${PN}.lv2/
    cp -r ${S}/*.ttl ${D}/${LV2_DIR_BAD}/${PN}.lv2/
    cp -r ${S}/modgui ${D}/${LV2_DIR_BAD}/${PN}.lv2/

    chmod 755 -R ${D}/${LV2_DIR_BAD}
}

DEPENDS += "\
    lilv \
    jack \
"

FILES:${PN} = "\
    ${LV2_DIR_BAD}/${PN}.lv2 \
"

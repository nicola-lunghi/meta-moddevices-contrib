SUMMARY = "Sooper-looper lv2 plugin"
DESCRIPTION = ""
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

#INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/sooperlooper-lv2-plugin.git;protocol=https;branch=master \
"
SRCREV="d3e80029ce4f6ef62b3758e95c5b889cab1db740"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_OEMAKE = "'NOOPT=true'"

do_install() {
    #cd ${S}/sooperlooper
    #oe_runmake install DESTDIR=${D} INSTALL_PATH=${LV2_DIR_BAD}

    install -d ${D}/${LV2_DIR_BAD}/sooperlooper.lv2
    cp -r ${S}/sooperlooper/sooperlooper.so ${D}/${LV2_DIR_BAD}/sooperlooper.lv2

    install -d ${D}/${LV2_DIR_BAD}/sooperlooper-2x2.lv2
    cp -r ${S}/sooperlooper-2x2/sooperlooper-2x2.so ${D}/${LV2_DIR_BAD}/sooperlooper-2x2.lv2

    chmod 755 -R ${D}/${LV2_DIR_BAD}
}

DEPENDS = " \
    lilv \
"

RDEPENDS:sooper-looper-lv2 = "\
"

FILES:${PN} = "\
    ${LV2_DIR_BAD}/sooperlooper.lv2 \
    ${LV2_DIR_BAD}/sooperlooper-2x2.lv2 \
"

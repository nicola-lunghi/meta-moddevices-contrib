SUMMARY = "Calf studio gear lv2 plugin suite"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

#INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/calf-studio-gear/calf.git;protocol=https;tag=0.0.60 \
    file://fix-compilation-float-double.patch \
"

S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF = "--with-lv2-dir=${LV2_DIR} --exec-prefix=${LV2_DIR} --prefix=${LV2_DIR}"

do_install () {
    install -d ${D}/${LV2_DIR}/calf.lv2
    cp -r ${WORKDIR}/build/src/.libs/calf.so ${D}/${LV2_DIR}/calf.lv2
    chmod 755 -R ${D}/${LV2_DIR}

    install -d ${D}/${LV2_DIR_BAD}/calf-bad.lv2
    cp -r ${WORKDIR}/build/src/.libs/calf.so ${D}/${LV2_DIR_BAD}/calf-bad.lv2
    chmod 755 -R ${D}/${LV2_DIR_BAD}
}

DEPENDS = " \
    cairo \
    fluidsynth \
    glib-2.0 \
    glib-2.0-native \
    lilv \
    jack \
"

FILES:${PN} = "\
    ${LV2_DIR}/calf.lv2 \
    ${LV2_DIR_BAD}/calf-bad.lv2 \
"


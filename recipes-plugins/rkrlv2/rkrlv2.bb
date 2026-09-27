# TODO: test this plugin suite
# INFO: this plugin suite uses fftw double precision calls
SUMMARY = "Rakarrack lv2 plugin suite (rkr)"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

#INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"
#INSANE_SKIP:${PN} = "ldflags"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/ssj71/rkrlv2.git;protocol=https;branch=master \
    file://Remove-custom-cxx-flags.patch \
"
SRCREV = "7edcb4e29a358623bfd57fa2c27e5da60adfcec3"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

do_install () {
    # Install rkr
    install -d ${D}/${LV2_DIR}/rkr.lv2
    cp ${WORKDIR}/build/lv2/rkrlv2.so ${D}/${LV2_DIR}/rkr.lv2/
    chmod 755 -R ${D}/${LV2_DIR}

    # Install rkr-bad
    install -d ${D}/${LV2_DIR_BAD}/rkr-bad.lv2
    cp ${WORKDIR}/build/lv2/rkrlv2.so ${D}/${LV2_DIR_BAD}/rkr-bad.lv2/
    chmod 755 -R ${D}/${LV2_DIR_BAD}
}

DEPENDS = "\
    lv2 \
    fftw \
    libsamplerate0 \
"

RDEPENDS:${PN} = "\
    libfftw \
"

FILES:${PN} = "\
    ${LV2_DIR}/rkr.lv2 \
    ${LV2_DIR_BAD}/rkr-bad.lv2 \
"

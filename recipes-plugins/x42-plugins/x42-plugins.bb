# INFO: this plugin suite uses fftw single precision calls
SUMMARY = "Lv2 bundle x42-plugins"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/x42/x42-plugins.git;protocol=https;branch=master \
    file://0001-Skip-desktop-plugins.patch \
"
SRCREV="ec8a2d0d3a59a8465aef1325c0ba748e8e5b1757"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_OEMAKE = "'MOD=1' 'OPTIMIZATIONS=' 'BUILDOPENGL=no' 'BUILDJACKAPP=no' 'BUILDGTK=no' 'HAVE_UI=no' 'LIBZITACONVOLVER="-lzita-convolver"' 'PREFIX=' 'LV2DIR=${LV2_DIR}'"

do_install () {
    oe_runmake install DESTDIR=${D}
}

DEPENDS += " \
    lv2 \
    fftw \
    zita-convolver \
    libsndfile1 \
    libsamplerate0 \
"

FILES:${PN} = "\
    ${LV2_DIR} \
"

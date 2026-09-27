# INFO: this plugin suite uses fftw single precision calls and is using wisdom file
# cabsim.wisdom generated with unknown parameters
SUMMARY = "Cab IR plugin convolver from Modddevices"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/mod-cabsim-IR-loader.git;protocol=https;branch=master \
    file://01-fix-installation-paths.patch;patchdir=../ \
    file://cabsim.wisdom \
"
SRCREV="75876819500a674fb14841e900c12723d21602ef"

S = "${WORKDIR}/git/source"

inherit pkgconfig

do_compile () {
    oe_runmake
}

do_install () {
    oe_runmake install DESTDIR=${D} PREFIX=${LV2_DIR}
    cp ${WORKDIR}/cabsim.wisdom ${D}/${LV2_DIR}/cabsim-IR-loader.lv2
}

DEPENDS = "\
    lv2 \
    fftw \
    libsamplerate0 \
"

RDEPENDS:${PN} = "\
    libfftwf \
"

FILES:${PN} = "\
    ${LV2_DIR}/cabsim-IR-loader.lv2 \
"

SUMMARY = "Lv2 plugin modspectre"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
BUNDLEDIR = "${@bb.utils.contains('SECTION', 'lv2/stable', '${LV2_DIR}', '${LV2_DIR_BAD}', d)}"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/x42/modspectre.lv2.git;protocol=https;branch=master \
"
SRCREV="ec65c2fed8e65e38f656d57ea3e12c76bd046bba"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_OEMAKE = "'MOD=1' 'OPTIMIZATIONS="-fno-finite-math-only"' 'PREFIX=' 'LV2DIR=${BUNDLEDIR}'"

do_install () {
    oe_runmake install DESTDIR=${D}
}

DEPENDS += " \
    lv2 \
    fftw \
"

FILES:${PN} = "\
    ${BUNDLEDIR} \
"

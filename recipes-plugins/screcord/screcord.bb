SUMMARY = "Screcord.lv2 a Lv2 capture plugin"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
BUNDLEDIR = "${@bb.utils.contains('SECTION', 'lv2/stable', '${LV2_DIR}', '${LV2_DIR_BAD}', d)}"

INSANE_SKIP:${PN} = "already-stripped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/brummer10/screcord.lv2.git;protocol=https;branch=master \
    file://fix-compilation.patch \
"
SRCREV="fe0d42d31fecde5ad7d3c05514cf2ecb57fd2495"

S = "${WORKDIR}/git"

inherit pkgconfig

DEPENDS = " \
    libsndfile1 \
    lv2 \
"

do_compile () {
    oe_runmake mod
}

do_install () {
    oe_runmake install DESTDIR=${D}${BUNDLEDIR} INSTALL_DIR=""
}

FILES:${PN} = "\
    ${BUNDLEDIR} \
"

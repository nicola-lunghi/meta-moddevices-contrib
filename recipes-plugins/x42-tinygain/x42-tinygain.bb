SUMMARY = "Lv2 plugin x42-tinyamp"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/x42/tinyamp.lv2.git;protocol=https;branch=master \
"
SRCREV="7da2876da03a443cb08b27f0c4d3f24633ed91c4"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_OEMAKE = "'MOD=1' 'OPTIMIZATIONS="-fno-finite-math-only"' 'PREFIX=' 'LV2DIR=${LV2_DIR}'"

do_install () {
    oe_runmake install DESTDIR=${D}
}

DEPENDS += "\
    lv2 \
"

FILES:${PN} = "\
    ${LV2_DIR} \
"

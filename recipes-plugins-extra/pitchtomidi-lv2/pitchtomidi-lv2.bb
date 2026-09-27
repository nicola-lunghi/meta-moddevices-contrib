SUMMARY = "Lv2 plugin pitchtomidi"
DESCRIPTION = ""
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/dsheeler/harmonizer.lv2.git;protocol=https;branch=master \
    file://0001-Fixing-shell-command-that-broke-compilation.patch \
"
SRCREV = "b16e01c71263bd6254df3096eb16c70864a3d6dd"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_OEMAKE = "'MOD=1' 'OPTIMIZATIONS="-msse -msse2 -mfpmath=sse -ffast-math -fomit-frame-pointer -O3 -fno-finite-math-only"' 'PREFIX=' 'LV2DIR=${LV2_DIR_BAD}'"

do_install () {
    oe_runmake install DESTDIR=${D}
}

DEPENDS += "\
    lv2 \
"

FILES:${PN} = "\
    ${LV2_DIR_BAD} \
"

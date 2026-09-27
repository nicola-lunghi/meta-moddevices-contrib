SUMMARY = "Lenticular lv2 plugin suite from Polyeffects"
DESCRIPTION = "A bundle of interesting plugins based on units from Mutable Instruments"
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/polyeffects/lenticular_lv2.git;protocol=https;branch=master \
    file://fix-lv2-includes.patch \
    file://fix-typo-makefile.patch \
"
SRCREV="d1127077e9d7b29fdc9824d725c31464633b6a11"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_OEMAKE = "'DESTDIR=${D}' 'LV2DIR=${LV2_DIR_BAD}'"

do_configure:prepend() {
    cd ${S}
    git submodule update --init --recursive
}

do_install () {
    oe_runmake install
}

DEPENDS += " \
    lv2 \
    jack \
"

FILES:${PN} = "\
    ${LV2_DIR_BAD} \
"

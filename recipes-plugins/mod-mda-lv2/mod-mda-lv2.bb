SUMMARY = "Mod-mda lv2 plugin suite"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

#INSANE_SKIP:${PN} = "already-stripped"
INSANE_SKIP:${PN} += " installed-vs-shipped"

S = "${WORKDIR}/git"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/mda-lv2.git;protocol=https;branch=master \
"
SRCREV="3d6dd099146b72c1fe88e06679034715fb999a5b"

inherit waf pkgconfig

EXTRA_OECONF = "--lv2-user --lv2dir=${LV2_DIR}"

WAF_PYTHON = "python"

do_install() {
    ${S}/waf -j1 install --destdir=${D}
}

DEPENDS = " \
    lilv \
    jack \
"

RDEPENDS:${PN} = "\
"

FILES:${PN} = "\
    ${LV2_DIR} \
"

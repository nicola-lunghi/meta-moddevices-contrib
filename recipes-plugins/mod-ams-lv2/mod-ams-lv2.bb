# INFO: this plugin suite uses fftw double precision calls. It uses also mod:CVPort specifier in the ttl.
SUMMARY = "Mod-ams lv2 plugin suite"
DESCRIPTION = "This a sub-set of LV2 plugins that is a port of the internal modules found in Alsa Modular Synth"
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

S = "${WORKDIR}/git"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/mod-ams-lv2.git;protocol=https;branch=master \
    file://limit_frequency.patch \
    file://0001-We-re-using-lvtk-version-2.0.0.patch \
    file://0002-Adjusted-includes-for-lvtk-2.0.0.patch \
"
SRCREV="75895e56f9aeca84463dcfc83390617fa3d3ff67"

inherit waf pkgconfig

EXTRA_OECONF = "--lv2-user --lv2dir=${LV2_DIR_BAD}"

do_install() {
    ${S}/waf -j1 install --destdir=${D}
}

DEPENDS = " \
    fftw \
    lvtk \
"

RDEPENDS:${PN} = "\
"

FILES:${PN} = "\
    ${LV2_DIR_BAD} \
"

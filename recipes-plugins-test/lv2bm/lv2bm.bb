# Recipe to install lv2bm, a benchmark tool for LV2 plugins
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/lv2bm.git;protocol=https;branch=master \
"
SRCREV = "ffd35a049c5f609f93460289dae0b319ad297810"

S = "${WORKDIR}/git"

inherit pkgconfig

do_compile () {
    oe_runmake 'CC=${CC}' 'LD=${LD}'
}

DEPENDS = "\
    libsndfile1 \
    glib-2.0 \
    lv2 \
    lilv \
    jack \
"

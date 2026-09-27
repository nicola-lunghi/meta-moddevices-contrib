SUMMARY = "LV2 Toolkit"
DESCRIPTION = "This software package contains libraries that wrap the LV2 C API and extensions into easy to use C++ classes."
SECTION = "devel"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/lvtk/lvtk.git;protocol=https;branch=master \
"
SRCREV="6bfe981dfb5b27ea199dd4f6801b5305ca0355f9"

S = "${WORKDIR}/git"

PV = "2.0.0"

inherit waf pkgconfig

WAF_PYTHON = "python"

EXTRA_OECONF = "--prefix=/usr --disable-examples --disable-ui"

do_install:prepend () {
    cp -r ${WORKDIR}/build ${S}/
}

DEPENDS = "\
    lv2 \
"

RDEPENDS:${PN} = " \
"

RPROVIDES:${PN} = " \
    lvtk-2 \
"

FILES:${PN} = "\
    /usr/include/lvtk-2 \
    /usr/lib \
"

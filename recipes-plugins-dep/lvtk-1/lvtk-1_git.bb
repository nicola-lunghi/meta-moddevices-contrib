SUMMARY = "LV2 Toolkit"
DESCRIPTION = "This software package contains libraries that wrap the LV2 C API and extensions into easy to use C++ classes."
SECTION = "devel"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/lvtk/lvtk.git;protocol=https;branch=master \
"
SRCREV="c105fd5077b4f7d963ad543b9979b94b9b052551"

S = "${WORKDIR}/git"

PV = "1.0.0"

inherit waf pkgconfig

do_configure () {
    ${S}/waf configure --prefix=/usr --disable-examples --disable-tools --disable-ui
}

do_compile () {
    ${S}/waf build
}

do_install () {
    ${S}/waf install --destdir=${D}
}

DEPENDS = "\
	lv2 \
"

RDEPENDS:${PN} = " \
"

RPROVIDES:${PN} = " \
    lvtk-1 \
"

FILES:${PN} = "\
    /usr/include/lvtk-1 \
	/usr/lib \
"

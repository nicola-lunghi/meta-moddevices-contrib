# Recipe to install jack-capture
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/kmatheussen/jack_capture.git;protocol=git;branch=master \
    file://01_fix-cross-compile.patch \
    file://02_configure-sndfile.patch \
"
SRCREV="e2c0c3d25fce0a13f3f18c622079cd29178af7f8"

S = "${WORKDIR}/git"

inherit pkgconfig

do_compile() {
    oe_runmake jack_capture
}

do_install() {
    oe_runmake install DESTDIR=${D}
}

DEPENDS = "\
    libsndfile1 \
    jack \
"

RDEPENDS:jack-capture = "\
    jack-server \
"

FILES:${PN} = "\
    /usr/local/bin/jack_capture \
"

# Recipe to install mod-host software
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"

INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

S = "${WORKDIR}/git"

SRC_URI = "\
    git://github.com/moddevices/mod-host.git;protocol=https;branch=master \
    file://01_fix-libjack-dir.patch \
    file://02_connect-all-networks.patch \
"
SRCREV="f36bce78eed80f4f7194c923afd4dcae2c80bc79"

inherit pkgconfig

CFLAGS:append = "-ffast-math"

do_compile () {
    oe_runmake
}

do_install () {
    oe_runmake install DESTDIR=${D} PREFIX=/usr
}

DEPENDS = "\
    lilv \
    jack \
    fftw \
    readline \
"

FILES:${PN} += " \
    /usr/bin \
    /usr/lib \
    /usr/bin/mod-host \
    /usr/lib/jack \
    /usr/lib/jack/mod-host.so \
    /usr/lib/jack/mod-monitor.so \
"
# Recipe to install mxml
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

#INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"
INSANE_SKIP:${PN} = "ldflags"
INSANE_SKIP:${PN} += "useless-rpaths"

PV = "2.10"

## url is different from 2.10 to 2.11
## https://github.com/michaelrsweet/mxml/releases/download/v${PV}/mxml-${PV}.tar.gz (2.11)
## https://github.com/michaelrsweet/mxml/releases/download/release-${PV}/mxml-${PV}.tar.gz (2.10)
## file://0001-fix-cross-target-installation.patch (2.10)
## S dir need to be specified as S = ${WORKDIR}/mxml-${PV} for 2.10, as S = ${WORKDIR} for 2.11

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    https://github.com/michaelrsweet/mxml/releases/download/release-${PV}/mxml-${PV}.tar.gz \
    file://0001-fix-cross-target-installation.patch \
"
SRC_URI[md5sum] = "8804c961a24500a95690ef287d150abe"
SRC_URI[sha256sum] = "267ff58b64ddc767170d71dab0c729c06f45e1df9a9b6f75180b564f09767891"

S = "${WORKDIR}/mxml-${PV}"

inherit autotools pkgconfig

do_configure () {
    oe_runconf
}

do_compile:prepend () {
    cp -r ${S}/* ${WORKDIR}/build
}

do_compile () {
    oe_runmake
}

do_install () {
    oe_runmake 'DSTROOT=${D}' install
}

DEPENDS = " \
"

FILES:${PN} = "\
    /usr/lib/libmxml.so \
    /usr/lib/libmxml.so.1 \
    /usr/lib/libmxml.so.1.5 \
    /usr/include \
    /usr/lib/pkgconfig \
    /usr/lib/pkgconfig/mxml.pc \
    /usr/include/mxml.h \
"

# Recipe to install mod-lv2-extensions
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

S = "${WORKDIR}/git"

SRC_URI = "\
    git://github.com/moddevices/mod-sdk.git;protocol=https;branch=master \
    file://01_add-makefile.patch \
"
SRCREV="2fe7c7728faa551b2838baa49c0d1953c64f2151"

do_install () {
    oe_runmake install DESTDIR=${D} PREFIX=/usr
}

DEPENDS = "\
"

RDEPENDS:${PN} = "\
"

FILES:${PN} += " \
    /usr/lib/lv2 \
"


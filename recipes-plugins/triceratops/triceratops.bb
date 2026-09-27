# Recipe to install triceratops lv2 plugin
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
#FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

#INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"
#INSANE_SKIP:${PN} = "ldflags"
#INSANE_SKIP:${PN} += "useless-rpaths"

PV = "0.3.0"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    http://download.sourceforge.net/triceratops/triceratops-lv2-v${PV}.tar.gz \
"
SRC_URI[md5sum] = "ce6928d5e746c18ea7c50b81c7841d1f"
SRC_URI[sha256sum] = "359c4f05191cc565e569ea0b8eb264bcedafb6d190a6caefbd1db1501a1ff9d8"

S = "${WORKDIR}/triceratops-lv2-v${PV}"

inherit waf pkgconfig

do_configure () {
    ${S}/waf configure --prefix=${D}/usr
}

do_compile () {
    ${S}/waf build
}

do_install () {
    ${S}/waf install
}

# gtkmm
DEPENDS = " \
	lv2 \
"

FILES:${PN} = "\
"

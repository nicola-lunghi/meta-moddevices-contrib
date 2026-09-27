# Recipe to install stegosaurus lv2 plugin
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
#FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

#INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"
#INSANE_SKIP:${PN} = "ldflags"
#INSANE_SKIP:${PN} += "useless-rpaths"

PV = "0.0.3"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    http://download.sourceforge.net/stegosaurus-lv2/Stegosaurus_LV2_${PV}.tar.gz \
"
SRC_URI[md5sum] = "fec688cf4eced6e0ade69ce4d8cd33cd"
SRC_URI[sha256sum] = "c69725a2207379277b21e76ef1e6cc4370947b18bf15249cc99814f490508bc1"

#S = "${WORKDIR}/Stegosaurus_LV2_${PV}"
S = "${WORKDIR}/stegosaurus-git"

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
	cairo \
"

FILES:${PN} = "\
"

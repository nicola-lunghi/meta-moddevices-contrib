SUMMARY = "C library providing simple use of LV2 plugins"
HOMEPAGE = "http://drobilla.net/software/lilv"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=7aceb3a3edc99517b08f5cdd557e11fb"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit waf bash-completion pkgconfig

DEPENDS += "lv2 serd sord sratom"

PV = "0.24.2"

SRC_URI = "\
    git://github.com/lv2/lilv.git;protocol=https;branch=master \
    file://01_optional-skip-state-properties.patch \
    file://02_mod-version-compare.patch \
"
SRCREV = "a208ffd4776ebc9072bfca2603d6931e24917ccf"

S = "${WORKDIR}/git"

EXTRA_OECONF = "--configdir=${sysconfdir}"

SUMMARY = "C library for RDF syntax which supports accessing Turtle and NTriples"
HOMEPAGE = "http://drobilla.net/software/serd"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=7aceb3a3edc99517b08f5cdd557e11fb"

inherit waf pkgconfig

SRC_URI = "\
    git://github.com/drobilla/serd.git;protocol=https;branch=master \
    file://01_bypass-strict-separator-check.patch \
"
SRCREV = "32f10751605e7124e415d38feb4d8176288c03ad"

S = "${WORKDIR}/git"

PV = "0.28.0"

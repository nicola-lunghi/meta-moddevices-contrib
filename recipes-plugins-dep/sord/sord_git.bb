SUMMARY = "C library for storing RDF data in memory"
HOMEPAGE = "http://drobilla.net/software/sord"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=6b8d060e6d32fbd53684f9dc0443b6a3"

inherit waf pkgconfig

DEPENDS += "libpcre serd"

SRC_URI = "\
    git://git.drobilla.net/sord.git;protocol=http;branch=master \
"
SRCREV = "31ea384f24e12778d6e30cc7a30b0f48f3d50523"

S = "${WORKDIR}/git"

PV = "0.16.0"

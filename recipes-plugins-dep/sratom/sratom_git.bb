SUMMARY = "Sratom is a library for serialising LV2 atoms to and from RDF"
HOMEPAGE = "http://drobilla.net/software/sratom"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=ebc7934238811c788037421c6c548ddf"

inherit waf pkgconfig

DEPENDS += "lv2 serd sord"

SRC_URI = "\
    git://git.drobilla.net/sratom.git;protocol=http;branch=master \
"
SRCREV = "b9717ab942b38f023cee99b3c108514a651b2b51"

S = "${WORKDIR}/git"

PV = "0.6.0"

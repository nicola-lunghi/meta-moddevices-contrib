SUMMARY = "Simple web file browser using flask"
HOMEPAGE = "https://github.com/ergoithz/browsepy"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e8d404beb09945b662eef91856df88db"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit setuptools3 pkgconfig

SRC_URI = "\
    git://github.com/moddevices/browsepy.git;protocol=https;branch=master \
    file://01_skip-unicategories-req.patch \
"
SRCREV="c2245873f9432b2839f09be7eeb2992fd3bbc5ff"

S = "${WORKDIR}/git"

RDEPENDS:${PN} = "\
    python3-flask \
    python3-setuptools \
"

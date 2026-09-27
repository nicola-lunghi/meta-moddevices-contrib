SUMMARY = "LV2 is an open standard for audio plugins"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=0383f162366b0c5a316292759a55d292"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PV = "1.18.2"
SRC_URI = "\
    http://lv2plug.in/spec/lv2-${PV}.tar.bz2 \
    file://01_plugin-is-project.patch \
    file://02_lv2-validate-prefix.patch \
    file://03_lv2-validate-without-example-plugins.patch \
"
SRC_URI[md5sum] = "4693bac601af4ca329ff18e6d6dae3fa"
SRC_URI[sha256sum] = "4e891fbc744c05855beb5dfa82e822b14917dd66e98f82b8230dbd1c7ab2e05e"

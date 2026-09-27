# @TODO: fix me do_configure breaks for juceaide
SUMMARY = "Lv2 plugin analog tape model by Jatin Chowdhury"
DESCRIPTION = ""
HOMEPAGE = "https://chowdsp.com/"
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/jatinchowdhury18/AnalogTapeModel.git;protocol=https;branch=master \
    file://01_only-lv2.patch;patchdir=../ \
    file://02_no-lv2-gen.patch;patchdir=../ \
    file://03_set-lv2-features.patch;patchdir=../ \
    file://04_no-lto.patch;patchdir=../ \
    file://06_no-alsa-jack.patch;patchdir=../ \
    file://07_rtneural-build-arm.patch;patchdir=../ \
    file://toolchain.cmake;patchdir=../ \
"
SRCREV="139ab8b5c2030d432ec258887f852a274582a862"

S = "${WORKDIR}/git/Plugin"

inherit cmake

EXTRA_OECMAKE += "-DMOD_TOOLCHAIN_FILE=${WORKDIR}/toolchain.cmake"

DEPENDS = " \
    lilv \
    freetype \
"

RDEPENDS:${PN} = "\
"

FILES:${PN} = "\
"

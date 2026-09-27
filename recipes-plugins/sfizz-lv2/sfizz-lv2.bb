SUMMARY = "Sfizz lv2 plugin"
DESCRIPTION = "Sfizz is a player for samples in sfz format available as lv2 plugin"
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "\
    gitsm://github.com/sfztools/sfizz.git;protocol=https;branch=master \
    file://01-filetypes.patch \
    file://02-skip-qt-widgets.patch \
"
SRCREV="222df62eb0e968ef5aa859f0d96e5aca705001d5"

PV = "1.1.1"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

EXTRA_OECMAKE = "\
    -DSFIZZ_JACK=OFF \
    -DSFIZZ_RENDER=OFF \
    -DSFIZZ_LV2_UI=OFF \
    -DSFIZZ_VST=OFF \
    -DSFIZZ_AU=OFF \
    -DSFIZZ_SHARED=OFF \
    -DABSL_BUILD_DLL=FALSE \
    -DBUILD_SHARED_LIBS=FALSE \
    -DLV2PLUGIN_INSTALL_DIR=${LV2_DIR}\
"

DEPENDS += "\
    lv2 \
    jack \
    libsndfile1 \
"

FILES:${PN} = "\
    ${LV2_DIR} \
"

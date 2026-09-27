SUMMARY = "Artyfx OpenAV lv2 plugin suite"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/openAVproductions/openAV-ArtyFX.git;protocol=https;branch=master \
    file://01_build-flags.patch \
"
SRCREV="3fdeecd081c9a03b3a630d2ed0825674a30c05d5"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

EXTRA_OECMAKE = "-DBUILD_GUI=OFF"

do_install () {
    # artyfx.lv2 graphics is installed by mod-lv2-data recipe
    install -d ${D}/${LV2_DIR}/artyfx.lv2
    cp -r ${WORKDIR}/build/artyfx.so ${D}/${LV2_DIR}/artyfx.lv2
    chmod 755 -R ${D}/${LV2_DIR}/artyfx.lv2

    # artyfx-bad.lv2 graphics is installed by mod-lv2-data recipe
    install -d ${D}/${LV2_DIR_BAD}/artyfx-bad.lv2
    cp -r ${WORKDIR}/build/artyfx.so ${D}/${LV2_DIR_BAD}/artyfx-bad.lv2
    chmod 755 -R ${D}/${LV2_DIR_BAD}/artyfx-bad.lv2
}

DEPENDS = "\
    lv2 \
"

RDEPENDS:${PN} = "\
"

FILES:${PN} = "\
    ${LV2_DIR}/artyfx.lv2 \
    ${LV2_DIR_BAD}/artyfx-bad.lv2 \
"
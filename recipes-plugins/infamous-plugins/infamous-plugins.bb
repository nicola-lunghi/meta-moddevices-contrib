# INFO: this plugin suite uses fftw single precision calls
SUMMARY = "Infamous lv2 plugin suite"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/ssj71/infamousPlugins.git;protocol=https;branch=master \
"
SRCREV="28b405414a5d044e576ab00b75ceaa1c0a7b8929"

S = "${WORKDIR}/git"

FXLIST = "\
    envfollower \
    bentdelay \
    powerup \
    lushlife \
    powercut \
    cheapdist \
    hip2b \
    casynth \
    ewham \
    stuck \
"

inherit cmake pkgconfig

#EXTRA_OECMAKE = " -DBUILD_GUI=OFF"

do_install () {
    # TODO: test other plugins in this package before install

    # Install stuck
    install -d ${D}/${LV2_DIR}/stuck.lv2
    cp ${WORKDIR}/build/src/stuck/stuck.so ${D}/${LV2_DIR}/stuck.lv2/
    cp ${S}/src/stuck/*.ttl ${D}/${LV2_DIR}/stuck.lv2/
    cp -r ${S}/src/stuck/modgui ${D}/${LV2_DIR}/stuck.lv2/

    # Install ewham
    install -d ${D}/${LV2_DIR}/ewham.lv2
    cp ${WORKDIR}/build/src/ewham/ewham.so ${D}/${LV2_DIR}/ewham.lv2/
    cp ${S}/src/ewham/*.ttl ${D}/${LV2_DIR}/ewham.lv2/
    cp -r ${S}/src/ewham/modgui ${D}/${LV2_DIR}/ewham.lv2/

    # Install mindi
    install -d ${D}/${LV2_DIR}/mindi.lv2
    cp ${WORKDIR}/build/src/mindi/mindi.so ${D}/${LV2_DIR}/mindi.lv2/
    cp ${S}/src/mindi/*.ttl ${D}/${LV2_DIR}/mindi.lv2/
    cp -r ${S}/src/mindi/modgui ${D}/${LV2_DIR}/mindi.lv2/

    chmod 755 -R ${D}/${LV2_DIR}
}

DEPENDS = "\
    lv2 \
    fftw \
    zita-resampler \
"

RDEPENDS:${PN} = "\
    libfftwf \
"

FILES:${PN} = "\
    ${LV2_DIR}/stuck.lv2 \
    ${LV2_DIR}/ewham.lv2 \
    ${LV2_DIR}/mindi.lv2 \
"

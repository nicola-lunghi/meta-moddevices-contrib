# Recipe to install fluidsynth-plug software
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

S = "${WORKDIR}/git"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/falkTX/FluidPlug.git;protocol=https;branch=master\
"
SRCREV="889b2568f5c4483b1c72b5d82e4090e27ef824de"

inherit pkgconfig

do_compile:prepend () {
    sed -i -- 's/$(PREFIX)\/lib\/lv2//' ${S}/Makefile
}

do_compile () {
    oe_runmake NOOPT=true
}

do_install () {
    install -d ${D}/${LV2_DIR}
    cp -r ${S}/*.lv2 ${D}/${LV2_DIR}
    chmod 755 -R ${D}/${LV2_DIR}
}

DEPENDS = " \
    fluidsynth \
    lv2 \
"

RDEPENDS:fluidsynth-plug = "\
    fluidsynth \
"

FILES:${PN} = "\
    ${LV2_DIR}/AirFont320.lv2/* \
    ${LV2_DIR}/AVL_Drumkits_Perc.lv2/* \
    ${LV2_DIR}/Black_Pearl_4A.lv2/* \
    ${LV2_DIR}/Black_Pearl_4B.lv2/* \
    ${LV2_DIR}/Black_Pearl_5.lv2/* \
    ${LV2_DIR}/FluidBass.lv2/* \
    ${LV2_DIR}/FluidBrass.lv2/* \
    ${LV2_DIR}/FluidChromPerc.lv2/* \
    ${LV2_DIR}/FluidDrums.lv2/* \
    ${LV2_DIR}/FluidEnsemble.lv2/* \
    ${LV2_DIR}/FluidEthnic.lv2/* \
    ${LV2_DIR}/FluidGM.lv2/* \
    ${LV2_DIR}/FluidGuitars.lv2/* \
    ${LV2_DIR}/FluidOrgans.lv2/* \
    ${LV2_DIR}/FluidPercussion.lv2/* \
    ${LV2_DIR}/FluidPianos.lv2/* \
    ${LV2_DIR}/FluidPipes.lv2/* \
    ${LV2_DIR}/FluidReeds.lv2/* \
    ${LV2_DIR}/FluidSoundFX.lv2/* \
    ${LV2_DIR}/FluidStrings.lv2/* \
    ${LV2_DIR}/FluidSynthFX.lv2/* \
    ${LV2_DIR}/FluidSynthLeads.lv2/* \
    ${LV2_DIR}/FluidSynthPads.lv2/* \
    ${LV2_DIR}/Red_Zeppelin_4.lv2/* \
    ${LV2_DIR}/Red_Zeppelin_5.lv2/* \
"


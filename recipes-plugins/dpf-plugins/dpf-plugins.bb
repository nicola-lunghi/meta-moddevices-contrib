SUMMARY = "Dpf lv2 plugin bundle"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/DISTRHO/DPF-Plugins.git;protocol=https;branch=master \
"
SRCREV="86084a934adb26f529038cbcf901fd7a09b95897"

S = "${WORKDIR}/git"

FXLIST = "\
    3BandEQ.lv2 \
    3BandSplitter.lv2 \
    AmplitudeImposer.lv2 \
    CycleShifter.lv2 \
    Kars.lv2 \
    MaBitcrush.lv2 \
    MaFreeverb.lv2 \
    MaGigaverb.lv2 \
    MaPitchshift.lv2 \
    MVerb.lv2 \
    Nekobi.lv2 \
    PingPongPan.lv2 \
    SoulForce.lv2 \
"

do_compile () {
    oe_runmake NOOPT=true HAVE_CAIRO=false HAVE_GL=false plugins
}

do_install () {
    for fx in ${FXLIST}; do
        install -d ${D}/${LV2_DIR}/${fx}
        cp -r ${S}/bin/${fx}/*.so ${D}/${LV2_DIR}/${fx}
    done
    chmod 755 -R ${D}/${LV2_DIR}
}

DEPENDS += " \
    lilv \
    jack \
"

FILES:${PN} = "\
    ${LV2_DIR}/3BandEQ.lv2/* \
    ${LV2_DIR}/3BandSplitter.lv2/* \
    ${LV2_DIR}/AmplitudeImposer.lv2/* \
    ${LV2_DIR}/CycleShifter.lv2/* \
    ${LV2_DIR}/Kars.lv2/* \
    ${LV2_DIR}/MaBitcrush.lv2/* \
    ${LV2_DIR}/MaFreeverb.lv2/* \
    ${LV2_DIR}/MaGigaverb.lv2/* \
    ${LV2_DIR}/MaPitchshift.lv2/* \
    ${LV2_DIR}/MVerb.lv2/* \
    ${LV2_DIR}/Nekobi.lv2/* \
    ${LV2_DIR}/PingPongPan.lv2/* \
    ${LV2_DIR}/SoulForce.lv2/* \
"

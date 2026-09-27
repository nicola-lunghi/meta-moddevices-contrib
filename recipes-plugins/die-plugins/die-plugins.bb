# LICENSE: the web graphics of distrho-a-fluidsynth.lv2 is provided by lv2-data-creative-commons
SUMMARY = "Die lv2 plugins"
DESCRIPTION = "This is a collection of plugins imported into the DISTRHO project"
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP:${PN} = "already-stripped"

SRC_URI = "\
    gitsm://github.com/DISTRHO/DIE-Plugins.git;protocol=https;branch=main \
"
SRCREV="d4c3922368710531b2adbebea292fd277cdc3ba0"

PV = "1.1"

S = "${WORKDIR}/git"

inherit pkgconfig

do_compile () {
    oe_runmake DISABLE_OPENMP=1
}

do_install () {
    # distrho-a-fluidsynth.lv2 graphics is installed by lv2-data-creative-commons recipe
    install -d ${D}${LV2_DIR}/distrho-a-fluidsynth.lv2
    cp -rL ${S}/bin/distrho-a-fluidsynth.lv2/*.so ${D}${LV2_DIR}/distrho-a-fluidsynth.lv2
}

DEPENDS += "\
    lv2 \
    jack \
    libsndfile1 \
    glib-2.0 \
"

FILES:${PN} = "\
    ${LV2_DIR} \
"

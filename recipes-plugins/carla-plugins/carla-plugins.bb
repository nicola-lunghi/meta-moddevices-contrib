SUMMARY = "Carla lv2 plugins"
DESCRIPTION = "Carla lv2 plugins contains audio and midi players"
SECTION = "lv2/stable"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README.md;md5=df509b3978de953a47be5c82c7727684"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
BUNDLEDIR = "${@bb.utils.contains('SECTION', 'lv2/stable', '${LV2_DIR}', '${LV2_DIR_BAD}', d)}"

INSANE_SKIP:${PN} = "already-stripped"

SRC_URI = "\
    gitsm://github.com/falkTX/Carla.git;protocol=https;branch=main \
"
SRCREV="51028655d09c9a21fc51cadd7bc48210295aa791"

PV = "2.4.1"

S = "${WORKDIR}/git"

inherit pkgconfig

CARLA_PLUGINS_NO_GUI = 'HAVE_FFMPEG=false HAVE_HYLIA=false HAVE_PYQT=false'

do_compile () {
    oe_runmake ${CARLA_PLUGINS_NO_GUI} NOOPT=true lv2-bundles
}

do_install () {
    install -d ${D}${BUNDLEDIR}/carla-files.lv2
    cp -rL ${S}/bin/carla-files.lv2/*.so ${D}${BUNDLEDIR}/carla-files.lv2

    # carla-files.lv2 ttls and modgui are installed from mod-lv2-data
    install -d ${D}/${BUNDLEDIR}/carla-files.lv2
    cp -r ${WORKDIR}/../../mod-lv2-data/*/git/plugins-fixed/carla-files.lv2/*.ttl ${D}/${BUNDLEDIR}/carla-files.lv2
    cp -r ${WORKDIR}/../../mod-lv2-data/*/git/plugins-fixed/carla-files.lv2/modgui ${D}/${BUNDLEDIR}/carla-files.lv2
}

DEPENDS += "\
    lv2 \
    jack \
    libsndfile1 \
    mod-lv2-data \
"

FILES:${PN} = "\
    ${LV2_DIR} \
"

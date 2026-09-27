# INFO: this plugin suite uses fftw single precision calls
SUMMARY = "Lv2 plugin x42-zconv"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
BUNDLEDIR = "${@bb.utils.contains('SECTION', 'lv2/stable', '${LV2_DIR}', '${LV2_DIR_BAD}', d)}"

INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/x42/zconvo.lv2.git;protocol=https;branch=master \
    file://01_skip-thread-safe-planner.patch \
    file://02_mod-tweaks.patch \
"
SRCREV="348f266330de493fae73520361af89c4826029c4"

PV = "0.6.5"

S = "${WORKDIR}/git"

inherit pkgconfig

CXXFLAGS:append = " -fno-finite-math-only -DNDEBUG -D_MOD_DEVICE_DWARF"

do_install () {
    cd ${S}
    oe_runmake install DESTDIR=${D} LV2DIR=${BUNDLEDIR} PREFIX=""

    # zeroconvo.lv2.lv2 ttls and modgui are installed from mod-lv2-data
    rm -rf ${D}${BUNDLEDIR}/zeroconvo.lv2/ir ${D}${BUNDLEDIR}/zeroconvo.lv2/*.ttl
    install -d ${D}/${BUNDLEDIR}/carla-files.lv2
    cp -r ${WORKDIR}/../../mod-lv2-data/*/git/plugins-fixed/zeroconvo.lv2/*.ttl ${D}/${BUNDLEDIR}/zeroconvo.lv2
    cp -r ${WORKDIR}/../../mod-lv2-data/*/git/plugins-fixed/zeroconvo.lv2/modgui ${D}/${BUNDLEDIR}/zeroconvo.lv2
}

DEPENDS += " \
    lv2 \
    fftw \
    libsndfile1 \
    libsamplerate0 \
    glib-2.0 \
    mod-lv2-data \
"

FILES:${PN} = "\
    ${LV2_DIR} \
"

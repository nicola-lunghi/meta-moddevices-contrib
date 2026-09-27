# INFO: guitarix uses fftw single precision calls in some plugins,
# while for other plugins using zita-convolver a version based on ffmpeg instead of fftw is used
SUMMARY = "Guitarix lv2 plugins"
DESCRIPTION = "Recipe to install lv2 plugins contained in Guitarix"
HOMEPAGE = "http://guitarix.org/"
SECTION = "lv2/stable"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
BUNDLEDIR = "${@bb.utils.contains('SECTION', 'lv2/stable', '${LV2_DIR}', '${LV2_DIR_BAD}', d)}"

SRC_URI = "\
    git://github.com/brummer10/guitarix.git;protocol=https;branch=master \
    file://0001-Rework-messages-somehow-yes-or-no-is-missing.patch \
    file://0002-Do-not-strip-LV2-plugins.patch \
    file://0003-Use-avail-libgmm-version.patch \
"
SRCREV = "a14b455d4d258917a597456c674b6f80470e1d80"
PV = "0.42.1"
S = "${WORKDIR}/git/trunk"

WEBGUI = "\
    gxtuner.lv2 \
"

WEBGUICC = "\
    ${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "commercial", "gx_shimmizita.lv2", "", d)} \
"

inherit waf pkgconfig

EXTRA_OECONF = " \
    --disable-sse \
    --cxxflags="${CFLAGS}" \
    --ldflags="${LDFLAGS}" \
    --includeconvolver \
    --includeresampler \
    --no-ldconfig \
    --destdir=${D} \
    --no-standalone \
    --no-desktop-update \
    --no-faust \
    --no-lv2-gui \
    --mod-lv2 \
    --lv2dir=${BUNDLEDIR} \
"

do_install:append () {
    for fx in ${WEBGUI}; do
        rm -rf ${D}${BUNDLEDIR}/${fx}/*.ttl
        cp -rL ${WORKDIR}/../../mod-lv2-data/*/git/plugins/${fx}/*.ttl ${D}/${BUNDLEDIR}/${fx}
    done

    # gx_shimmizita.lv2 graphics is installed by lv2-data-creative-commons recipe
    for fx in ${WEBGUICC}; do
        rm -rf ${D}${BUNDLEDIR}/${fx}/*.ttl
        cp -rL ${WORKDIR}/../../lv2-data-creative-commons/*/git/plugin-data/${fx}/*.ttl ${D}/${BUNDLEDIR}/${fx}
        cp -rL ${WORKDIR}/../../lv2-data-creative-commons/*/git/plugin-data/${fx}/modgui ${D}/${BUNDLEDIR}/${fx}
    done
}

DEPENDS = " \
    boost \
    libeigen \
    fftw \
    glibmm \
    lilv \
    jack \
    mod-lv2-data \
    ${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "commercial", "lv2-data-creative-commons", "", d)} \
"

FILES:${PN} += "\
    ${LV2_DIR} \
"

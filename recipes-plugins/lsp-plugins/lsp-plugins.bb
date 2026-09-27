# Recipe to install LSP (Linux Studio Plugins)
SUMMARY = "LSP (Linux Studio Plugins)"
DESCRIPTION = "LSP (Linux Studio Plugins) is a collection of open-source plugins currently compatible with LADSPA, LV2 and LinuxVST formats."
SECTION = "lv2/stable"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3000208d539ec061b899bce1d9ce9404"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
BUNDLEDIR = "${@bb.utils.contains('SECTION', 'lv2/stable', '${LV2_DIR}', '${LV2_DIR_BAD}', d)}"

INSANE_SKIP:${PN} = "ldflags"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/sadko4u/lsp-plugins.git;protocol=https;branch=master \
    file://change-lv2path-behaviour.patch \
    file://ttl.tar.gz \
    file://lsp-plugins-impulse-responses.lv2/impulse_responses_mono.ttl \
    file://lsp-plugins-impulse-responses.lv2/impulse_responses_stereo.ttl \
    file://lsp-plugins-impulse-responses.lv2/manifest.ttl \
    file://lsp-plugins-impulse-responses.lv2/modgui_mono.ttl \
    file://lsp-plugins-impulse-responses.lv2/modgui_stereo.ttl \
    file://lsp-plugins-impulse-responses.lv2/modgui \
"
SRCREV="5ea0b02e08595a2fd2e29a1e0a2acf7189cce47a"

S = "${WORKDIR}/git"

PV = "1.1.31"

inherit pkgconfig

EXTRA_OEMAKE = "BUILD_MODULES=lv2 LV2_UI=0 BUILD_PLATFORM=Linux BUILD_PROFILE=aarch64 BUILD_R3D_BACKENDS= XLIB_HEADERS= XLIB_LIBS="

do_compile () {
    oe_runmake
}

do_install () {
    # Not possible to use make install since lv2_genttl.exe is compiled for target as well
    # so I've generated ttl files from a desktop install and provide them in a tar.gz
    #oe_runmake install DESTDIR=${D} PREFIX= LV2PATH=${BUNDLEDIR}

    # Install binary
    install -d ${D}/${BUNDLEDIR}/${PN}.lv2
    cp ${S}/.build/lsp-plugins-lv2.so ${D}/${BUNDLEDIR}/${PN}.lv2/

    # Install .ttl
    cp ${WORKDIR}/ttl/*.ttl ${D}/${BUNDLEDIR}/${PN}.lv2/
    mv ${D}/${BUNDLEDIR}/${PN}.lv2/manifest.ttl ${D}/${BUNDLEDIR}/${PN}.lv2/manifest.ttl.original

    # Overwrite with custom version, containing only impulse responses loader mono/stereo
    cp ${WORKDIR}/lsp-plugins-impulse-responses.lv2/*.ttl ${D}/${BUNDLEDIR}/${PN}.lv2/
    cp -r ${WORKDIR}/lsp-plugins-impulse-responses.lv2/modgui ${D}/${BUNDLEDIR}/${PN}.lv2/

    chmod 755 -R ${D}/${BUNDLEDIR}
}

DEPENDS += "\
    jack \
    lv2 \
    fftw \
    libsndfile1 \
    libsamplerate0 \
"

FILES:${PN} = "\
    ${BUNDLEDIR} \
"

# INFO: this plugin suite uses fftw single precision calls (see patch below)
SUMMARY = "Zynaddsubfx lv2 plugins"
DESCRIPTION = ""
HOMEPAGE = "http://zynaddsubfx.sourceforge.net"
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

INSANE_SKIP:${PN} = "already-stripped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://git.code.sf.net/p/zynaddsubfx/code;protocol=https;branch=master \
    file://01_no-asm.patch \
    file://02_tweak-global-settings.patch \
    file://03_no-cmake-opts.patch \
    file://04_no-async.patch \
    file://06_no-notepool-assert.patch \
    file://07_no-fft-mutex.patch \
    file://08_use-fftwf.patch \
    file://09_more-tweaks.patch \
    file://10_fix-build.patch \
    file://11_mod-path-tweaks.patch \
    file://12_no-ttl-generator.patch \
"
SRCREV = "4d4aedf834dbd13c6e5f07ac512c9da74732fd58"

PV = "3.0.5"

S = "${WORKDIR}/git"

inherit cmake

FXLIST = "\
    AddSubFX \
    AlienWah \
    Chorus \
    Distortion \
    DynamicFilter \
    Echo \
    Phaser \
    Reverb \
"

do_install () {
    for fx in ${FXLIST}; do
        install -d ${D}/${LV2_DIR}/Zyn${fx}.lv2
        if [ ${fx} = "AddSubFX" ]; then
            cp -r ${WORKDIR}/build/src/Plugin/Zyn${fx}/lv2/*.so ${D}/${LV2_DIR}/Zyn${fx}.lv2
        else
            cp -r ${WORKDIR}/build/src/Plugin/${fx}/lv2/*.so ${D}/${LV2_DIR}/Zyn${fx}.lv2
        fi
    done
    chmod 755 -R ${D}/${LV2_DIR}/
}

DEPENDS = "\
    mxml \
    fftw \
    liblo \
    zlib \
    lilv \
    jack \
"

RDEPENDS:zynaddsubfx = "\
"

FILES:${PN} = "\
    ${LV2_DIR}/ZynAddSubFX.lv2/* \
    ${LV2_DIR}/ZynAlienWah.lv2/* \
    ${LV2_DIR}/ZynChorus.lv2/* \
    ${LV2_DIR}/ZynDistortion.lv2/* \
    ${LV2_DIR}/ZynDynamicFilter.lv2/* \
    ${LV2_DIR}/ZynEcho.lv2/* \
    ${LV2_DIR}/ZynPhaser.lv2/* \
    ${LV2_DIR}/ZynReverb.lv2/* \
"

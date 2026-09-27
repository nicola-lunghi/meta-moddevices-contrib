SUMMARY = "Distrho-ports lv2 plugin bundle v6"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/DISTRHO/DISTRHO-Ports.git;protocol=https;branch=master \
    file://01_no-lv2-gen.patch \
"
SRCREV="9f93a1966f70d65ad661ce0aeaf70305bc2d9d35"

S = "${WORKDIR}/git"

inherit meson

FXLIST = "\
    SwankyAmp.lv2 \
    vitalium.lv2 \
"

EXTRA_OEMESON:append = "\
    --buildtype release --prefix /usr \
    -Dlinux-embed=true \
    -Doptimizations=false \
    -Dbuild-lv2=true \
    -Dbuild-vst2=false \
    -Dbuild-vst3=false \
    -Dplugins=swankyamp,vitalium \
"

do_install () {
    for fx in ${FXLIST}; do
        install -d ${D}/${LV2_DIR}/${fx}
        cp -r ${B}/ports/${fx}/*.so ${D}/${LV2_DIR}/${fx}
    done
    chmod 755 -R ${D}/${LV2_DIR}
}

DEPENDS += " \
    lilv \
    jack \
    fftw \
    freetype \
"

FILES:${PN} = "\
    ${LV2_DIR}/SwankyAmp.lv2/* \
    ${LV2_DIR}/vitalium.lv2/* \
"

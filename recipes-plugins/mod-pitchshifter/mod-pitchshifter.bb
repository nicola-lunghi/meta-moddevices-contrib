# TO BE FIXED!!!
# Recipe to install mod-pitchshifter lv2 plugin
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

S = "${WORKDIR}/git"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/mod-pitchshifter.git;protocol=https;branch=master \
"
SRCREV="4fabd8994c8dff40886198d9641c1b734655602d"

FXLIST = "\
    2Voices \
    Capo \
    Drop \
    SuperCapo \
    SuperWhammy \
    Harmonizer \
    Harmonizer2 \
    HarmonizerCS \
"

EXTRA_OEMAKE = "NOOPT=true"

do_install () {
    install -d ${D}/${LV2_DIR}
    for fx in ${FXLIST}; do
        install -d ${D}/${LV2_DIR}/mod-${fx}.lv2
        cp -r ${S}/${fx}/*.so ${D}/${LV2_DIR}/mod-${fx}.lv2
        cp -r ${S}/${fx}/ttl/*.ttl ${D}/${LV2_DIR}/mod-${fx}.lv2
        cp -r ${S}/${fx}/ttl/modgui ${D}/${LV2_DIR}/mod-${fx}.lv2
        cp -r ${S}/Shared_files/harmonizer.wisdom ${D}/${LV2_DIR}/mod-${fx}.lv2
    done
    chmod 755 -R ${D}/${LV2_DIR}
}

DEPENDS = "\
    armadillo \
    fftw-native \
    fftw \
    lv2 \
"

RDEPENDS:mod-pitchshifter = "\
"

FILES:${PN} = "\
    ${LV2_DIR} \
"


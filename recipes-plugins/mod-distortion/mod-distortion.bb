SUMMARY = "Mod-distortion lv2 plugin suite"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

S = "${WORKDIR}/git"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/mod-distortion.git;protocol=https;branch=master \
"
SRCREV="e672d5feb9d631798e3d56eb96e8958c3d2c6821"

FXLIST = "\
    bigmuff \
    ds1 \
"
# TODO:
#FXLIST = "\
#    bigmuff \
#    ds1 \
#    guitarix-Overdrive \
#    mufffuzz \
#"

do_install () {
    for fx in ${FXLIST}; do
        install -d ${D}/${LV2_DIR}/mod-${fx}.lv2
        cp -r ${S}/${fx}/*.so ${D}/${LV2_DIR}/mod-${fx}.lv2
        cp -r ${S}/${fx}/ttl/*.ttl ${D}/${LV2_DIR}/mod-${fx}.lv2
        cp -r ${S}/${fx}/ttl/modgui ${D}/${LV2_DIR}/mod-${fx}.lv2
    done

    chmod 755 -R ${D}/${LV2_DIR}
}

DEPENDS = " \
    lilv \
    jack \
"

RDEPENDS:mod-distortion = "\
"

FILES:${PN} = "\
    ${LV2_DIR} \
"


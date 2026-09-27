# Recipe to install shiro lv2 plugins
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/ninodewit/SHIRO-Plugins.git;protocol=https;branch=master \
"
SRCREV="60a678ca6abdb7fa10b0ac8dd87e0e41052abe78"

S = "${WORKDIR}/git"

FXLIST = "\
    Harmless.lv2 \
    Larynx.lv2 \
    Modulay.lv2 \
    Pitchotto.lv2 \
    Shiroverb.lv2 \
"

do_compile () {
    oe_runmake NOOPT=true plugins
}

do_install () {
    for fx in ${FXLIST}; do
        install -d ${D}/${LV2_DIR}/${fx}
        cp -r ${S}/bin/${fx}/*.so ${D}/${LV2_DIR}/${fx}
    done
    chmod 755 -R ${D}/${LV2_DIR}
}

DEPENDS += "\
    lilv \
    jack \
"

FILES:${PN} = "\
    ${LV2_DIR}/Harmless.lv2/* \
    ${LV2_DIR}/Larynx.lv2/* \
    ${LV2_DIR}/Modulay.lv2/* \
    ${LV2_DIR}/Pitchotto.lv2/* \
    ${LV2_DIR}/Shiroverb.lv2/* \
"

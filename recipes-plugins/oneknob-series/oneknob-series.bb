SUMMARY = "DISTRHO OneKnob Series"
DESCRIPTION = "A collection of stupidly simple but well-polished, pleasent and functional audio plugins with as little controls as possible."
HOMEPAGE = "https://github.com/DISTRHO/OneKnob-Series.git"
SECTION = "lv2/stable"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
BUNDLEDIR = "${@bb.utils.contains('SECTION', 'lv2/stable', '${LV2_DIR}', '${LV2_DIR_BAD}', d)}"

INSANE_SKIP:${PN} += "already-stripped"

FXLIST = "\
    MOD-ConvolutionLoader.lv2 \
"

SRC_URI = "\
    gitsm://github.com/DISTRHO/OneKnob-Series.git;protocol=https;branch=main \
    file://conv-fix-crash-short-irs.patch \
"
SRCREV = "d3e790f0030a3999b72d89d95bcedcdca851bea4"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_OEMAKE += 'CROSS_COMPILING=true MOD_BUILD=true NOOPT=true WITH_LTO=true -C .'
CFLAGS:append = " -D__MOD_DEVICES__ -D_MOD_DEVICE_DWARF"
CXXFLAGS:append = " -D__MOD_DEVICES__ -D_MOD_DEVICE_DWARF"

do_install () {
    for fx in ${FXLIST}; do
        install -d ${D}/${BUNDLEDIR}/${fx}
        cp -r ${S}/bin/${fx}/*.so ${D}/${BUNDLEDIR}/${fx}
    done

    # carla-files.lv2 ttls and modgui are installed from mod-lv2-data
    for fx in ${FXLIST}; do
        cp -rL ${WORKDIR}/../../mod-lv2-data/*/git/plugins-fixed/${fx}/*.ttl ${D}/${BUNDLEDIR}/${fx}
        cp -rL ${WORKDIR}/../../mod-lv2-data/*/git/plugins-fixed/${fx}/modgui ${D}/${BUNDLEDIR}/${fx}
    done
}

DEPENDS += "\
    lv2 \
    mod-lv2-data \
"

FILES:${PN} = "\
    ${BUNDLEDIR} \
"

SUMMARY = "Dragonfly Reverb lv2 plugin"
DESCRIPTION = "Dragonfly Reverb is a bundle of free audio effects for Linux, MacOS, and Windows."
HOMEPAGE = "https://github.com/michaelwillis/dragonfly-reverb.git"
SECTION = "lv2/stable"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"
BUNDLEDIR = "${@bb.utils.contains('SECTION', 'lv2/stable', '${LV2_DIR}', '${LV2_DIR_BAD}', d)}"

INSANE_SKIP:${PN} += "already-stripped"

FXLIST = "\
    dragonfly-early-reflections \
    dragonfly-hall-reverb \
    dragonfly-plate-reverb \
    dragonfly-room-reverb \
"

BINLIST = "\
    DragonflyRoomReverb.lv2 \
    DragonflyPlateReverb.lv2 \
    DragonflyHallReverb.lv2 \
    DragonflyEarlyReflections.lv2 \
"

# @TODO: gitsm doesn't work for this recipe
SRC_URI = "\
    git://github.com/michaelwillis/dragonfly-reverb.git;protocol=https;branch=master \
    file://01_optimizations.patch \
    file://02_allow-zero-size-delay.patch \
"
SRCREV = "582b9c65248023f710ae3245951fff678d38c3a2"

S = "${WORKDIR}/git"

inherit pkgconfig

do_configure:prepend () {
    bbwarn Fetching submodules in do_configure:prepend, fixme
    cd ${S}
    git submodule update --init --recursive
}

do_compile () {
    for fx in ${FXLIST}; do
        bbdebug 1 "Building ${fx}..."
        cd ${S}/plugins/${fx}
        oe_runmake 'NOOPT=true lv2_dsp -C .'
    done
}

do_install () {
    for fx in ${BINLIST}; do
        install -d ${D}/${BUNDLEDIR}/${fx}
        cp -r ${S}/bin/${fx}/*.so ${D}/${BUNDLEDIR}/${fx}
    done

    # Ttls and modgui are installed from mod-lv2-data
    for fx in ${BINLIST}; do
        cp -r ${WORKDIR}/../../mod-lv2-data/*/git/plugins-fixed/${fx}/*.ttl ${D}/${BUNDLEDIR}/${fx}
        cp -r ${WORKDIR}/../../mod-lv2-data/*/git/plugins-fixed/${fx}/modgui ${D}/${BUNDLEDIR}/${fx}
    done
}

DEPENDS += "\
    lv2 \
    mod-lv2-data \
"

FILES:${PN} = "\
    ${BUNDLEDIR} \
"

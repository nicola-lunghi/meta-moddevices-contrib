SUMMARY = "A set of extra lv2 plugins from the guitarix project"
DESCRIPTION = ""
HOMEPAGE = "https://github.com/brummer10/GxPlugins.lv2"
SECTION = "lv2/stable"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"
BUNDLEDIR = "${@bb.utils.contains('SECTION', 'lv2/stable', '${LV2_DIR}', '${LV2_DIR_BAD}', d)}"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

SRC_URI = "\
    gitsm://github.com/brummer10/GxPlugins.lv2.git;protocol=https;branch=master \
"
SRCREV = "e40b34f3fd5dc4c6523dc826062d0ddb2578f573"
PV = "0.8"
S = "${WORKDIR}/git"

FXLIST = "\
    GxAxisFace.lv2 \
    GxBaJaTubeDriver.lv2 \
    GxBlueAmp.lv2 \
    GxBoobTube.lv2 \
    GxBottleRocket.lv2 \
    GxClubDrive.lv2 \
    GxCreamMachine.lv2 \
    GxDOP250.lv2 \
    GxEpic.lv2 \
    GxEternity.lv2 \
    GxFz1b.lv2 \
    GxFz1s.lv2 \
    GxGuvnor.lv2 \
    GxHeathkit.lv2 \
    GxHotBox.lv2 \
    GxHyperion.lv2 \
    GxKnightFuzz.lv2 \
    GxLiquidDrive.lv2 \
    GxLuna.lv2 \
    GxMicroAmp.lv2 \
    GxPlexi.lv2 \
    GxQuack.lv2 \
    GxSaturator.lv2 \
    GxSD1.lv2 \
    GxSD2Lead.lv2 \
    GxShakaTube.lv2 \
    GxSloopyBlue.lv2 \
    GxSlowGear.lv2 \
    GxSunFace.lv2 \
    GxSuperFuzz.lv2 \
    GxSupersonic.lv2 \
    GxSuppaToneBender.lv2 \
    GxSVT.lv2 \
    GxTimRay.lv2 \
    GxToneMachine.lv2 \
    GxTubeDistortion.lv2 \
    GxUltraCab.lv2 \
    GxUVox720k.lv2 \
    GxValveCaster.lv2 \
    GxVBassPreAmp.lv2 \
    GxVintageFuzzMaster.lv2 \
    GxVmk2.lv2 \
    GxVoodoFuzz.lv2 \
"

# This is required since different naming between this repo
# and modgui graphics repo. Keep this list in sync with WEBGUICC.
FXLIST:remove = "\
    ${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "commercial", "GxSupersonic.lv2", "", d)} \
"

WEBGUICC = "\
    ${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "commercial", "gx_supersonic.lv2", "", d)} \
"

do_compile () {
    for fx in ${FXLIST}; do
        bbdebug 1 "Building ${fx}..."
        cd ${fx}
        sed -i -- 's/-msse3 -mfpmath=sse//' ./Makefile
        oe_runmake nogui
        cd ..
    done
}

do_install () {
    for fx in ${FXLIST}; do
        install -d ${D}/${BUNDLEDIR}/${fx}
        cp -r ${S}/${fx}/*.so ${D}/${BUNDLEDIR}/${fx}
        cp -rL ${S}/${fx}/MOD/* ${D}/${LV2_DIR}/${fx}
    done

    # The web graphics of GxSupersonic.lv2 is optionally provided by lv2-data-creative-commons under gx_supersonic.lv2
    for fx in ${WEBGUICC}; do
        install -d ${D}/${BUNDLEDIR}/${fx}
        cp -rL ${WORKDIR}/../../lv2-data-creative-commons/*/git/plugin-data/${fx}/*.ttl ${D}/${BUNDLEDIR}/${fx}
        cp -rL ${WORKDIR}/../../lv2-data-creative-commons/*/git/plugin-data/${fx}/modgui ${D}/${BUNDLEDIR}/${fx}
    done
}

DEPENDS = " \
    lilv \
    ${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "commercial", "lv2-data-creative-commons", "", d)} \
"

FILES:${PN} = "\
    ${LV2_DIR} \
"

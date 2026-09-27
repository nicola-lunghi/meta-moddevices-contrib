# ATTENTION: in order to compile this package, a manual action is required: the
# package generates Makefiles from a premake script and doesn't work within yocto.
# The patch provided below is not compatible with yocto.
# Please see https://github.com/DISTRHO/DISTRHO-Ports/issues/19
# Instructions
# build the package manually:
# bitbake distrho-ports -c do_cleanall
# bitbake distrho-ports -c do_patch
# now open a linux shell:
# cd tmp/work/aarch64-poky-linux/distrho-ports/1.0-r0/git
# export LINUX_EMBED=1
# ./scripts/premake-update.sh linux
# now return on poky shell and finish compilation and installation
# bitbake distrho-ports
SUMMARY = "Distrho-ports lv2 plugin bundle"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/DISTRHO/DISTRHO-Ports.git;protocol=https;branch=legacy \
    file://01_workaround-missing-premake.patch \
"
SRCREV="d19e3eb1b79df52250e233329e3d31cdbc922cb4"

S = "${WORKDIR}/git"

# Below list is considered unstable aka "labs" or "bad"
FXLIST = "\
    drowaudio-distortion.lv2 \
    drowaudio-distortionshaper.lv2 \
    drowaudio-flanger.lv2 \
    drowaudio-reverb.lv2 \
    drowaudio-tremolo.lv2 \
    Luftikus.lv2 \
    Obxd.lv2 \
    TAL-Dub-3.lv2 \
    TAL-Filter-2.lv2 \
    TAL-Filter.lv2 \
    TAL-NoiseMaker.lv2 \
    TAL-Reverb-2.lv2 \
    TAL-Reverb-3.lv2 \
    TAL-Reverb.lv2 \
    TAL-Vocoder-2.lv2 \
    TheFunction.lv2 \
    ThePilgrim.lv2 \
    Temper.lv2 \
    Wolpertinger.lv2 \
"

# Following list is considered stable
FXLIST += "\
    Vex.lv2 \
"

EXTRA_OEMAKE = "lv2_nogen LINUX_EMBED=true"

do_compile () {
    oe_runmake
}

do_install () {
    # distrho-ports plugins graphics is installed by mod-lv2-data recipe
    for fx in ${FXLIST}; do
        install -d ${D}/${LV2_DIR}/${fx}
        cp -r ${S}/bin/lv2/${fx}/*.so ${D}/${LV2_DIR}/${fx}
    done
    chmod 755 -R ${D}/${LV2_DIR}
}

DEPENDS += " \
    lilv \
    jack \
"

FILES:${PN} = "\
    ${LV2_DIR}/drowaudio-distortion.lv2/* \
    ${LV2_DIR}/drowaudio-distortionshaper.lv2/* \
    ${LV2_DIR}/drowaudio-flanger.lv2/* \
    ${LV2_DIR}/drowaudio-reverb.lv2/* \
    ${LV2_DIR}/drowaudio-tremolo.lv2/* \
    ${LV2_DIR}/Luftikus.lv2/* \
    ${LV2_DIR}/Obxd.lv2/* \
    ${LV2_DIR}/TAL-Dub-3.lv2/* \
    ${LV2_DIR}/TAL-Filter-2.lv2/* \
    ${LV2_DIR}/TAL-Filter.lv2/* \
    ${LV2_DIR}/TAL-NoiseMaker.lv2/* \
    ${LV2_DIR}/TAL-Reverb-2.lv2/* \
    ${LV2_DIR}/TAL-Reverb-3.lv2/* \
    ${LV2_DIR}/TAL-Reverb.lv2/* \
    ${LV2_DIR}/TAL-Vocoder-2.lv2/* \
    ${LV2_DIR}/TheFunction.lv2/* \
    ${LV2_DIR}/ThePilgrim.lv2/* \
    ${LV2_DIR}/Temper.lv2/* \
    ${LV2_DIR}/Wolpertinger.lv2/* \
    ${LV2_DIR}/Vex.lv2/* \
"
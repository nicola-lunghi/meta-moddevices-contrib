SUMMARY = "Amsynth lv2 plugin"
DESCRIPTION = "The AmSynth is a very big synth that might look quite difficult at first. However it is a synth with a classic subtractive synthesizer topology"
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PV = "1.6.3"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    https://github.com/amsynth/amsynth/releases/download/release-${PV}/amsynth-${PV}.tar.gz \
    file://01_no-gtk-ui.patch \
"
SRC_URI[md5sum] = "8704d7db65c21d24e40cbe249d523e60"
SRC_URI[sha256sum] = "04a50fc55a059cefb9764c4ce6ec547e6c8b6aaa60db0bed25fa5ca061351b94"

S = "${WORKDIR}/amsynth-${PV}"

inherit autotools pkgconfig

EXTRA_OECONF = "--with-gui=no"
EXTRA_OECONF += "--with-oss=no"
EXTRA_OECONF += "--with-alsa=no"
EXTRA_OECONF += "--with-jack=no"
EXTRA_OECONF += "--with-lash=no"
EXTRA_OECONF += "--with-sndfile=no"
EXTRA_OECONF += "--with-dssi=no"
EXTRA_OECONF += "--with-vst=no"
EXTRA_OECONF += "-with-lv2=yes"

do_install () {
    install -d ${D}/${LV2_DIR}/amsynth.lv2
    cp -r ${WORKDIR}/build/.libs/*.so ${D}/${LV2_DIR}/amsynth.lv2
}

DEPENDS = "\
    lv2 \
"

RDEPENDS:${PN} = "\
"

FILES:${PN} = "\
    ${LV2_DIR}/amsynth.lv2 \
"

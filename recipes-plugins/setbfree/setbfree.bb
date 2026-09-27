# Recipe to install setBfree software
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

#INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/pantherb/setBfree.git;protocol=https;branch=master \
"
SRCREV="93e7f154bee67590d6d321a572a1b107f8fc36e1"

inherit pkgconfig

S = "${WORKDIR}/git"

do_configure:prepend () {
    sed -i -- 's/-msse -msse2 -mfpmath=sse//' ${S}/common.mak
    sed -i -- 's/^lv2dir = \$(PREFIX)\/lib\/lv2/lv2dir = \/\/home\/root\/.lv2/' ${S}/common.mak
}

do_install () {
    install -d ${D}/${LV2_DIR}/b_synth
    cp -r ${S}/b_synth/b_synth.so ${D}/${LV2_DIR}/b_synth

    install -d ${D}/${LV2_DIR}/b_overdrive
    cp -r ${S}/b_overdrive/b_overdrive.so ${D}/${LV2_DIR}/b_overdrive

    install -d ${D}/${LV2_DIR}/b_reverb
    cp -r ${S}/b_reverb/b_reverb.so ${D}/${LV2_DIR}/b_reverb

    install -d ${D}/${LV2_DIR}/b_whirl
    cp -r ${S}/b_whirl/b_whirl.so ${D}/${LV2_DIR}/b_whirl

    chmod 755 -R ${D}/${LV2_DIR}/
}

DEPENDS += " \
    lilv \
    jack \
"

FILES:${PN} = "\
    ${LV2_DIR}/b_overdrive/b_overdrive.so \
    ${LV2_DIR}/b_reverb/b_reverb.so \
    ${LV2_DIR}/b_synth/b_synth.so \
    ${LV2_DIR}/b_whirl/b_whirl.so \
"

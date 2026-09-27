SUMMARY = "CAPS lv2 plugin suite"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP:${PN} = "already-stripped"

S = "${WORKDIR}/git"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/caps-lv2.git;protocol=https;branch=master \
"
SRCREV="b830e2f8e46c2bf860ae77bdf86c57dfc9d977f9"

FXLIST = "\
    mod-caps-AmpVTS.lv2 \
    mod-caps-AutoFilter.lv2 \
    mod-caps-CabinetIII.lv2 \
    mod-caps-CabinetIV.lv2 \
    mod-caps-CEO.lv2 \
    mod-caps-ChorusI.lv2 \
    mod-caps-Click.lv2 \
    mod-caps-Compress.lv2 \
    mod-caps-CompressX2.lv2 \
    mod-caps-Eq10.lv2 \
    mod-caps-Eq10X2.lv2 \
    mod-caps-Eq4p.lv2 \
    mod-caps-EqFA4p.lv2 \
    mod-caps-Fractal.lv2 \
    mod-caps-Narrower.lv2 \
    mod-caps-Noisegate.lv2 \
    mod-caps-PhaserII.lv2 \
    mod-caps-Plate.lv2 \
    mod-caps-PlateX2.lv2 \
    mod-caps-Saturate.lv2 \
    mod-caps-Scape.lv2 \
    mod-caps-Sin.lv2 \
    mod-caps-Spice.lv2 \
    mod-caps-SpiceX2.lv2 \
    mod-caps-ToneStack.lv2 \
    mod-caps-White.lv2 \
    mod-caps-Wider.lv2 \
"

do_compile () {
    # Disabling ffast-math in Makefiles
    for fx in ${FXLIST}; do
        sed -i -- 's/ -ffast-math//' ${S}/plugins/${fx}/Makefile
    done
    oe_runmake NOOPT=true
}

do_install () {
    for fx in ${FXLIST}; do
        install -d ${D}/${LV2_DIR}/${fx}
        cp -r ${S}/plugins/${fx}/*.so ${D}/${LV2_DIR}/${fx}
        cp -r ${S}/plugins/${fx}/*.ttl ${D}/${LV2_DIR}/${fx}
        cp -r ${S}/plugins/${fx}/modgui ${D}/${LV2_DIR}/${fx}
    done
    chmod 755 -R ${D}/${LV2_DIR}/
}

DEPENDS = " \
    lv2 \
"

FILES:${PN} = "\
    ${LV2_DIR} \
"

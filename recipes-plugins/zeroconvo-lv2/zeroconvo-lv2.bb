# INFO: this plugin suite uses fftw single precision calls
SUMMARY = "Zeroconvo lv2 plugin"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://gareus.org/zeroconvo.lv2;protocol=https;branch=master \
"
SRCREV="9a056680a7d460ec93ec5ff355812586513ed2ea"

PV = "0.5.2"

S = "${WORKDIR}/git"

inherit pkgconfig

#CXXFLAGS:append = " -ffast-math -fomit-frame-pointer -O3 -fno-finite-math-only -DNDEBUG"
CXXFLAGS:append = " -fno-finite-math-only -DNDEBUG"

# Extra IR files not present in source code
ZEROCONVO_IRS_TARBALL = "sisel4-ir.tar.xz"
ZEROCONVO_IRS_URL = "https://x42-plugins.com/tmp/${ZEROCONVO_IRS_TARBALL}"

do_install:prepend () {
    cd ${WORKDIR}
    wget ${ZEROCONVO_IRS_URL}
    tar xf ${ZEROCONVO_IRS_TARBALL}
    mv sisel4-ir.lv2/*.wav ${S}/build/ir/
    cat sisel4-ir.lv2/manifest.ttl | tail -n +7 >> ${S}/build/manifest.ttl
    cat sisel4-ir.lv2/presets.ttl | tail -n +10 >> ${S}/build/presets.ttl
}

do_install () {
    cd ${S}
    oe_runmake install DESTDIR=${D} LV2DIR=${LV2_DIR}

    # @TODO: fix ttl files
    mv ${D}${LV2_DIR}/zeroconvo.lv2/ir/*.wav ${D}${LV2_DIR}/zeroconvo.lv2
    mv ${D}${LV2_DIR}/zeroconvo.lv2/delta-48k.wav ${D}${LV2_DIR}/zeroconvo.lv2/ir
}

DEPENDS += "\
    lv2 \
    fftw \
    libsndfile1 \
    libsamplerate0 \
    glib-2.0 \
"

FILES:${PN} = "\
    ${LV2_DIR} \
"

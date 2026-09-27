SUMMARY = "Tap-lv2 plugin suite"
DESCRIPTION = ""
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

S = "${WORKDIR}/git"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/tap-lv2.git;protocol=https;branch=master \
    file://fix-code-indentation.patch \
"
SRCREV="de26a3c8c8c2227e6d7fba3dcb54ec5fe2def258"

FXLIST = "\
    autopan \
    chorusflanger \
    deesser \
    doubler \
    dynamics-st \
    dynamics \
    echo \
    eq \
    eqbw \
    limiter \
    pinknoise \
    pitch \
    reflector \
    reverb \
    rotspeak \
    sigmoid \
    tremolo \
    tubewarmth \
    vibrato \
"

do_compile:prepend () {
    sed -i -- 's/-mtune=generic -msse -msse2 -mfpmath=sse//' ${S}/Makefile.mk
}

do_install () {
    for fx in ${FXLIST}; do
        install -d ${D}/${LV2_DIR}/${fx}.lv2
        cp -r ${S}/${fx}/*.so ${D}/${LV2_DIR}/${fx}.lv2
        cp -r ${S}/${fx}/*.ttl ${D}/${LV2_DIR}/${fx}.lv2
        cp -r ${S}/${fx}/modgui ${D}/${LV2_DIR}/${fx}.lv2
    done
    chmod 755 -R ${D}/${LV2_DIR}/
}

DEPENDS = "\
    lilv \
    jack \
"

RDEPENDS:tap-lv2 = "\
"

FILES:${PN} = "\
    ${LV2_DIR} \
"

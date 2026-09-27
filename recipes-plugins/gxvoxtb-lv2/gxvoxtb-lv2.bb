# Recipe to install gx_voxtb lv2 plugin
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

S = "${WORKDIR}/git"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/gx_voxtb.lv2.git;protocol=https;branch=master \
"
SRCREV = "54a9acc8fe85212542c1cb4088338d3627788139"

do_compile:prepend () {
    sed -i -- 's/-msse2 -mfpmath=sse//' ${S}/Makefile
}

do_install () {
    install -d ${D}/${LV2_DIR}/gx_voxtb.lv2
    cp -r ${S}/gx_voxtb.lv2/* ${D}/${LV2_DIR}/gx_voxtb.lv2
    chmod 755 -R ${D}/${LV2_DIR}/
}

DEPENDS = "\
    lilv \
"

RDEPENDS:${PN} = "\
"

FILES:${PN} = "\
    ${LV2_DIR} \
"


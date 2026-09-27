# Recipe to install GxSwitchlessWah lv2 plugin
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

S = "${WORKDIR}/git"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/GxSwitchlessWah.lv2.git;protocol=https;branch=master \
"
SRCREV = "5d2fc315c85ac38e90ebe079968d45b805000fd2"

do_compile:prepend () {
    sed -i -- 's/-msse2 -mfpmath=sse//' ${S}/Makefile
}

do_install () {
    install -d ${D}/${LV2_DIR}/GxSwitchlessWah.lv2
    cp -r ${S}/GxSwitchlessWah.lv2/* ${D}/${LV2_DIR}/GxSwitchlessWah.lv2
    chmod 755 -R ${D}/${LV2_DIR}/
}

DEPENDS = "\
    lilv \
"

RDEPENDS:gxswitchlesswah-lv2 = "\
    libgcc \
"

FILES:${PN} = "\
    ${LV2_DIR} \
"


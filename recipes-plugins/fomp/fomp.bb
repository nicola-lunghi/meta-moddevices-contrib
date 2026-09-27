SUMMARY = "Fomp lv2 plugin suite"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://git.drobilla.net/fomp.lv2.git;protocol=https;branch=master \
    file://01_add-mod-brand-and-label.patch \
"
SRCREV="4511cfc6641bb9b4b08d63f3cd7d16911bce8372"

S = "${WORKDIR}/git"

inherit waf pkgconfig

WAF_PYTHON = "python"

EXTRA_OECONF = "--prefix=${D}/usr"

do_install () {
    # fomp.lv2 graphics is installed by mod-lv2-data recipe
    #${S}/waf install --destdir=${DESTDIR}
    install -d ${D}/${LV2_DIR}/fomp.lv2
    cp -r ${B}/fomp.lv2/*.so ${D}/${LV2_DIR}/fomp.lv2
    chmod 755 -R ${D}/${LV2_DIR}/fomp.lv2
}

DEPENDS = "\
    lv2 \
"

RDEPENDS:${PN} = "\
"

FILES:${PN} = "\
    ${LV2_DIR}/fomp.lv2 \
"
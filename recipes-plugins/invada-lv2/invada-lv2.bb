SUMMARY = "Invada lv2 plugin suite"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PV = "1.2.0"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    https://launchpad.net/invada-studio/lv2/1.2/+download/invada-studio-plugins-lv2_${PV}-nopkg.tgz \
    file://0001-find_lv2_ui_header.patch \
    file://0002-fixed_wrong_graph_in_compressor_gui.patch \
    file://0003-pass_flags.patch \
    file://0004-update_ttls.patch \
    file://0005-no_gui.patch \
    file://0006-add_mod_brand_and_label.patch \
    file://0007-LDFLAGS-provided-by-yocto-are-wrong-Wl-O1.patch \
"
SRC_URI[md5sum] = "fe05214dd65dd3096d03c91d05bc3f5d"
SRC_URI[sha256sum] = "c6cac7c32effc6b3052e3b017133244f385ef8e053147859d88eae6facaf7d12"

S = "${WORKDIR}/invada-studio-plugins-lv2-${PV}"

inherit pkgconfig

# This should fix No GNU_HASH in the elf binary but it doesn't
#TARGET_CC_ARCH += "${LDFLAGS}"
INSANE_SKIP:${PN} = "ldflags"
#INSANE_SKIP:${PN}-dev = "ldflags"

do_compile () {
    oe_runmake
}

do_install () {
    #oe_runmake install-sys DESTDIR=${D} INSTALL_SYS_PLUGINS_DIR=${LV2_DIR}
    install -d ${D}/${LV2_DIR}/invada.lv2
    cp -r ${S}/plugin/*.so ${D}/${LV2_DIR}/invada.lv2
    chmod 755 -R ${D}/${LV2_DIR}

    install -d ${D}/${LV2_DIR_BAD}/invada-bad.lv2
    cp -r ${S}/plugin/*.so ${D}/${LV2_DIR_BAD}/invada-bad.lv2
    chmod 755 -R ${D}/${LV2_DIR_BAD}
}

DEPENDS = "\
    lv2 \
"

RDEPENDS:${PN} = "\
"

FILES:${PN} = "\
    ${LV2_DIR}/invada.lv2 \
    ${LV2_DIR_BAD}/invada-bad.lv2 \
"

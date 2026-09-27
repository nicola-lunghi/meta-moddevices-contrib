SUMMARY = "Alo looper lv2 plugin"
DESCRIPTION = ""
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/devcurmudgeon/alo.git;protocol=https;branch=master \
    file://0001-Now-log-is-saved-under-tmp.patch;patchdir=../ \
"
SRCREV="e4199cc84db18dbd9eb6a14c39fd6e1fae9a57c0"

S = "${WORKDIR}/git/source"

inherit pkgconfig

# This should fix No GNU_HASH in the elf binary but it doesn't
#TARGET_CC_ARCH += "${LDFLAGS}"
#INSANE_SKIP:${PN} = "ldflags"
#INSANE_SKIP:${PN}-dev = "ldflags"

do_compile () {
    oe_runmake
}

do_install () {
    INST_PATH=${LV2_DIR_BAD}
    install -d ${D}/${INST_PATH}/alo.lv2
    cp -r ${S}/alo.lv2/*.so ${D}/${INST_PATH}/alo.lv2
    cp -r ${S}/alo.lv2/*.ttl ${D}/${INST_PATH}/alo.lv2
    cp -r ${S}/alo.lv2/*.ttl.in ${D}/${INST_PATH}/alo.lv2
    cp -r ${S}/alo.lv2/modgui ${D}/${INST_PATH}/alo.lv2

    chmod 755 -R ${D}/${INST_PATH}
}

DEPENDS = "\
    lv2 \
"

RDEPENDS:${PN} = "\
"

FILES:${PN} = "\
    ${LV2_DIR_BAD}/alo.lv2 \
"

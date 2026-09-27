SUMMARY = "Dexed lv2 synth plugin"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/dcoredump/dexed.git;protocol=https;branch=master \
    file://01_fix-duplicated-comment.patch \
    file://02_makefile-ignore-ttl-change.patch \
"
SRCREV="f3518feb75c6039d94a60b99aff2126bd197296b"

S = "${WORKDIR}/git"

inherit pkgconfig

do_compile () {
    cd ${S}/src
    oe_runmake MOD=1
}

do_install () {
    cd ${S}/src
    #oe_runmake install DESTDIR=${D} PREFIX=/usr
    install -d ${D}/${LV2_DIR}
    cp -r ${S}/src/dexed.lv2 ${D}/${LV2_DIR}
    chmod 755 -R ${D}/${LV2_DIR}/dexed.lv2
}

DEPENDS = "\
    lvtk \
"

RDEPENDS:${PN} = "\
"

FILES:${PN} = "\
    ${LV2_DIR}/dexed.lv2 \
"
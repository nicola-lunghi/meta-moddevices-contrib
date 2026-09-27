SUMMARY = "Lv2 ttl files generator tool"
DESCRIPTION = ""
HOMEPAGE = ""
SECTION = "devel"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

inherit pkgconfig

inherit native

SRC_URI = " \
    file://lv2_ttl_generator.c \
    file://GNUmakefile \
"

do_compile:prepend () {
    cd ${WORKDIR}
    mkdir build
    mv lv2_ttl_generator.c GNUmakefile build/
}

do_compile () {
    cd ${WORKDIR}/build
    oe_runmake
}

do_install () {
    install -d ${D}${bindir}
    install -m 755 ${WORKDIR}/lv2_ttl_generator ${D}${bindir}/lv2-ttl-generator
}
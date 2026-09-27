SUMMARY = "Real-time neural network inferencing"
URL = "https://github.com/jatinchowdhury18/RTNeural"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=989b379cee495d4160172fe4c2033ea4"

#DEPENDS = "xsimd nlohmann-json"

SRC_URI = "\
    gitsm://github.com/jatinchowdhury18/RTNeural.git;protocol=https;branch=main \
    file://RTNeural.pc \
"

SRCREV="a32024ba96ee1cd37be6c71188b57dec8e3cbbbe"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

EXTRA_OECMAKE = "-DRTNEURAL_DEFAULT_ALIGNMENT=16 -DRTNEURAL_XSIMD=ON"

do_install () {
    install -d ${D}${libdir}
    install -m 644 ${B}/RTNeural/libRTNeural.a ${D}${libdir}/libRTNeural.a

    install -d ${D}${libdir}/pkgconfig
    install -m 644 ${WORKDIR}/RTNeural.pc ${D}${libdir}/pkgconfig/

    install -d ${D}${includedir}/RTNeural
    cp -r ${S}/RTNeural/* ${D}${includedir}/RTNeural/

    install -d ${D}${includedir}/modules/json
    cp -r ${S}/modules/json ${D}${includedir}/modules/
}

FILES:${PN} += "${libdir}"
FILES:${PN}-dev += "${includedir}"
FILES:${PN}-dbg += "${libdir}/.debug"

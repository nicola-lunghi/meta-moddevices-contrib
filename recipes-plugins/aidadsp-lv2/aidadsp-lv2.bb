SUMMARY = "Lv2 plugin bundle aidadsp-lv2"
DESCRIPTION = ""
URL = "https://github.com/AidaDSP/aidadsp-lv2.git"
SECTION = "lv2/stable"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
BUNDLEDIR = "${@bb.utils.contains('SECTION', 'lv2/stable', '${LV2_DIR}', '${LV2_DIR_BAD}', d)}"

INSANE_SKIP:${PN} += "already-stripped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/AidaDSP/aidadsp-lv2.git;protocol=https;branch=main \
"
SRCREV="f86e21dcb3b481246f0406c5f003f1a0c7d93d37"

PV = "v1.2"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

# -funsafe-loop-optimizations causes the plugin to sound really bad, excluding
CXXFLAGS:append:aarch64 = " -fprefetch-loop-arrays -funroll-loops -static-libstdc++ -Wl,-Ofast -Wl,--as-needed -Wl,--strip-all"
LDFLAGS:append:aarch64 = " -static-libstdc++ -Wl,-Ofast -Wl,--as-needed -Wl,--strip-all"

EXTRA_OECMAKE = '-DCMAKE_BUILD_TYPE=Release -DRTNEURAL_XSIMD=ON -DDESTDIR=${BUNDLEDIR} -DCMAKE_VERBOSE_MAKEFILE:BOOL=ON'

DEPENDS = " \
    lv2 \
"

FILES:${PN} = "\
    ${BUNDLEDIR} \
"

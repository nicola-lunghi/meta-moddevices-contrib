SUMMARY = "C++ wrappers for SIMD intrinsics and parallelized, optimized mathematical functions"
URL = "https://github.com/xtensor-stack/xsimd"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=88b0e6c5e0cfdc34a62377d454240611"

#DEPENDS = "gcc-runtime libgfortran"

SRC_URI = "\
    git://github.com/xtensor-stack/xsimd.git;protocol=https;branch=master \
"
SRCREV="dbba514a7e918b4147396ae4d84ba349f5d9e893"

PV = "8.0.5"

S = "${WORKDIR}/git"

#EXTRA_OECMAKE = "-DBUILD_SHARED_LIBS=ON"

inherit cmake pkgconfig

FILES:${PN} += "${libdir}"
FILES:${PN}-dbg += "${libdir}/.debug"

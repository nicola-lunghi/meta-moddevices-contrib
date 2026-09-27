SUMMARY = "Audio tools"
DESCRIPTION = "A collection of tools (written in C) to do interesting things with sound files."
HOMEPAGE = "https://github.com/libsndfile/sndfile-tools"
AUTHOR = "Erik de Castro Lopo"
DEPENDS = "cairo fftw jack glib-2.0 libsamplerate0 libsndfile1"
SECTION = "libs/multimedia"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

SRC_URI = "\
    git://github.com/libsndfile/sndfile-tools.git;branch=master;protocol=https \
    file://01_jackplay-internal-client.patch \
"
SRCREV = "5545b7c381016ca881bfc100656edfa31d90337c"

S = "${WORKDIR}/git"

inherit autotools lib_package pkgconfig multilib_header

CFLAGS:append:armv7a = " -fprefetch-loop-arrays -funroll-loops -funsafe-loop-optimizations"
CXXFLAGS:append:armv7a = " -fprefetch-loop-arrays -funroll-loops -funsafe-loop-optimizations"

CFLAGS:append = " -ffast-math"
CXXFLAGS:append = " -ffast-math"

do_install:append () {
    install -d ${D}/usr/lib/jack
    mv ${D}/usr/lib/sndfile-jackplay.so ${D}/usr/lib/jack/ 
}

FILES:${PN}:append = " \
    /usr/bin \
    /usr/share \
    /usr/lib \
"
SUMMARY = "Pystache is a Python implementation of Mustache. Mustache is a framework-agnostic, logic-free templating system inspired by ctemplate and et."
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eb4417802c56384aac71b34505528a60"

inherit setuptools3 pkgconfig

SRC_URI = " \
    https://github.com/defunkt/pystache/archive/v${PV}.tar.gz \
"

SRC_URI[md5sum] = "9a064bfa93ce3df8410eced840190abc"
SRC_URI[sha256sum] = "163f5b8fb45f6be3a5074a53a47e79ef51ec015ee43f3ec34b16be279147c96f"

S = "${WORKDIR}/pystache-${PV}"

DEPENDS += "python3"

RDEPENDS:${PN} = "\
    python3-core \
"

# DISTUTILS_INSTALL_ARGS += "--disable-platform-guessing"

CFLAGS:append = " -I${STAGING_INCDIR}"
LDFLAGS:append = " -L${STAGING_LIBDIR}"

do_compile:prepend() {
    export LDFLAGS="$LDFLAGS -L${STAGING_LIBDIR}"
    export CFLAGS="$CFLAGS -I${STAGING_INCDIR}"
}

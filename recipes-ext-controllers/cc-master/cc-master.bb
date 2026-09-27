# Recipe to install cc-master
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/cc-master.git;protocol=https;branch=master \
"
SRCREV = "3fc46c8cee5318217a8d88e9883cbba25cc5e131"

S = "${WORKDIR}/git"

inherit waf pkgconfig

WAF_PYTHON = "python"

EXTRA_OECONF = "--prefix=/usr"

RPROVIDES:${PN} += "\
    libcc_client \
    libcontrolchain \
"

DEPENDS = "\
    libserialport \
    jansson \
"

RDEPENDS:${PN} = "\
    libserialport \
    jansson \
    libcontrolchain \
"

FILES:${PN} += "\
    /usr/include \
    /usr/bin \
    /usr/lib \
    /usr/lib/libcc_client.so \
    /usr/lib/libcontrolchain.so \
"

FILES:${PN}-dev = "\
    ${includedir} \
"

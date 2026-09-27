# Recipe to install mod-ui software
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

INSANE_SKIP:${PN} = "file-rdeps"
INSANE_SKIP:${PN} += "already-stripped"

inherit setuptools3 pkgconfig

SRC_URI = "\
    git://github.com/moddevices/mod-ui.git;protocol=https;branch=master \
    file://solve-do-package-qa-issue.patch \
    file://0002-Migration-to-python3-pycryptodome.patch \
"
SRCREV = "43c204a397cf12f25654381893f59304ceec32a5"

S = "${WORKDIR}/git"

DEPENDS += "\
    alsa-lib \
    jack \
    lilv \
"

RDEPENDS:${PN} = "\
    mod-host \
    sndfile-tools \
    python3-pillow \
    python3-pyserial \
    python3-pystache \
    python3-tornado \
    python3-pycryptodome \
    python3-aggdraw \
    python3-browsepy \
    python3-ctypes \
    mod-lv2-extensions \
    kxstudio-lv2-extensions \
"

FILES:${PN} += "/"

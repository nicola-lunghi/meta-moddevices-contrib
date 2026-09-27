SUMMARY = "Install entrypoint.sh into container"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " file://entrypoint.sh"

do_install () {
    install -m 755 ${WORKDIR}/entrypoint.sh ${D}/entrypoint.sh
}

FILES:${PN} = "/entrypoint.sh" 

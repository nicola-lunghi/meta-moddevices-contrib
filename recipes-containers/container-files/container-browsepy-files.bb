SUMMARY = "Copy samples, IRs, amp model SIMs into directories handled by browsepy"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

do_install () {
    install -d ${D}/home/root/.irfiles/cabs
    install -d ${D}/home/root/user-files
    cp -r ${WORKDIR}/../../aidadsp-lv2/*/git/irs/* ${D}/home/root/.irfiles/cabs/
    ln -s ../.irfiles/cabs ${D}/home/root/user-files/'Speaker Cabinets IRs'
    ln -s ../.irfiles ${D}/home/root/user-files/'Reverb IRs'

    install -d ${D}/home/root/user-files/'Aida DSP Models'
    cp -r ${WORKDIR}/../../aidadsp-lv2/*/git/models/* ${D}/home/root/user-files/'Aida DSP Models'/
}

DEPENDS = "\
    aidadsp-lv2 \
"

FILES:${PN} = "/home/root"

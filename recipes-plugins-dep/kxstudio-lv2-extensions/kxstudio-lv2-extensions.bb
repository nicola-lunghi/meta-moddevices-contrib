# Recipe to install kxstudio-lv2-extensions
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

S = "${WORKDIR}/git"

SRC_URI = "\
    git://github.com/KXStudio/LV2-Extensions.git;protocol=https;branch=master \
"
SRCREV="fae65fbc173cd2c4367e85917a6ef97280532d88"

do_install () {
    oe_runmake install DESTDIR=${D} PREFIX=/usr
}

DEPENDS = "\
"

RDEPENDS:${PN} = "\
"

FILES:${PN} += "\
    /usr/lib/lv2 \
"


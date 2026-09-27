DESCRIPTION = "Serial MIDI interface for MOD devices."
SECTION = "devel"
HOMEPAGE = "https://github.com/moddevices/mod-ttymidi"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "\
    git://github.com/moddevices/mod-ttymidi;protocol=https;branch=master \
"
SRCREV = "512edcc6aab390bdd3627cea005861211cd29d67"

S = "${WORKDIR}/git"

inherit pkgconfig

do_compile () {
    oe_runmake 'CC=${CC}'
}

do_install () {
    install -d ${D}/usr/local/bin
    install -d ${D}/usr/lib/jack
    oe_runmake install DESTDIR=${D} PREFIX=/usr/local
}

DEPENDS = "\
    jack \
"

RDEPENDS:${PN} = "\
    jack-server \
"

FILES:${PN} += "\
    /usr/local/bin \
    /usr/lib/jack \
"

FILES:${PN}-dbg += "/usr/local/bin/.debug"

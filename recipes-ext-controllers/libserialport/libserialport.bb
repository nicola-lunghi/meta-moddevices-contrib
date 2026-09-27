# Recipe to install libserialport
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://sigrok.org/libserialport;protocol=git;branch=master \
"
SRCREV="master"

S = "${WORKDIR}/git"

inherit autotools

# Without this folder fails the do_configure task...
do_configure:prepend () {
    mkdir ${S}/autostuff
}

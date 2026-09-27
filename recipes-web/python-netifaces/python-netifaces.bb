SUMMARY = "netifaces"
DESCRIPTION = "\
"
HOMEPAGE = "https://bitbucket.org/al45tair/netifaces"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "https://pypi.python.org/packages/72/01/ba076082628901bca750bf53b322a8ff10c1d757dc29196a8e6082711c9d/netifaces-0.10.6.tar.gz"
SRC_URI[md5sum] = "1d424cb5ef52907c5ab913011122a98b"
SRC_URI[sha256sum] = "0c4da523f36d36f1ef92ee183f2512f3ceb9a9d2a45f7d19cda5a42c6689ebe0"

S = "${WORKDIR}/netifaces-${PV}"

inherit setuptools3

#RDEPENDS:${PN} = "${PYTHON_PN}-werkzeug ${PYTHON_PN}-jinja2 ${PYTHON_PN}-itsdangerous ${PYTHON_PN}-click"

SUMMARY = "Unicode category database, generated and cached on setup."
HOMEPAGE = "https://gitlab.com/ergoithz/unicategories"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0113901dde3d22841688ebe57be9f82c"

inherit pypi setuptools3

PYPI_PACKAGE = "unicategories"

SRC_URI[md5sum] = "82c17a23ae8ca4975873159a3d69a74d"
SRC_URI[sha256sum] = "f2755ea219484bd9f00a723d7ea8a7ba9b0b5f302502d847b1fbce5230b48163"

RDEPENDS:${PN} = "\
    python3-appdirs \
"

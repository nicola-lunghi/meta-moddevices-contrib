SUMMARY = "JACK Audio Connection Kit (JACK) Client for Python"
HOMEPAGE = "https://github.com/spatialaudio/jackclient-python.git"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b0926efcfb97071ad1ed5232c4e64c49"

inherit pypi setuptools3

PV = "0.5.1"

PYPI_PACKAGE = "JACK-Client"

SRC_URI[md5sum] = "d6f0a5a66c76ccfa7dfb84df8184d181"
SRC_URI[sha256sum] = "55bc516a88fc9e3eaac9dc6e55205bbf8eab8d9f9aae665a07324b2cfb54f211"

DEPENDS += "\
    python3 \
    python3-cffi \
    jack \
"

RDEPENDS:${PN} = "\
    python3-core \
"

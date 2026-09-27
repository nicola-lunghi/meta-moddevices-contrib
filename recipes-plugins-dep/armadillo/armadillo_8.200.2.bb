SUMMARY = "C++ linear algebra library"
HOMEPAGE = "http://arma.sourceforge.net/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d273d63619c9aeaf15cdaf76422c4f87"
SRC_URI = "http://sourceforge.net/projects/arma/files/armadillo-${PV}.tar.xz"

SRC_URI[md5sum] = "ba298a232a5a37071b8e685c4bd3e383"
SRC_URI[sha256sum] = "63845e36235f2bd78b4f6890fe5013b6ce51a4e92e3176b20bbc6631b340050a"

EXTRA_OECMAKE += " -DBUILD_SHARED_LIBS=ON -DINSTALL_LIB_DIR=${libdir}"
DEPENDS += " lapack "
inherit cmake

FILES:${PN}-dev += " \
  /usr/share/Armadillo/CMake/ArmadilloLibraryDepends-noconfig.cmake \
  /usr/share/Armadillo/CMake/ArmadilloLibraryDepends.cmake \
  /usr/share/Armadillo/CMake/ArmadilloConfig.cmake \
  /usr/share/Armadillo/CMake/ArmadilloConfigVersion.cmake \
"

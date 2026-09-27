SUMMARY = "Ttl and web gui files for lv2 plugins from moddevices under CC-BY-NC-ND-4.0 license"
DESCRIPTION = "These files usually overwrite those present in plugins sources"
SECTION = "lv2/webui"
LICENSE = "CC-BY-NC-ND-3.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CC-BY-NC-ND-3.0;md5=a1038f0f4a3de1fe9e79e2180c40f10c"
LICENSE_FLAGS = "commercial"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"
do_populate_sysroot[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/lv2-data-creative-commons.git;protocol=https;branch=master \
"
SRCREV = "d94a369b849d2c0c511319c12a2b5a10908ca2d9"

S = "${WORKDIR}/git"

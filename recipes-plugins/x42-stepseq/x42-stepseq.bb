# Recipe to build and install x42-stepseq lv2 plugin
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP:${PN} = "already-stripped"
#INSANE_SKIP:${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
# 474cdc9fa8ad16c052671d2e8066e9cf38aa52b1
SRC_URI = "\
    git://github.com/x42/stepseq.lv2.git;protocol=https;branch=master \
"
SRCREV = "b009812fd0af471b647c179c50ca0941c87ff6a4"

inherit pkgconfig

S = "${WORKDIR}/git"

# 'OPTIMIZATIONS="-fno-finite-math-only -DNDEBUG"'
EXTRA_OEMAKE = "'MOD=1' 'PREFIX=/usr'"

do_configure:prepend () {
    sed -i -- 's/-msse -msse2 -mfpmath=sse//' ${S}/Makefile
    sed -i -- 's/LV2DIR ?= \$(PREFIX)\/lib\/lv2/LV2DIR ?= \/\/usr\/local\/.lv2/' ${S}/Makefile
    sed -i -- 's/BUILDOPENGL?=yes/BUILDOPENGL?=no/' ${S}/Makefile
    sed -i -- 's/BUILDJACKAPP?=yes/BUILDJACKAPP?=no\nMOD?=yes/' ${S}/Makefile
}

do_compile () {
    # 8x4 version
    oe_runmake clean
    oe_runmake N_STEPS=8 N_NOTES=4 install DESTDIR=${D}
    cp ${S}/build/stepseq.so ${S}/build/stepseq_s8n4.so

    # 8x8 version
    oe_runmake clean
    oe_runmake N_STEPS=8 N_NOTES=8 install DESTDIR=${D}
    cp ${S}/build/stepseq.so ${S}/build/stepseq_s8n8.so

    # 8x16 version
    oe_runmake clean
    oe_runmake N_STEPS=8 N_NOTES=16 install DESTDIR=${D}
    cp ${S}/build/stepseq.so ${S}/build/stepseq_s8n16.so
}

do_install () {
    install -d ${D}/${LV2_DIR}/stepseq_s8n4.lv2
    cp ${S}/build/stepseq_s8n4.so ${D}/${LV2_DIR}/stepseq_s8n4.lv2/stepseq.so

    install -d ${D}/${LV2_DIR}/stepseq_s8n8.lv2
    cp ${S}/build/stepseq_s8n8.so ${D}/${LV2_DIR}/stepseq_s8n8.lv2/stepseq.so

    install -d ${D}/${LV2_DIR}/stepseq_s8n16.lv2
    cp ${S}/build/stepseq_s8n16.so ${D}/${LV2_DIR}/stepseq_s8n16.lv2/stepseq.so

    chmod 755 -R ${D}/${LV2_DIR}/
}

DEPENDS += "\
    lv2 \
"

FILES:${PN} = "\
    ${LV2_DIR}/stepseq_s8n4.lv2 \
    ${LV2_DIR}/stepseq_s8n8.lv2 \
    ${LV2_DIR}/stepseq_s8n16.lv2 \
"

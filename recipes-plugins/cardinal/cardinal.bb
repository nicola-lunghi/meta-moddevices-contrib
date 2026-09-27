SUMMARY = "Lv2 plugin suite Cardinal"
DESCRIPTION = ""
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

INSANE_SKIP:${PN} += "already-stripped"

PV = "22.05"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    https://github.com/DISTRHO/Cardinal/releases/download/${PV}/cardinal+deps-${PV}.tar.xz \
    file://0001-Removed-quickjs-dep-needed-by-Aria-Modules.patch \
    file://0003-fix-gcc_struct-error.patch \
    file://0006-fix-non-present-modgui-graphics.patch \
    file://0007-fix-quickjs-makefile.patch \
    file://cardinal-ttl-files.tar.gz \
    file://manifest.ttl \
    file://CardinalFX.ttl \
"
SRC_URI[md5sum] = "0ee33a89f00b8a31835513391b24a840"
SRC_URI[sha256sum] = "6f823170b098e612dce93b391bb67ee67dfc16ce0017104f92e3d8b011292ebd"

S = "${WORKDIR}/cardinal-${PV}"

inherit pkgconfig

CFLAGS:append = " -ffat-lto-objects -Wno-format-security -Wimplicit-fallthrough=0"
CXXFLAGS:append = " -ffat-lto-objects -Wno-format-security -Wimplicit-fallthrough=0"
LDFLAGS:append = " -ffat-lto-objects -Wno-format-security -Wimplicit-fallthrough=0"
EXTRA_OEMAKE = "HEADLESS=true CROSS_COMPILING=true MOD_BUILD=true NOOPT=true SYSDEPS=true STATIC_BUILD=true WITH_LTO=true"

# QuickJS target, needed for AriaModules
#QUICKJS_MAKE_FLAGS  = CFLAGS="$(BUILD_C_FLAGS) -D_GNU_SOURCE -DCONFIG_VERSION='\"Cardinal\"' -w"
QUICKJS_MAKE_FLAGS = 'PROGS=libquickjs.a'
QUICKJS_MAKE_FLAGS += ' CONFIG_LTO=y'

do_compile () {
    # Compile QuickJS first
    bbwarn "Compiling QuickJS"
    cd ${S}/deps/QuickJS
    oe_runmake ${QUICKJS_MAKE_FLAGS}
    mkdir -p ${S}/deps/sysroot/lib
    cp ${S}/deps/QuickJS/libquickjs.a ${S}/deps/sysroot/lib/
    cp ${S}/deps/QuickJS/quickjs.h ${S}/plugins/AriaModules/src/

    bbwarn Compiling Cardinal
    cd ${S}
    oe_runmake
}

do_install () {
    install -d ${D}/${LV2_DIR_BAD}/CardinalFX.lv2
    cp -rL ${S}/bin/CardinalFX.lv2 ${D}/${LV2_DIR_BAD}/

    # @TODO: remove 0006-fix-non-present-modgui-graphics.patch in next Cardinal releases
    cp -r ${WORKDIR}/src/MOD/CardinalFX.lv2/* ${D}/${LV2_DIR_BAD}/CardinalFX.lv2/

    # @TODO: these ttl files came from the arm64 X11 release
    install -m 775 ${WORKDIR}/manifest.ttl ${D}/${LV2_DIR_BAD}/CardinalFX.lv2/
    install -m 775 ${WORKDIR}/CardinalFX.ttl ${D}/${LV2_DIR_BAD}/CardinalFX.lv2/

    chmod 755 -R ${D}/${LV2_DIR_BAD}
}

DEPENDS += " \
    lilv \
    lv2 \
    fftw \
    liblo \
    jansson \
    libarchive \
    libsamplerate0 \
    speexdsp \
    liblo \
"

RDEPENDS:${PN} += " \
    liblo \
    jansson \
    libarchive \
    libsamplerate0 \
    speexdsp \
    liblo \
    zstd \
"

FILES:${PN} = "\
    ${LV2_DIR_BAD} \
"
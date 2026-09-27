SUMMARY = "Airwindows plugins (LV2 port)"
DESCRIPTION = "This is an LV2 port of the Airwindows plugins made by Chris Johnson."
HOMEPAGE = "https://github.com/hannesbraun/airwindows-lv2"
SECTION = "lv2/stable"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
BUNDLEDIR = "${@bb.utils.contains('SECTION', 'lv2/stable', '${LV2_DIR}', '${LV2_DIR_BAD}', d)}"

INSANE_SKIP:${PN} += "already-stripped"

SRC_URI = "\
    git://github.com/hannesbraun/airwindows-lv2.git;protocol=https;branch=master \
    file://01_optimizations.patch \
"
SRCREV = "9b48123f778e0ed623b0e432695d6b5d32f70638"

S = "${WORKDIR}/git"

inherit cmake

do_install () {
    for p in `ls ${WORKDIR}/build/Airwindows.lv2 | sort | grep -- '\.so'`; do
        bbdebug 1 Installing ${p}
        pl=`echo ${p} | sed 's/\.so//'`
        pll=`echo ${pl} | tr '[:upper:]' '[:lower:]'`
        install -d ${D}/${BUNDLEDIR}/Airwindows-${pl}.lv2
        cp ${WORKDIR}/build/Airwindows.lv2/${pl}.* ${D}/${BUNDLEDIR}/Airwindows-${pl}.lv2
        echo "@prefix lv2:  <http://lv2plug.in/ns/lv2core#> ." > ${D}/${BUNDLEDIR}/Airwindows-${pl}.lv2/manifest.ttl
        echo "@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> ." >> ${D}/${BUNDLEDIR}/Airwindows-${pl}.lv2/manifest.ttl
        echo -n "<https://hannesbraun.net/ns/lv2/airwindows/" >> ${D}/${BUNDLEDIR}/Airwindows-${pl}.lv2/manifest.ttl
        echo -n ${pll} >> ${D}/${BUNDLEDIR}/Airwindows-${pl}.lv2/manifest.ttl
        echo ">" >> ${D}/${BUNDLEDIR}/Airwindows-${pl}.lv2/manifest.ttl
        echo "a lv2:Plugin ; lv2:binary <${pl}.so> ; rdfs:seeAlso <${pl}.ttl> ." >> ${D}/${BUNDLEDIR}/Airwindows-${pl}.lv2/manifest.ttl
    done

    # Airwindows-StarChild.lv2 ttls and modgui are installed from mod-lv2-data
    cp -rL ${WORKDIR}/../../mod-lv2-data/*/git/plugins-fixed/Airwindows-StarChild.lv2/*.ttl ${D}/${BUNDLEDIR}/Airwindows-StarChild.lv2
    cp -rL ${WORKDIR}/../../mod-lv2-data/*/git/plugins-fixed/Airwindows-StarChild.lv2/modgui ${D}/${BUNDLEDIR}/Airwindows-StarChild.lv2
}

DEPENDS += "\
    lv2 \
    mod-lv2-data \
"

FILES:${PN} = "\
    ${BUNDLEDIR} \
"

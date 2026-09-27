require ${BPN}.inc

inherit waf pkgconfig

EXTRA_OECONF = "--libdir=${libdir}"
EXTRA_OECONF += "--no-plugins"

DEPENDS = "libsndfile1"

FILES:${PN} += " \
    ${datadir} \
"

SUMMARY = "Font rendering capabilities for complex non-Roman writing systems"
HOMEPAGE = "http://sourceforge.net/projects/silgraphite"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=b0452d508cc4eb104de0226a5b0c8786"

inherit cmake

DEPENDS += "freetype"

SRC_URI = "git://github.com/silnrsi/graphite.git"
SRCREV = "92f59dcc52f73ce747f1cdc831579ed2546884aa"
PV = "1.3.14"
S = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DLIB_SUFFIX=${@d.getVar('baselib').replace('lib', '')}"

BBCLASSEXTEND = "native"

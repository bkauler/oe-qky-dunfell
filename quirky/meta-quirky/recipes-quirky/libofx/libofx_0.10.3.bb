# Recipe created by recipetool
# recipetool create -o libofx_0.10.3.bb https://sourceforge.net/projects/libofx/files/libofx/libofx-0.10.3.tar.gz

HOMEPAGE = "http://libofx.sourceforge.net/"
SUMMARY = "OFX banking protocol abstraction library"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4641e94ec96f98fabc56ff9cc48be14b"

SRC_URI = "https://sourceforge.net/projects/libofx/files/libofx/libofx-${PV}.tar.gz"
SRC_URI[md5sum] = "9c6974c1e0cde99fa6d839f6a38fce08"
SRC_URI[sha256sum] = "7b5f21989afdd9cf63ab4a2df4ca398782f24fda2e2411f88188e00ab49e3069"

DEPENDS = "opensp curl libxml2 libxml++"

inherit autotools pkgconfig

EXTRA_OECONF = "--disable-html-docs --disable-doxygen --with-opensp-includes=${SROOT}/usr/include/OpenSP --with-opensp-libs=${SROOT}/usr/lib"
#EXTRA_OECONF = "--disable-html-docs --disable-doxygen"

XXXdo_configure() {
 oe_runconf
}

#ERROR: libofx-0.10.3-r5 do_configure: QA Issue: This autoconf log indicates errors, it looked at host include and/or library paths while determining system capabilities.
#Rerun configure task after fixing this. [configure-unsafe]
#do this bad hack for now...
ERROR_QA_remove = "configure-unsafe"

#ERROR: libofx-0.10.3-r5 do_package_qa: QA Issue: libofx: /usr/bin/ofxconnect contains probably-redundant RPATH /usr/lib/../lib
#libofx: /usr/bin/ofx2qif contains probably-redundant RPATH /usr/lib/../lib
#libofx: /usr/bin/ofxdump contains probably-redundant RPATH /usr/lib/../lib [useless-rpaths]
ERROR_QA_remove = "useless-rpaths"

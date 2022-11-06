# Recipe created by recipetool
# recipetool create -o libdv_1.0.0.bb https://downloads.sourceforge.net/libdv/libdv-1.0.0.tar.gz
# ref: https://www.linuxfromscratch.org/blfs/view/svn/multimedia/libdv.html

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"

SRC_URI = "https://downloads.sourceforge.net/libdv/libdv-${PV}.tar.gz"
SRC_URI[md5sum] = "f895162161cfa4bb4a94c070a7caa6c7"
SRC_URI[sha256sum] = "a305734033a9c25541a59e8dd1c254409953269ea7c710c39e540bd8853389ba"

DEPENDS = "libx11 libxv popt libsdl libsdl-mixer libsdl-image libsdl-ttf"

inherit pkgconfig autotools-brokensep

EXTRA_OECONF = "--disable-xv --disable-static"

HOMEPAGE = "http://libdv.sourceforge.net/"
SUMMARY = "A codec for DV video, used by most digital camcorders"

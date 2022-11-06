# Recipe created by recipetool
# recipetool create -o potrace_1.16.bb https://downloads.sourceforge.net/potrace/potrace-1.16.tar.gz

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6ccdcb5472e5e7e8061f4128ef20e049"

SRC_URI = "https://downloads.sourceforge.net/potrace/potrace-${PV}.tar.gz"
SRC_URI[md5sum] = "5f0bd87ddd9a620b0c4e65652ef93d69"
SRC_URI[sha1sum] = "256b4fb858c66bc38117afde7c722016d2e265f3"
SRC_URI[sha256sum] = "be8248a17dedd6ccbaab2fcc45835bb0502d062e40fbded3bc56028ce5eb7acc"
SRC_URI[sha384sum] = "852957f0b5d81b84d460175546b940a75c47c3eb994e05b1d820509bb9e5653f3f3c6d359c0d8683e0cf838f7380e2c0"
SRC_URI[sha512sum] = "10d85ed3fada80951223c65e923b5e6ee3e76e071575971d5b09b996b477ec5ff2403e0337c44963c6b441cc3ba6c4f7009541124a25c16b7721fdebd1f774b9"

DEPENDS = "zlib"

inherit autotools

# ref: http://www.linuxfromscratch.org/blfs/view/svn/general/potrace.html
EXTRA_OECONF = "--disable-static --enable-a4 --enable-metric --with-libpotrace"

HOMEPAGE = "http://potrace.sourceforge.net/"
SUMMARY = "Transform bitmaps to vector graphics"


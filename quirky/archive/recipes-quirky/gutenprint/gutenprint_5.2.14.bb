# Recipe created by recipetool
# recipetool create -o gutenprint_5.2.14.bb https://downloads.sourceforge.net/project/gimp-print/gutenprint-5.2/5.2.14/gutenprint-5.2.14.tar.bz2

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

SRC_URI = "https://downloads.sourceforge.net/project/gimp-print/gutenprint-5.2/${PV}/gutenprint-${PV}.tar.bz2 \
  file://xmli18n-tmp.h"
SRC_URI[md5sum] = "4177da0cb5ce39c6115e54694687d656"
SRC_URI[sha256sum] = "45bf0766f196a764c113bfad7ab1ea748f1d5ff9ebb0d3a2206705fe52e82389"

DEPENDS = "readline libtool libusb1 gtk+ flex-native bison-native bzip2 xz gzip cups poppler ghostscript ijs cups-filters gimp perl bash"

inherit perlnative pkgconfig gettext autotools

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF = "--disable-test --without-doc --disable-samples --enable-libgutenprintui2 --disable-nls"

# BK ah, just having "oe_runconf" in here avoids the rebuilding of 'configure' script
# ...fixes do_configure...
do_configure() {
#    gnu-configize
#    libtoolize --force
    sed -i -e 's%^	\./extract-strings .*%	cat ${WORKDIR}/xmli18n-tmp.h > $@.tmp%' ${S}/src/xml/Makefile.in
    oe_runconf
}

# shebangs are wrong...
do_install_append() {
 sed -i -e 's%^#! /mnt/.*%#!/usr/bin/perl -w%' ${D}/usr/sbin/cups-genppdupdate
}

FILES_${PN} += "${libdir}/cups ${datadir}/cups"

RDEPENDS_gutenprint += "perl"

HOMEPAGE = "http://gutenprint.sourceforge.net/"
SUMMARY = "High quality drivers for Canon Epson Lexmark Sony Olympus and PCL"

# Recipe created by recipetool
# recipetool create -o gutenprint_5.3.3.bb https://sourceforge.net/projects/gimp-print/files/gutenprint-5.3/5.3.3/gutenprint-5.3.3.tar.xz

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

SRC_URI = "https://sourceforge.net/projects/gimp-print/files/gutenprint-5.3/${PV}/gutenprint-${PV}.tar.xz"
SRC_URI[md5sum] = "8703d3e27283c4cf9cd475aaacfe8e4b"
SRC_URI[sha1sum] = "edfab4bafe8e6420c7c321915eeb2dd16e899c59"
SRC_URI[sha256sum] = "7279ecbc8e67d1858c53cb24e423ea1be98e214872006b79e6e03650dd54a072"
SRC_URI[sha384sum] = "a109f30063e3df4a2cedddf7a906e8379f701ed8843590d99470dc6f0f25eb7d5b7f697ce7ce1c09a31a2d55407b9230"
SRC_URI[sha512sum] = "d2c47eb4ccc3c46ccb2f1042682edf7443f5c57439ead72f49ecd10537cf967251bacf7ca7da29fb8dceecc7eebd55ed021f2594ff9fb6509bab543fab1dc8d6"

# NOTE: the following prog dependencies are unknown, ignoring: rzip db2ps bzip2 
#  dvipdf db2pdf true dvipdfm test gzip convert bash db2html dvips doxygen lrzip 
#  texi2html glib-mkenums xz zpaq

# 20220719 removed 'gimp' dependency
DEPENDS = "libusb1 libtool readline gtk+ bison-native flex-native bzip2 xz \
           zlib cups ghostscript readline gzip poppler ijs cups-filters"

inherit pkgconfig perlnative gettext autotools

EXTRA_OECONF = "--disable-test --without-doc --disable-samples --enable-libgutenprintui2 \
                --disable-static --enable-static-genppd"

#ERROR: gutenprint-5.3.3-r0 do_package_qa: QA Issue: gutenprint: /work/core2-64-poky-linux/gutenprint/5.3.3-r0/packages-split/gutenprint/usr/sbin/cups-genppdupdate maximum shebang size exceeded, the maximum size is 128. [shebang-size]
ERROR_QA_remove = "shebang-size"
#ERROR: gutenprint-5.3.3-r0 do_package_qa: QA Issue: /usr/sbin/cups-genppdupdate contained in package gutenprint requires /mnt/sda1/nvme/oe-builds/oe-quirky/build-amd64/tmp/work/core2-64-poky-linux/gutenprint/5.3.3-r0/recipe-sysroot-native/usr/bin/perl-native/perl, but no providers found in RDEPENDS_gutenprint? [file-rdeps]
ERROR_QA_remove = "file-rdeps"

HOMEPAGE = "http://gutenprint.sourceforge.net/"
SUMMARY = "High quality drivers for Canon Epson Lexmark Sony Olympus and PCL"



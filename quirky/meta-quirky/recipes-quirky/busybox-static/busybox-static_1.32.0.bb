# Recipe created by recipetool

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=de10de48642ab74318e893a61105afbb \
                    file://archival/libarchive/bz/LICENSE;md5=28e3301eae987e8cfe19988e98383dae"

#           file://0007-busybox-1.22-20130821-guess_fstype-applet.patch 

SRC_URI = "http://busybox.net/downloads/busybox-${PV}.tar.bz2 \
           file://BBconfig-1.32.0-200717"
SRC_URI[md5sum] = "9576986f1a960da471d03b72a62f13c7"
SRC_URI[sha256sum] = "c35d87f1d04b2b153d33c275c2632e40d388a88f19a9e71727e0bbbff51fe689"

S = "${WORKDIR}/busybox-${PV}"

DEPENDS = "kern-tools-native flex-native bison-native"

export EXTRA_CFLAGS = "${CFLAGS}"
export EXTRA_LDFLAGS = "${LDFLAGS}"

EXTRA_OEMAKE = "CC='${CC}' LD='${CCLD}' V=1 ARCH=${TARGET_ARCH} CROSS_COMPILE=${TARGET_PREFIX} SKIP_STRIP=y HOSTCC='${BUILD_CC}' HOSTCPP='${BUILD_CPP}'"

do_configure () {
 cp -a -f ${WORKDIR}/BBconfig-1.32.0-200717 ${S}/.config
}

do_compile () {
 oe_runmake
}

do_install () {
 #oe_runmake install
 install -d ${D}/bin
 install -m 755 busybox ${D}/bin
}

SUMMARY = "swiss-army-knife utilities"
HOMEPAGE = "https://busybox.net/"

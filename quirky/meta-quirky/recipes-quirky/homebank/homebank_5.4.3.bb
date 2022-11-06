# Recipe created by recipetool
# recipetool create -o homebank_5.4.3.bb http://homebank.free.fr/public/homebank-5.4.3.tar.gz

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "http://homebank.free.fr/public/homebank-${PV}.tar.gz"
SRC_URI[md5sum] = "39651c2698a4affadf0a296c8039d862"
SRC_URI[sha1sum] = "a63e8cf9c4f91449dec4b690df768492f56af033"
SRC_URI[sha256sum] = "9222d7ed7cc44fcfff3f1fe20935a1b7fe91bb4d9f90003cb3c6f3b893298d0b"
SRC_URI[sha384sum] = "f31244e10c45f21d4297e57871a3cd7edc2f47aa7ee37516c2ecf605d2b05ea9708e4f6aa641d63a82b8067b7b63e75b"
SRC_URI[sha512sum] = "e2b3b45a6d35205c3d2bf24d4c0c065889fa4e7d92cc9551db873b8b33cc15e6c1dd721936b438db0725509233bf265446b0512e23973cbebae7af4a7dce96ba"

#2020-09-19 /mnt/sda1/nvme/oe/oe-quirky/build-amd64/tmp/work/core2-64-poky-linux/homebank/5.4.3-r0/temp/run.do_configure.528: line 169: glib-gettextize: command not found. fix: glib-2.0-native
DEPENDS = "libsoup-2.4 gtk+3 libofx glib-2.0 glib-2.0-native intltool-native"

# NOTE: if this software is not capable of being built in a separate build directory
# from the source, you should replace autotools with autotools-brokensep in the
# inherit line
inherit pkgconfig gettext autotools

EXTRA_OECONF = "--with-ofx"

FILES_${PN} += "${datadir}/mime ${datadir}/application-registry ${datadir}/icons ${datadir}/mime-info ${datadir}/appdata"

HOMEPAGE = "http://homebank.free.fr/"
SUMMARY = "Personal finance management"


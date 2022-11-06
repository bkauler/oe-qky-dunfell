# Recipe created by recipetool
# recipetool create -o redshift_1.11.bb https://github.com/jonls/redshift/archive/refs/tags/v1.11.tar.gz

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://github.com/jonls/redshift/archive/refs/tags/v${PV}.tar.gz"

SRC_URI[md5sum] = "26981273f5a6b5a5ff425a3060908cf8"
SRC_URI[sha256sum] = "a89cee0dc822fa2bfda0703d8ecedeccc08da3aebdf1880593f28da81c3dc879"

DEPENDS = "intltool-native libdrm libxcb libx11 glib-2.0 libxxf86vm"

inherit pkgconfig gettext autotools

EXTRA_OECONF = "--with-systemduserunitdir=no --enable-drm --enable-randr \
       --disable-geoclue --disable-geoclue2 --disable-gui --disable-ubuntu"


HOMEPAGE = "http://jonls.dk/redshift/"
SUMMARY = "Redshift adjusts the color temperature of your screen according to your surroundings"

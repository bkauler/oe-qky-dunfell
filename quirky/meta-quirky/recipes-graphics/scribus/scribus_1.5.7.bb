# Recipe created by recipetool
# recipetool create -o scribus_1.5.7.bb https://downloads.sourceforge.net/project/scribus/scribus-devel/1.5.7/scribus-1.5.7.tar.gz

inherit cmake_qt5 cmake_extra_sanity python3-dir pkgconfig python3native

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=46d73026c0f1b3a4016bc06a677411ab"

SRC_URI = "https://downloads.sourceforge.net/project/scribus/scribus-devel/${PV}/scribus-${PV}.tar.gz"
SRC_URI[md5sum] = "a0e3a62705dbc221d990b3b0da5bca06"
SRC_URI[sha256sum] = "ff9678e18d9eebf4e381d18eb40c5477b6fa28112481733db647d39a8d58aac7"

DEPENDS = "icu cairo cairo-native freetype freetype-native tiff boost \
           libpagemaker virtual/libgl jpeg libcdr zlib libxml2 \
           poppler librsvg sqlite3 hunspell libdrm expat libjpeg-turbo \
           lcms pixman libpng harfbuzz ghostscript libxcb libxrender \
           libx11 libxext openssl mesa gnutls glib-2.0 graphite2 xz util-linux \
           libxau libxdmcp libxshmfence libgcrypt libxdamage libxfixes libxxf86vm \
           libidn libunistring nettle gmp libxau glib-2.0-native \
           qtbase qtbase-native qttools qttools-native \
           python3 python3-native libwpd libwpd-native libmspub libmspub-native \
           libvisio libvisio-native libfreehand libfreehand-native \
           librevenge librevenge-native libpagemaker libpagemaker-native cups \
           cups-filters fontconfig fontconfig-native"


SUMMARY = "Desktop publishing"
HOMEPAGE = "https://www.scribus.net/"


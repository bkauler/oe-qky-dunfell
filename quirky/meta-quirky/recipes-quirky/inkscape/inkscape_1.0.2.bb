# Recipe created by recipetool
# recipetool create -o inkscape_1.0.2.bb https://inkscape.org/gallery/item/23820/inkscape-1.0.2.tar.xz

LICENSE = "GPLv2 & GPLv3 & LGPLv3 & BSD-3-Clause & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=0842e63acabdf4dfa2249c93f54d4f52 \
                    file://LICENSES/GPL-2.0.txt;md5=0597d53b8f2dba85c174c771eead1324 \
                    file://LICENSES/LGPL-3.0-or-later.txt;md5=6aeb448bc450b9fe4c14e7f1d723e0f4 \
                    file://LICENSES/LGPL-2.1.txt;md5=58536dbdbca839fcfc5366fa07bfa758 \
                    file://LICENSES/GPL-3.0.txt;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LICENSES/LGPL-3.0.txt;md5=3000208d539ec061b899bce1d9ce9404"

SRC_URI = "https://inkscape.org/gallery/item/23820/inkscape-${PV}.tar.xz"
SRC_URI[md5sum] = "ec90e0c1e4c7e3dba8f46b16c73462b3"
SRC_URI[sha1sum] = "17a1c90f49bc45f4068f35a7986f92399f9c1be5"
SRC_URI[sha256sum] = "da3e230511a08cbf21e86710d161458594fea87867e9157b67ed01a04ea2798a"
SRC_URI[sha384sum] = "d8a085650ed8932f5f596cb1c4047c6642f637b4cb853887276fb02bcb61d12b08abcaeeed05a1a8cbd2239d73dbb750"
SRC_URI[sha512sum] = "f4fbc627c0f07db11194715c505b771a60af29a9a7f8be0232e4f7eb6b1c5814c3f160c2003d64ec62aeb92ef44174320a47aa6b6abc7a67cc27c996cba9522d"

S = "${WORKDIR}/${BPN}-${PV}_2021-01-15_e86c870879"

# ref: https://wiki.inkscape.org/wiki/index.php?title=Tracking_Dependencies
# NOTE: unable to map the following CMake package dependencies: LibCDR Potrace Iconv BoehmGC GTest JeMalloc DoubleConversion LibVisio LCMS2 LibWPG PopplerCairo
# NOTE: unable to map the following pkg-config dependencies: ImageMagick++ GraphicsMagick++ gdl-3.0 gtkspell3-3.0 gtk-mac-integration-gtk3
#       (this is based on recipes that have previously been built and packaged)
DEPENDS = "boost libx11 libxslt zlib aspell libpng jpeg virtual/libintl libxml2 \
           libsigc++-2.0 gtkmm3 gtksourceview2 bdwgc poppler gsl intltool gtk+3 \
           glibmm libart-lgpl librsvg librsvg-native popt cups ghostscript freetype \
           fontconfig harfbuzz libsoup-2.4 lcms pango double-conversion potrace \
           wget libcanberra gdl libcdr"

inherit cmake pkgconfig gettext

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = ""

HOMEPAGE = "https://inkscape.org"
SUMMARY = "SVG vector editor"

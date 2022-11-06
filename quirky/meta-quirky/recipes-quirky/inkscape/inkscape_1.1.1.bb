# Recipe created by recipetool
# recipetool create -o inkscape_1.1.1.bb https://inkscape.org/gallery/item/29255/inkscape-1.1.1.tar.xz

LICENSE = "GPLv2 & GPLv3 & LGPLv3 & BSD-3-Clause & LGPLv2.1 & MPL-1.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=d20d608cfa3a8343d722c2e69a43bbcd \
                    file://LICENSES/GPL-2.0.txt;md5=0597d53b8f2dba85c174c771eead1324 \
                    file://LICENSES/LGPL-3.0-or-later.txt;md5=6aeb448bc450b9fe4c14e7f1d723e0f4 \
                    file://LICENSES/LGPL-2.1.txt;md5=58536dbdbca839fcfc5366fa07bfa758 \
                    file://LICENSES/GPL-2.0-or-later.txt;md5=cc25c4e718ebe6811a78da4d0fbdddab \
                    file://LICENSES/GPL-3.0-or-later.txt;md5=495c5dccf51c1d4f4cf723450b1af016 \
                    file://LICENSES/GPL-3.0.txt;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LICENSES/LGPL-3.0.txt;md5=3000208d539ec061b899bce1d9ce9404 \
                    file://LICENSES/LGPL-2.1-or-later.txt;md5=388d98f2bb3c60a8a3105be2e7f812ee \
                    file://CMakeScripts/COPYING-CMAKE-SCRIPTS;md5=54c7042be62e169199200bc6477f04d1 \
                    file://src/3rdparty/adaptagrams/libavoid/LICENSE.LGPL;md5=49f14752fbcf469d6889865f91ef8493 \
                    file://src/3rdparty/adaptagrams/libvpsc/COPYING;md5=a6a79b625a6b5fa5d201e59fd0ac98ca \
                    file://src/3rdparty/2geom/COPYING-LGPL-2.1;md5=fad9b3332be894bab9bc501572864b29 \
                    file://src/3rdparty/2geom/LICENSE.md;md5=676991d8aaee00f6ab19d996658dab0a \
                    file://src/3rdparty/2geom/COPYING-MPL-1.1;md5=bfe1f75d606912a4111c90743d6c7325 \
                    file://share/extensions/LICENSE.txt;md5=0597d53b8f2dba85c174c771eead1324 \
                    file://share/themes/LICENSE.txt;md5=cc25c4e718ebe6811a78da4d0fbdddab \
                    file://share/doc/LICENSE;md5=2ddb103ee628b1d6bea487dfcb345f32 \
                    file://buildtools/check_license_headers.py;md5=51ded1f391e8bbf1b7ef2b1d14ec929e \
                    file://testfiles/rendering_tests/fonts/LICENSES;md5=17d85695dbdaed9ae2867f2935b48124"

SRC_URI = "https://inkscape.org/gallery/item/29255/inkscape-${PV}.tar.xz"
SRC_URI[md5sum] = "1852c535a1aec7ca25eca9de110330d7"
SRC_URI[sha256sum] = "aeca0b9d33b5b1cfa9aa70433bdee6a8c3d020ffafc2e6f0c9a60eed7a7978af"

S = "${WORKDIR}/${BPN}-${PV}_2021-09-20_3bf5ae0d25"

DEPENDS = "libxslt readline gsl libpng libx11 boost litehtml gspell jpeg cairo \
           libxml2 glib-2.0 zlib gtk+3 virtual/libintl potrace double-conversion \
           poppler enchant2 aspell libsigc++-2.0 gtkmm3 gtksourceview2 bdwgc \
           gsl intltool glibmm libart-lgpl librsvg librsvg-native popt cups \
           ghostscript freetype fontconfig harfbuzz libsoup-2.4 lcms pango \
           wget libcanberra gdl libcdr libpng librevenge libwpg \
           libwpd libvisio readline gzip libxext libice libxslt"

inherit cmake python3-dir pkgconfig gettext

EXTRA_OECMAKE = ""

HOMEPAGE = "https://inkscape.org"
SUMMARY = "SVG vector editor"

# ERROR: inkscape-1.1.1-r6 do_package_qa: QA Issue: inkscape: /usr/lib/lib2geom.so.1.1.0 contains probably-redundant RPATH /usr/lib [useless-rpaths]
ERROR_QA_remove = "useless-rpaths"

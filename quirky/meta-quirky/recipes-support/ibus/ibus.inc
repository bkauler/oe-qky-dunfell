SUMMARY = "Intelligent Input Bus for Linux/Unix"
HOMEPAGE = "https://github.com/ibus/ibus/wiki"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

inherit autotools-brokensep pkgconfig gobject-introspection gettext gtk-doc vala

# BK 20220914
#PV = "1.5.23+git${SRCPV}"
PV = "1.5.23"

DEPENDS = "unicode-ucd"

# BK 20220914
SRC_URI = "https://github.com/ibus/ibus/releases/download/${PV}/ibus-${PV}.tar.gz \
    file://0001-Do-not-try-to-start-dbus-we-do-not-have-dbus-lauch.patch"

SRC_URI[md5sum] = "2e2cf49d449c532171b9d9698f934a01"
SRC_URI[sha1sum] = "99f093568588b3c7695230768560eb1645ed80de"
SRC_URI[sha256sum] = "b7e8d5bdb7d71a5ba4ee43cdf374675f77121a71c1679c9b9e7e02875bd0e150"

PACKAGECONFIG[appindicator] = "--enable-appindicator,--disable-appindicator,qtbase"
PACKAGECONFIG[dconf] = "--enable-dconf,--disable-dconf,dconf"
PACKAGECONFIG[gtk2] = "--enable-gtk2,--disable-gtk2,gtk+"
PACKAGECONFIG[gtk3] = "--enable-gtk3,--disable-gtk3,gtk+3"
PACKAGECONFIG[wayland]  = "--enable-wayland,--disable-wayland,wayland"
PACKAGECONFIG[vala]  = "--enable-vala,--disable-vala"
PACKAGECONFIG[x11]  = "--enable-xim --enable-ui,--disable-xim --disable-ui,virtual/libx11"

EXTRA_OECONF = " \
    --disable-tests \
    --disable-emoji-dict \
    --disable-python2 \
"

do_configure_prepend() {
    touch ${S}/ChangeLog
    touch ${S}/ABOUT-NLS
}

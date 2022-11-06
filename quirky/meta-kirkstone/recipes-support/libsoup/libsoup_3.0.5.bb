# Recipe created by recipetool
# recipetool create -o libsoup_3.0.5.bb https://download.gnome.org/sources/libsoup/3.0/libsoup-3.0.5.tar.xz

LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2"

SRC_URI = "https://download.gnome.org/sources/libsoup/3.0/libsoup-${PV}.tar.xz"

SRC_URI[md5sum] = "9471bce83c9a019f23966a0e7303ba69"
SRC_URI[sha1sum] = "036d31d6d74f579e455a2638ba30af287e4e9e1f"
SRC_URI[sha256sum] = "f5d143db6830b3825edc2a1c4449d639273b0bfa017a4970871962d9bca22145"

SUMMARY = "An HTTP library implementation in C"
HOMEPAGE = "https://wiki.gnome.org/Projects/libsoup"
SECTION = "x11/gnome/libs"

DEPENDS = "glib-2.0 glib-2.0-native libxml2 sqlite3 libpsl nghttp2"

SHRT_VER = "${@d.getVar('PV').split('.')[0]}.${@d.getVar('PV').split('.')[1]}"

PROVIDES = "libsoup-3.0"
CVE_PRODUCT = "libsoup"

inherit meson gettext pkgconfig upstream-version-is-even gobject-introspection gtk-doc

GIR_MESON_ENABLE_FLAG = 'enabled'
GIR_MESON_DISABLE_FLAG = 'disabled'

# libsoup-gnome is entirely deprecated and just stubs in 2.42 onwards. Disable by default.
PACKAGECONFIG ??= ""
PACKAGECONFIG[gssapi] = "-Dgssapi=enabled,-Dgssapi=disabled,krb5"

EXTRA_OEMESON_append = " -Dvapi=disabled -Dtls_check=false"

GTKDOC_MESON_OPTION = "gtk_doc"

# When built without gnome support, libsoup will contain only one shared lib
# and will therefore become subject to renaming by debian.bbclass. Prevent
# renaming in order to keep the package name consistent regardless of whether
# gnome support is enabled or disabled.
DEBIAN_NOAUTONAME_${PN} = "1"

# glib-networking is needed for SSL, proxies, etc.
RRECOMMENDS_${PN} = "glib-networking"

BBCLASSEXTEND = "native nativesdk"


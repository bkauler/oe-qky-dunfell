# Recipe created by recipetool
# recipetool create -o libvirt_8.9.0.bb https://libvirt.org/sources/libvirt-8.9.0.tar.xz
# crap libvirt 8.9.0 requires meson 0.56.0
# have bumped meson to 0.55.1, libvirt 8.8.0 needs meson 0.54.0

# 20221105 PR_NUM is defined in local.conf, currently r10
PR = "r${@int(PR_NUM) + 1}"

SUMMARY = "Library providing a simple virtualization API"
HOMEPAGE = "https://libvirt.org"

LICENSE = "LGPLv2.1 & GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=4b54a1fd55a448865a0b32d41598759d \
                    file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "https://libvirt.org/sources/libvirt-${PV}.tar.xz"

SRC_URI[md5sum] = "c20121ef8c9297a982dd1f2e529159f3"
SRC_URI[sha256sum] = "eb0cbb6cd199e7a2f341e62f5410ca2daf65a0bf91bd522d951c1a18f0df0fa3"

GIR_MESON_ENABLE_FLAG = 'enabled'
GIR_MESON_DISABLE_FLAG = 'disabled'
#GTKDOC_MESON_OPTION = 'gtk_doc'

inherit meson gobject-introspection gettext features_check pkgconfig gtk-doc

ANY_OF_DISTRO_FEATURES = "${GTK3DISTROFEATURES}"

DEPENDS += "gobject-introspection gobject-introspection-native \
            libxml2 libxml2-native libxslt libxslt-native \
            glib-2.0 glib-2.0-native \
            attr util-linux libcap-ng curl fuse fuse3 libpcap \
            libssh2 libpciaccess readline eudev qemu yajl"


EXTRA_OEMESON += " -Dsystem=true -Ddriver_qemu=enabled -Ddocs=disabled"

do_install_append() {
 cp -a -f ${D}/usr/lib64/* ${D}/usr/lib/
 rm -rf ${D}/usr/lib64
}

# Recipe created by recipetool
# recipetool create -o gdl_3.34.0.bb https://download.gnome.org/sources/gdl/3.34/gdl-3.34.0.tar.xz

LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2"

SRC_URI = "https://download.gnome.org/sources/gdl/3.34/gdl-${PV}.tar.xz"
SRC_URI[md5sum] = "d624f2ca85555402828d5c4a98912b82"
SRC_URI[sha1sum] = "c4711fcd2bf124dcd04fc23de40a669350186028"
SRC_URI[sha256sum] = "858b30f0cdce4c4cb3e8365a7d54ce57c388beff38ea583be5449bc78dda8d02"
SRC_URI[sha384sum] = "5d20aa7bf827f00d7e7454f5d6577a4fffd502f1246b11e6725cd0a490882f69aa6e09fe28928a7e524d6aed5ca051f0"
SRC_URI[sha512sum] = "8c43f330556dae67e4f64e92c3ba5c2e9351d3040d6002bda89f20f9494fc93030abe80072f0cf86280f98e290805154e9ca12aaa7013f0a341d8ea94323d3b0"

#remove, compile fails:  gobject-introspection-native
DEPENDS = "gtk+3 intltool-native libxml2"

inherit pkgconfig gettext autotools

# ref: http://www.linuxfromscratch.org/blfs/view/svn/x/gdl.html
EXTRA_OECONF = "--disable-static --enable-introspection=no"

#20210501 fix for the error, host files access...
do_compile_prepend() {
 sed -i -e 's%\-I$(includedir) % %' ${B}/gdl/Makefile
}

# ERROR: gdl-3.34.0-r2 do_package_qa: QA Issue: gdl: The compile log indicates that host include and/or library paths were used.
XXXdo_package_qa() {
 true
}

HOMEPAGE = "https://gitlab.gnome.org/GNOME/gdl"
SUMMARY = "Gnome development docking library"


# Recipe created by recipetool
# recipetool create -o zarfy_0.1.0.bb https://downloads.sourceforge.net/project/zarfy/zarfy/zarfy%200.1.0/zarfy-0.1.0.tar.gz

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://downloads.sourceforge.net/project/zarfy/zarfy/zarfy%20${PV}/zarfy-${PV}.tar.gz \
  file://010-fix-makefiles-install.patch \
  file://020-increase-maxctrc.patch \
  file://030-add-m-option-and-fixes.patch"
SRC_URI[md5sum] = "34e6356c0adf8026fb8dcebaade2b4f8"
SRC_URI[sha1sum] = "c09bc65d035f9cbe3a74dd886bea9e915e97cb29"
SRC_URI[sha256sum] = "5e79bfa9f2abbe161b016530ede3fbaf90d9f3810a3da05a6b1dd991d7276b40"
SRC_URI[sha384sum] = "0b614a2a2780c01a3049dc4caec9c387cb08445c4a2ff77899d7229f66dfd498feb303be3340bdacc0df9b5195271ad0"
SRC_URI[sha512sum] = "8fd99440874714bcc5f8a8260049040ac6347c33bf9d27587bf4ef5a3d84ee5d05851ddfee240921867135605dd7dd606a109c2ae7f04aa804e91596956e8d35"

DEPENDS = "gtk+ libx11 gdk-pixbuf libglade libxrandr libxml2"

inherit pkgconfig autotools gettext

EXTRA_OECONF = ""

do_configure_prepend() {
 touch README
}

do_compile_prepend() {
 sed -i -e 's%lXrandr%lXrandr -lX11 -lm%' ${B}/src/Makefile
 sed -i -e 's%lXrandr%lXrandr -lX11 -lm%' ${B}/Makefile
}

#crap, stuff is getting installed to the host system! (despite the slackware patch)...
do_install() {
 #oe_runmake install DESTDIR=${D}
 install -d ${D}/usr/bin
 install -m755 ${B}/src/zarfy ${D}/usr/bin/
 install -d ${D}/usr/share/zarfy
 install -m644 ${S}/data/dvi.png ${D}/usr/share/zarfy/
 install -m644 ${S}/data/lcd.png ${D}/usr/share/zarfy/
 install -m644 ${S}/data/monitor_d.png ${D}/usr/share/zarfy/
 install -m644 ${S}/data/monitor.png ${D}/usr/share/zarfy/
 install -m644 ${S}/data/monitor_s.png ${D}/usr/share/zarfy/
 install -m644 ${S}/data/tv.png ${D}/usr/share/zarfy/
 install -m644 ${S}/data/vga.png ${D}/usr/share/zarfy/
 install -m644 ${S}/data/zarfy.glade ${D}/usr/share/zarfy/
 install -m644 ${S}/data/zarfy.png ${D}/usr/share/zarfy/
}

HOMEPAGE = "https://sourceforge.net/projects/zarfy/"
SUMMARY = "A gui to libxrandr. Presents a visual representation of displays"


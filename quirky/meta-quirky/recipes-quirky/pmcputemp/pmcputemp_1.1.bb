
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://licence.txt;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/p/pmcputemp-${PV}.tar.gz"
SRC_URI[md5sum] = "c546c371d24a0e283b4206a63f491ffb"
SRC_URI[sha256sum] = "275654e12f5053475d4e6f3b6e27350eb756038034fb5fa9032f48e76cc2760b"

#not really required, S should already be correct...
S = "${WORKDIR}/${BPN}-${PV}"

DEPENDS = "gtk+ gdk-pixbuf cairo libx11"

# xgettext reads files in src, so need autotools-brokensep...
inherit gettext pkgconfig autotools-brokensep

do_configure () {
	true
}

do_compile () {
	xgettext --keyword="_" pmcputemp.c about.c  -o po/pmcputemp.pot
    ${CC} -c about.c ${CFLAGS} `pkg-config --cflags gtk+-2.0 cairo`
    ${CC} -c pmcputemp.c ${CFLAGS} `pkg-config --cflags gtk+-2.0 cairo`
    ${CC} -o pmcputemp pmcputemp.o about.o ${LDFLAGS} `pkg-config --libs gtk+-2.0 cairo` -lX11
}

do_install () {
	install -d ${D}/usr/share/man/man1
	install -d ${D}/usr/share/doc/nls/pmcputemp
	install -d ${D}/usr/bin
	install -m 755 pmcputemp ${D}/usr/bin
	install -m 755 pmcputemp.sh ${D}/usr/bin
	install -m 644 po/pmcputemp.pot ${D}/usr/share/doc/nls/pmcputemp
	install -m 644 pmcputemp.1 ${D}/usr/share/man/man1/pmcputemp.1
}

HOMEPAGE = "http://www.murga-linux.com/puppy/viewtopic.php?t=98299"
SUMMARY = "Tray applet to monitor cpu temperature"

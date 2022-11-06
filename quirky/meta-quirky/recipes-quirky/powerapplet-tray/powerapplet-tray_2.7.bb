# Recipe created by recipetool
# recipetool create -o powerapplet-tray_2.6.bb http://distro.ibiblio.org/quirky/quirky6/sources/t2/april/powerapplet_tray-2.6.tar.bz2

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://powerapplet_tray.c;md5=7cff1be7ba82f8032f1e3e277ddc7bbe"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/p/powerapplet_tray-2.7.tar.gz"
SRC_URI[md5sum] = "149a2f2e812bd30639ea2d91c1e7cc8d"
SRC_URI[sha256sum] = "ef31b138f148d2d2b64f41b1b509cfccc8c1d1829b9c1bbfadecbb573186261c"

S = "${WORKDIR}/powerapplet_tray-${PV}"

# NOTE: no Makefile found.
DEPENDS = "gtk+"
inherit pkgconfig gettext

do_configure () {
    if [ -f powerapplet_tray ];then
     rm -f powerapplet_tray
    fi
    if [ -f powerapplet_tray.pot ];then
     rm -f powerapplet_tray.pot
    fi
}

do_compile () {
    ${CC} `pkg-config --cflags --libs gtk+-2.0` powerapplet_tray.c -o powerapplet_tray ${CFLAGS} ${LDFLAGS}
    xgettext --keyword="_" powerapplet_tray.c  -o powerapplet_tray.pot
}

do_install () {
    install -d ${D}/root/Startup
    install -m755 powerapplet_tray ${D}/root/Startup
    install -d ${D}/usr/share/doc/nls/powerapplet_tray
    install -m644 powerapplet_tray.pot ${D}/usr/share/doc/nls/powerapplet_tray
}

FILES_${PN} += '/root/Startup'

HOMEPAGE = "https://bkhome.org/news/201910/powerapplettray-updated-to-version-27.html"
SUMMARY = "Battery status monitor for the system tray"

# Recipe created by recipetool
# recipetool create -o synaptic_0.90.2.bb https://github.com/mvo5/synaptic/archive/refs/tags/0.90.2.tar.gz

HOMEPAGE = "https://github.com/mvo5/synaptic/"
SUMMARY = "GUI package manager for apt and dpkg"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

#SRC_URI = "https://github.com/mvo5/synaptic/archive/refs/tags/${PV}.tar.gz"
SRC_URI = "http://deb.debian.org/debian/pool/main/s/synaptic/synaptic_${PV}.tar.xz"

SRC_URI[md5sum] = "9023bd2958aa8a4b3ac023de79bcf643"
SRC_URI[sha256sum] = "88fdfd5c39a0999d2f336e7038b55ebc244869ca53bdafb90b18d785411285e5"

# removed:  rpm
DEPENDS = "apt apt-native intltool-native m4-native vte gtk+3 dpkg xmlto-native"

inherit pkgconfig gettext autotools-brokensep

EXTRA_OECONF = ""

#ERROR: synaptic-0.91.2-r9 do_configure: QA Issue: This autoconf log indicates errors, it looked at host include and/or library paths while determining system capabilities.
#Rerun configure task after fixing this. [configure-unsafe]
#bad hack, ignore...
#ERROR_QA_remove = "configure-unsafe"
#...no, build still fails. it seems may be due to rpm dep -- remove.

do_configure_prepend() {
 sed -i -e 's%[ ]*\-I/usr/include/rpm%%' ${S}/configure.ac
 sed -i -e 's%\-I/usr/include/apt-pkg %%' ${S}/common/Makefile.am
}


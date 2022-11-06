# Recipe created by recipetool
# recipetool create -o alsamixergui_0.9.0rc1-2.bb http://distro.ibiblio.org/pdaxrom/download/1.1.0beta4/src/alsamixergui-0.9.0rc1-2.tar.gz

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

# patches and src are from gentoo...
SRC_URI = "http://distro.ibiblio.org/pdaxrom/download/1.1.0beta4/src/alsamixergui-${PV}.tar.gz \
  file://alsamixergui-0.9.0.1.2-fltk-1.1.patch \
  file://alsamixergui-0.9.0.1.2-gcc34.patch \
  file://segfault-on-exit.patch"

SRC_URI[md5sum] = "24b74dda2cf77c313c6cba9b062c8feb"
SRC_URI[sha1sum] = "74727334543b3f0755b32b3f2e858cc46d905826"
SRC_URI[sha256sum] = "6053a352580fa90fc5d35a2edce138759726acd72283ff6797cf7720632481cd"
SRC_URI[sha384sum] = "8e3d41e3b27b511ac7b34d065a8e0b7a865c4f56528d952872fd6bf209631b525d3811d804a48e45d8be120c02767ccc"
SRC_URI[sha512sum] = "c1eac24c87e3d3dd8a582312a24e4f517d084845a3a0cd0a7253e3386685d72a691fe138853002b0277361d397271fbc9f445fd5ad0f5d604e5ad022a408d6b7"

DEPENDS = "fltk xserver-xorg gnutls jpeg libxtst gettext-native alsa-lib"

inherit autotools

#override OE...
CFLAGS_append = " -Wno-error=format-security "

EXTRA_OECONF = "--disable-alsatest"

do_configure_append() {
 sed -i -e 's%Werror=%Wno-error=%g' ${B}/Makefile
 sed -i -e 's%Werror=%Wno-error=%g' ${B}/src/Makefile
}

HOMEPAGE = "https://packages.debian.org/sid/alsamixergui"
SUMMARY = "gui for alsamixer"

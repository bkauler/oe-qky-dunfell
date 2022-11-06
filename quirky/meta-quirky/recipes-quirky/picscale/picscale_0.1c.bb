# Recipe created by recipetool
# recipetool create -o picscale_0.1c.bb http://distro.ibiblio.org/easyos/source/oe/pyro/picscale-0.1c.tar.gz
# 20220809 attempting to fix compile, using old bacon 2.4.0.
# 20220811 now have bacon 4.5

# 20220811 PR_NUM is defined in local.conf, currently r9
PR = "r${@int(PR_NUM) + 1}"

HOMEPAGE = "http://basic-converter.proboards.com/thread/1014/compile-picscale-openembedded"
SUMMARY = "Scale png images from the command line."

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2"

SRC_URI = "http://distro.ibiblio.org/easyos/source/oe/pyro/picscale-${PV}.tar.gz"

SRC_URI[md5sum] = "fb58172c96973da7d80e37fbe93d2735"
SRC_URI[sha256sum] = "e5d97400f32c7cf8ce4ba0d19ae6bdd246477d01864b948527add6349096c0e5"

DEPENDS = "gtk+ gdk-pixbuf bash bacon-native"

do_configure () {
 true
}

do_compile () {
 echo "CC=${CC}"
 echo "CFLAGS=${CFLAGS}"
 echo "LDFLAGS=${LDFLAGS}"
 
 mkdir -p build
 bacon -d build -c "${CC}" -o "${CFLAGS}" -l "${LDFLAGS}" picscale.bac
}

do_install () {
    install -d ${D}/usr/bin
    install -m755 build/picscale ${D}/usr/bin
}


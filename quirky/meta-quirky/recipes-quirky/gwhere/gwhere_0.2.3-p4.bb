# Recipe created by recipetool
# recipetool create -o gwhere_0.2.3-p4.bb http://distro.ibiblio.org/quirky/quirky6/sources/t2/april/gwhere-0.2.3-patched_4.tar.bz2

#PR = "r1"
# 20210121 PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "http://distro.ibiblio.org/quirky/quirky6/sources/t2/april/gwhere-0.2.3-patched_4.tar.bz2 \
           file://gwhere-fix-aarch64.patch"
SRC_URI[md5sum] = "772358f395a1ce0514cf6b57c9ed1b60"
SRC_URI[sha256sum] = "971efc6279255ae16c07fe166b1f736edcdc062ef71ddf91f13e208ef2a14036"

S = "${WORKDIR}/${BPN}-0.2.3-patched_4"

DEPENDS = "zlib gtk+ glib-2.0"

inherit gettext autotools-brokensep pkgconfig

#20200921 override OE...
CFLAGS_append = " -Wno-error=format-security "

# BK '--enable-nls' broken... 20200921 --disable-debug*
EXTRA_OECONF = "--enable-gtk20 --disable-gtktest --disable-nls --disable-static --disable-debug --disable-debug-all"

# "reconfig" is broken, just run 'configure'...
do_configure() {
    oe_runconf
    #compile still tries to do stuff in folder 'intl', broken... this Makefile does nothing...
    echo -e 'all:\n\nall-yes:\n\nall-no:\n\ncheck:\n\ninstall:\n\ninstallcheck:\n\nuninstall:\n\nid:\n\nclean:\n\ndist distdir:\n\ndist-libc:\n\nMakefile:\n\n' > ${S}/intl/Makefile
    #fix Makefiles
    sed -i -e 's%^CFLAGS =%CFLAGS += -fPIC%' ${S}/src/Makefile
    sed -i -e 's%^LDFLAGS =%LDFLAGS += -fPIC%' ${S}/src/Makefile
    sed -i -e 's%^CFLAGS =%CFLAGS += -fPIC%' ${S}/src/tools/Makefile
    sed -i -e 's%^LDFLAGS =%LDFLAGS += -fPIC%' ${S}/src/tools/Makefile
    sed -i -e 's%^CFLAGS =%CFLAGS += -fPIC%' ${S}/src/res/Makefile
    sed -i -e 's%^LDFLAGS =%LDFLAGS += -fPIC%' ${S}/src/res/Makefile
    sed -i -e 's%^CFLAGS =%CFLAGS += -fPIC%' ${S}/src/plugins/Makefile
    sed -i -e 's%^LDFLAGS =%LDFLAGS += -fPIC%' ${S}/src/plugins/Makefile
    sed -i -e 's%^CFLAGS =%CFLAGS += -fPIC%' ${S}/src/gui/Makefile
    sed -i -e 's%^LDFLAGS =%LDFLAGS += -fPIC%' ${S}/src/gui/Makefile
    sed -i -e 's%^CFLAGS =%CFLAGS += -fPIC%' ${S}/src/db/Makefile
    sed -i -e 's%^LDFLAGS =%LDFLAGS += -fPIC%' ${S}/src/db/Makefile
    sed -i -e 's%^CFLAGS =%CFLAGS += -fPIC%' ${S}/src/data/Makefile
    sed -i -e 's%^LDFLAGS =%LDFLAGS += -fPIC%' ${S}/src/data/Makefile
    
    #180420
    for aMake in `find ${S} -type f -name Makefile`
    do
     sed -i -e 's%^GW_PROG_PATH_MOUNT =.*%GW_PROG_PATH_MOUNT = /bin/mount%' ${aMake}
     sed -i -e 's%^GW_PROG_PATH_UMOUNT =.*%GW_PROG_PATH_UMOUNT = /bin/umount%' ${aMake}
    done
    sed -i -e 's%GW_PROG_PATH_MOUNT%"/bin/mount"%' ${S}/src/gwdevicemanager.c
    sed -i -e 's%GW_PROG_PATH_UMOUNT%"/bin/umount"%' ${S}/src/gwdevicemanager.c
    
    #needs -lgmodule-2.0
    sed -i -e 's%^GTK_LIBS = %GTK_LIBS = -lgmodule-2.0 %' ${S}/src/Makefile
    sed -i -e 's%^LIBS = %LIBS = -lgmodule-2.0 %' ${S}/src/Makefile
}

#20200921 tried --disable-debug, made no difference, have to delete these:
do_install_append() {
 rm -rf ${D}/usr/share/gwhere/plugins/catalog/.debug
 rm -rf ${D}/usr/share/gwhere/plugins/descr/.debug
 rm -rf ${D}/usr/bin/.debug
}

#180420 get QA errors, files in wrong place...
INSANE_SKIP_${PN} += "dev-so libdir"

# Recipe created by recipetool
# recipetool create -o lives_20211222.bb https://distro.ibiblio.org/easyos/source/alphabetical/l/lives-20211222.tar.gz

LICENSE = "GPLv3 & LGPLv3 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949 \
                    file://COPYING.LGPL;md5=59cf55ceb3219acc195418308b48f82e \
                    file://src/giw/COPYING;md5=22c72e3134a91cc1a6afc9e296b50069 \
                    file://lives-plugins/plugins/encoders/COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949 \
                    file://lives-plugins/plugins/playback/video/COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949 \
                    file://lives-plugins/weed-plugins/COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/l/lives-${PV}.tar.gz \
           file://cross-compile-fix.patch"

SRC_URI[md5sum] = "607740eb4a0f615a8de0d502e85838ba"
SRC_URI[sha256sum] = "c430c8675aa7e43bdd8c1524518821953b17256fdac37af2c10e4f568eb379bc"

# NOTE: unable to map the following pkg-config dependencies:
#  libweed-compat libvisual libweed-plugin liboil-0.3 libweed-utils libvisual-0.4 
#  gdk-wayland-3.0 libprojectM libfreenect opencv4 libweed libexplain opencv
DEPENDS = "glib-2.0 gtk+3 libtheora fftw unicap libsdl libxrender libpng \
           libogg ffmpeg mesa mjpegtools orc libdv pulseaudio zlib libx11 \
           alsa-lib schroedinger libtirpc ghostscript unicap jack \
           libebml glib-2.0-native libdv sox mpv libmatroska v4l-utils \
           libsdl-mixer libsdl-ttf libsdl-gfx bzip2 jpeg cups cups-filters \
           imagemagick"

#error at final link step with separate 'build' folder, so use 'autotools-brokensep'
#| make[1]: *** No rule to make target '/mnt/sda1/nvme/oe-builds/oe-quirky/build-amd64/tmp/work/nocona-64-poky-linux/lives/20211222-r6/build/../lives-20211222/libweed/libweed.la', needed by 'lives-exe'.  Stop.

inherit gettext perlnative pkgconfig autotools-brokensep

EXTRA_OECONF = "--disable-doxygen --enable-threads=posix"

#attempting to fix:
# error: ../../libOSC/client/.libs/libOSC_client.a: No such file or directory
#PARALLEL_MAKE = "-j1"
#No, this fixes it:
# ref: https://www.yoctoproject.org/docs/current/ref-manual/ref-manual.html
DISABLE_STATIC = ""

do_configure_prepend() {
 #ERROR: Use of AM_GNU_GETTEXT without [external] argument is no longer supported.
 sed -i -e 's%^AM_GNU_GETTEXT$%AM_GNU_GETTEXT([external])%' ${S}/configure.ac
}

do_compile_prepend() {
 #| make[1]: Entering directory '/mnt/sda1/nvme/oe-builds/oe-quirky/build-amd64/tmp/work/nocona-64-poky-linux/lives/20211222-r6/build/intl'
 #| make[1]: *** No rule to make target 'all-@USE_INCLUDED_LIBINTL@', needed by 'all'.  Stop.
 sed -i -e 's%@USE_INCLUDED_LIBINTL@%no%' ${B}/intl/Makefile
 sed -i -e 's%@BUILD_INCLUDED_LIBINTL@%no%' ${B}/intl/Makefile
 
 #../../lives-20211222/src/intents.c:8:10: fatal error: weed-utils.h: No such file or directory
 #     8 | #include "weed-utils.h"
 cp -a -f ${S}/libweed/weed.h ${S}/src/
 cp -a -f ${S}/libweed/weed-utils.h ${S}/src/
 
 #| /mnt/sda1/nvme/oe-builds/oe-quirky/build-amd64/tmp/work/nocona-64-poky-linux/lives/20211222-r6/recipe-sysroot-native/usr/bin/x86_64-poky-linux/../../libexec/x86_64-poky-linux/gcc/x86_64-poky-linux/9.3.0/ld: /usr/lib/libjack.so: undefined reference to `PW_LOG_TOPIC_DEFAULT'
 #| collect2: error: ld returned 1 exit status
 sed -i -e 's%-L/usr/lib%%' ${S}/Makefile
 sed -i -e 's%-L/usr/lib%%' ${S}/src/Makefile
 
 #fix typo
 sed -i -e 's%last_fryame%last_frame%' ${S}/lives-plugins/plugins/decoders/mkv_decoder.c
}

#ERROR: lives-20211222-r6 do_package_qa: QA Issue: /usr/lib/lives/plugins/effects/rendered/wave contained in package lives requires /usr/bin/perl, but no providers found in RDEPENDS_lives? [file-rdeps]
ERROR_QA_remove = "file-rdeps"

HOMEPAGE = "http://lives-video.com/"
SUMMARY = "Video editing"

SUMMARY = "Open Source multimedia player"
DESCRIPTION = "mpv is a fork of mplayer2 and MPlayer. It shares some features with the former projects while introducing many more."
SECTION = "multimedia"
HOMEPAGE = "http://www.mpv.io/"
# BK 20200920 bumped to 0.32.0, from 0.27.2
# BK 20201013 remove pulseaudio dep.
# BK 20210407 added jack dep. bring back pulseaudio dep.

# BK 20180623 added: mpg123 xvidcore  20200920 added x265
# added deps as used in xine-lib + more... removed xsp
# 20211027 added pulseaudio
DEPENDS = "zlib ffmpeg jpeg virtual/libx11 libxv \
           libxscrnsaver libv4l libxinerama \
           libxext fontconfig freetype libx11 librsvg libpng alsa-lib \
           libdvdnav faac faad2 flac gdk-pixbuf mesa libglu liba52 libmad libmng \
           libtheora libva libvdpau libvorbis libogg libvpx libsdl libsdl-image \
           libsdl-mixer libsdl-ttf speex libmodplug vcdimager wavpack x264 libxcb \
           libxvmc lame lcms libcdio libraw1394 libavc1394 libdc1394 mpeg2dec \
           schroedinger taglib openssl libsamplerate0 libbluray mesa libdvdread \
           libdvdcss libcddb libcdio-paranoia libarchive libdrm libwebp \
           mpg123 xvidcore x265 jack pulseaudio"

REQUIRED_DISTRO_FEATURES = "x11"

LICENSE = "LGPLv2.1 & GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=4b54a1fd55a448865a0b32d41598759d \
                    file://LICENSE.GPL;md5=91f1cb870c1cc2d31351a4d2595441cb"

# While this item does not require it, it depends on ffmpeg which does
LICENSE_FLAGS = "commercial"

SRC_URI = "https://github.com/mpv-player/mpv/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "1f7d23afe7a8639dedc9f7beef4e90d7"
SRC_URI[sha1sum] = "5b69ea34dd5f8d209acd5266415c7bc00ab83341"
SRC_URI[sha256sum] = "9163f64832226d22e24bbc4874ebd6ac02372cd717bef15c28a0aa858c5fe592"
SRC_URI[sha384sum] = "b86030bccfcb4e01194548aea9e4c4ee0015571884886cf70fa038e8032f2bcf2c3903e2382f420536f7b347d369e3d2"
SRC_URI[sha512sum] = "f6426c0254ec0bf2f120e2196904f1e15fe17032b06764abca1d5e074f0cabb452eaf1cd09f8fd9b25b591accee7b881bfc3b06c19d5c98980305c4712486bd6"

#inherit waf pkgconfig pythonnative distro_features_check
inherit waf pkgconfig features_check mime-xdg python3native

# removed...
## Note: both lua and libass are required to get on-screen-display (controls)
#PACKAGECONFIG ??= " \
#    lua \
#    libass \
#    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)} \
#"
PACKAGECONFIG[drm] = "--enable-drm,--disable-drm,libdrm"
PACKAGECONFIG[gbm] = "--enable-gbm,--disable-gbm,virtual/mesa"
PACKAGECONFIG[lua] = "--enable-lua,--disable-lua,lua luajit"
PACKAGECONFIG[libass] = "--enable-libass,--disable-libass,libass"
PACKAGECONFIG[libarchive] = "--enable-libarchive,--disable-libarchive,libarchive"
PACKAGECONFIG[jack] = "--enable-jack, --disable-jack, jack"
PACKAGECONFIG[vaapi] = "--enable-vaapi, --disable-vaapi,libva"
PACKAGECONFIG[vdpau] = "--enable-vdpau, --disable-vdpau,libvdpau"
PACKAGECONFIG[wayland] = "--enable-wayland, --disable-wayland,wayland libxkbcommon"

SIMPLE_TARGET_SYS = "${@'${TARGET_SYS}'.replace('${TARGET_VENDOR}', '')}"

# removed: --disable-encoding  20200920 changed --enable-pulse
#20200920 removed, no longer avail: --enable-dvdread --disable-vapoursynth-lazy
#20201013 --disable-pulse  20210407 --enable-jack 20211027 --enable-pulse
EXTRA_OECONF = " \
    --prefix=${prefix} \
    --target=${SIMPLE_TARGET_SYS} \
    --confdir=${sysconfdir}/mpv \
    --datadir=${datadir} \
    --disable-manpage-build \
    --enable-gl \
    --disable-libsmbclient \
    --enable-libbluray \
    --enable-dvdnav \
    --enable-cdda \
    --disable-uchardet \
    --disable-rubberband \
    --enable-lcms2 \
    --disable-vapoursynth \
    --disable-debug-build \
    --enable-pulse \
    --enable-libmpv-shared \
    --enable-drm \
    --enable-alsa \
    --enable-libarchive \
    --disable-lua \
    --enable-jack \
    ${PACKAGECONFIG_CONFARGS} \
"

get_waf() {
    cd ${S}
    ./bootstrap.py
    sed -i -e 's|/usr/bin/env python|/usr/bin/env python3|g' ${S}/waf
    chmod +x ${S}/waf
    cd -
}

do_patch[postfuncs] += "get_waf"

FILES_${PN} += "${datadir}/icons"

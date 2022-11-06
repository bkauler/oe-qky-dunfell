
EXTRA_OECONF_append = ""

#| ../doltlibtool: line 19: /mnt/sda1/nvme/oe-builds/oe-quirky/build-amd64/tmp/work/nocona-64-poky-linux/vlc/3.0.12-r6/build/compat/../libtool: No such file or directory
#| make[3]: *** [Makefile:1319: libcompat.la] Error 127
# add libtool-native... ...NO, still get the error. instead see do_compile_prepend

DEPENDS_append = " pulseaudio v4l-utils x265 libarchive libtool-native"

DEPENDS_remove = "avahi"
DEPENDS_remove = "fribidi"

#already have in main recipe:
# live555 dc1394 dv1394 notify fontconfig fluidsynth freetype dvdread png x11 x264

#configure: error: Library freerdp >= 1.0.1 needed for freerdp was not found
# hmmm, oe dunfell has 2.0. anyway, remove "freerdp":

PACKAGECONFIG_append = " mad a52 jack libass libva speex vpx qt5 dvdnav \
                         sftp vorbis ogg svg svgdec notify vdpau"

do_compile_prepend() {
 #see error msg above.
 LIBTOOLFILE="$(find ${B} -mindepth 1 -maxdepth 1 -type f -name '*-poky-linux-libtool')"
 cp -a ${LIBTOOLFILE} ${B}/libtool
}

#20220101
do_install_append() {
 echo '[Desktop Entry]
Encoding=UTF-8
Name=VLC multimedia player
Icon=/usr/share/icons/hicolor/32x32/apps/vlc.png
Comment=VLC Audio Video Player
Exec=vlc
Terminal=false
Type=Application
Categories=X-Multimedia-mediaplayer
GenericName=VLC
MimeType=application/ogg;application/x-ogg;audio/ogg;audio/vorbis;audio/x-vorbis;audio/x-vorbis+ogg;video/ogg;video/x-ogm;video/x-ogm+ogg;video/x-theora+ogg;video/x-theora;audio/x-speex;audio/opus;application/x-flac;audio/flac;audio/x-flac;audio/x-ms-asf;audio/x-ms-asx;audio/x-ms-wax;audio/x-ms-wma;video/x-ms-asf;video/x-ms-asf-plugin;video/x-ms-asx;video/x-ms-wm;video/x-ms-wmv;video/x-ms-wmx;video/x-ms-wvx;video/x-msvideo;audio/x-pn-windows-acm;video/divx;video/msvideo;video/vnd.divx;video/avi;video/x-avi;application/vnd.rn-realmedia;application/vnd.rn-realmedia-vbr;audio/vnd.rn-realaudio;audio/x-pn-realaudio;audio/x-pn-realaudio-plugin;audio/x-real-audio;audio/x-realaudio;video/vnd.rn-realvideo;audio/mpeg;audio/mpg;audio/mp1;audio/mp2;audio/mp3;audio/x-mp1;audio/x-mp2;audio/x-mp3;audio/x-mpeg;audio/x-mpg;video/mp2t;video/mpeg;video/mpeg-system;video/x-mpeg;video/x-mpeg2;video/x-mpeg-system;application/mpeg4-iod;application/mpeg4-muxcodetable;application/x-extension-m4a;application/x-extension-mp4;audio/aac;audio/m4a;audio/mp4;audio/x-m4a;audio/x-aac;video/mp4;video/mp4v-es;video/x-m4v;application/x-quicktime-media-link;application/x-quicktimeplayer;video/quicktime;application/x-matroska;audio/x-matroska;video/x-matroska;video/webm;audio/webm;audio/3gpp;audio/3gpp2;audio/AMR;audio/AMR-WB;video/3gp;video/3gpp;video/3gpp2;x-scheme-handler/mms;x-scheme-handler/mmsh;x-scheme-handler/rtsp;x-scheme-handler/rtp;x-scheme-handler/rtmp;x-scheme-handler/icy;x-scheme-handler/icyx;application/x-cd-image;x-content/video-vcd;x-content/video-svcd;x-content/video-dvd;x-content/audio-cdda;x-content/audio-player;application/ram;application/xspf+xml;audio/mpegurl;audio/x-mpegurl;audio/scpls;audio/x-scpls;text/google-video-pointer;text/x-google-video-pointer;video/vnd.mpegurl;application/vnd.apple.mpegurl;application/vnd.ms-asf;application/vnd.ms-wpl;application/sdp;audio/dv;video/dv;audio/x-aiff;audio/x-pn-aiff;video/x-anim;video/x-nsv;video/fli;video/flv;video/x-flc;video/x-fli;video/x-flv;audio/wav;audio/x-pn-au;audio/x-pn-wav;audio/x-wav;audio/x-adpcm;audio/ac3;audio/eac3;audio/vnd.dts;audio/vnd.dts.hd;audio/vnd.dolby.heaac.1;audio/vnd.dolby.heaac.2;audio/vnd.dolby.mlp;audio/basic;audio/midi;audio/x-ape;audio/x-gsm;audio/x-musepack;audio/x-tta;audio/x-wavpack;audio/x-shorten;application/x-shockwave-flash;application/x-flash-video;misc/ultravox;image/vnd.rn-realpix;audio/x-it;audio/x-mod;audio/x-s3m;audio/x-xm;application/mxf;' > ${D}/usr/share/applications/vlc.desktop
}

SUMMARY = "Video player"


#20210615
DEPENDS += " zip-native neon neon-native"

#20210616 explicitly disable gstreamer...
EXTRA_OECONF += " --with-system-neon --disable-gstreamer-1-0"

#um, think need to do complete oe recompile, as host changed from glibc 2.31 to 2.33...
XXXdo_configure_prepend() {
 #weird, zip in zip-native doesn't work...
 cp -a -f /usr/bin/zip ${WORKDIR}/recipe-sysroot-native/usr/bin/
}

do_compile_prepend() {
 #have saved all the tarballs that libreoffice downloads...
 mkdir -p ${S}/external/tarballs
 #$TMPDIR is in the 'tmp' folder...
 #20210826 first time, have not saved the tarballs!... ***DO MANUALLY AFTER BUILD***
 if [ -d ${TMPDIR}/../../../tarballs-libreoffice-native ];then
  cp -a -u ${TMPDIR}/../../../tarballs-libreoffice-native/* ${S}/external/tarballs/
 fi
 #disable this check...
 sed -i -e 's%^check-if-root:%check-if-root:\n\ncheck-if-rootXXX:%' ${B}/Makefile
}

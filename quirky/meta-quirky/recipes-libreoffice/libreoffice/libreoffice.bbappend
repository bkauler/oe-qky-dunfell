#20220129 forgot the internationalization tarball, rebuild...
# bump r7 to r8... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

#20210615

#20210616 removed:     gstreamer1.0-plugins-base 
#20210617 added: libxinerama libx11 gtk+3
#         removed     clucene-core 
#         added neon neon-native
#20210617 ...works, refine:
#         added: ghostscript ijs
DEPENDS = " \
    python3-lxml-native \
    ccache-native \
    archive-zip-native \
    gperf-native \
    bison-native \
    zip-native \
    ${BPN}-native \
    \
    curl \
    icu \
    expat \
    poppler \
    harfbuzz \
    openldap \
    nss \
    zlib \
    jpeg \
    libpng \
    apr \
    serf \
    libatomic-ops \
    lcms \
    harfbuzz \
    cppunit \
    glew \
    openssl \
    cups \
    \
    glm \
    redland \
    libabw \
    libwps \
    libwpg \
    libwpd \
    libcdr \
    librevenge \
    libcmis \
    libfreehand \
    libe-book \
    libmwaw \
    libetonyek \
    libvisio \
    libmspub \
    libpagemaker \
    libodfgen \
    libgltf \
    libexttextcat \
    vigra \
    hunspell \
    mythes \
    hyphen \
    graphite2 \
    liblangtag \
    lpsolve \
    gpgme \
    mdds-1.5 \
    neon \
    neon-native \
    libxinerama \
    libx11 \
    gtk+3 \
    ghostscript ijs \
"

# took out     --with-system-clucene 
# added --with-system-neon
# 20210616 added:  --disable-gstreamer-1-0
# 20210617 added: --with-theme=colibre
# 20210617 ...works, refine more:
#          added: --without-fonts --without-krb5 --disable-odk
#          changed --with-lang=ALL to --with-lang="en-GB fr de nn nb"
#          added: --with-locales="en fr de nn nb" --disable-scripting-javascript --disable-scripting-beanshell
#          added: --disable-lotuswordpro --enable-cups --disable-vlc --disable-qt5
#          removed: --enable-python=system added: --disable-sdremote --disable-dbus --enable-python=no
#          added: --disable-debug --disable-evolution2 --enable-pdfimport
#          added: --enable-firebird-sdbc
EXTRA_OECONF = " \
    --without-doxygen \
    --enable-release-build \
    --disable-skia \
    ${@oe.utils.parallel_make_argument(d, '--with-parallelism=%d')} \
    \
    --with-system-boost \
    --with-boost-date-time=boost_date_time \
    --with-boost-filesystem=boost_filesystem \
    --with-boost-locale=boost_locale\
    --with-boost-iostreams=boost_iostreams\
    --with-boost-system=boost_system\
    \
    --with-system-cairo \
    --with-system-epoxy \
    --with-system-curl \
    --with-system-libpng \
    --with-system-jpeg \
    --with-system-libxml \
    --with-system-graphite \
    --with-system-harfbuzz \
    --with-system-icu \
    --with-system-expat \
    --with-system-lcms2 \
    --with-system-nss \
    --with-system-cppunit \
    --with-system-libabw \
    --with-system-libcdr \
    --with-system-libebook \
    --with-system-libfreehand \
    --with-system-hunspell \
    --with-system-mythes \
    --with-system-libcmis \
    --with-system-libpagemaker \
    --with-system-glm \
    --with-system-libetonyek \
    --with-system-libvisio \
    --with-system-libexttextcat \
    --with-system-altlinuxhyph \
    --with-system-librevenge \
    --with-system-libabw \
    --with-system-libcdr \
    --with-system-libebook \
    --with-system-libfreehand \
    --with-system-liblangtag \
    --with-system-zlib \
    --with-system-lpsolve \
    --with-system-libwpd \
    --with-system-libwpg \
    --with-system-libwps \
    --with-system-libcdr \
    --with-system-libmspub \
    --with-system-libmwaw \
    --with-system-mdds \
    --without-java \
    --with-lang="en-GB fr de nn nb" \
    \
    --disable-coinmp \
    --with-tls=nss \
    --without-galleries \
    \
    --with-system-poppler \
    --with-system-openldap \
    --with-system-apr \
    --with-system-serf \
    --with-system-libatomic_ops \
    --with-system-icu \
    --with-system-expat \
    --with-system-curl \
    --with-system-openssl \
    \
    --with-system-cppunit \
    --with-system-glm \
    --with-system-redland \
    --with-system-libabw \
    --with-system-libcmis \
    --with-system-libebook \
    --with-system-libetonyek \
    --with-system-libvisio \
    --with-system-libpagemaker \
    --with-system-libodfgen \
    --with-system-libexttextcat \
    --with-system-mythes \
    --with-system-altlinuxhyph \
    --with-system-gpgmepp \
    \
    --with-external-dict-dir=${datadir}/hunspell \
    --with-system-dicts \
    --with-system-neon \
    --disable-gstreamer-1-0 \
    --with-theme=colibre \
    \
    --without-fonts \
    --without-krb5 \
    --disable-odk \
    --with-locales="en fr de nn nb" \
    --disable-scripting-javascript \
    --disable-scripting-beanshell \
    --disable-lotuswordpro \
    --enable-cups \
    --disable-vlc \
    --disable-qt5 \
    --disable-sdremote \
    --disable-dbus \
    --enable-python=no \
    --disable-debug \
    --disable-evolution2 \
    --enable-pdfimport \
    --enable-firebird-sdbc \
"

# removed     postgresql 
PACKAGECONFIG = " \
    gtk3 \
    mariadb \
"

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
 if [ -d ${TMPDIR}/../../../tarballs-libreoffice ];then
  cp -a -u ${TMPDIR}/../../../tarballs-libreoffice/* ${S}/external/tarballs/
 fi
 #disable this check...
 sed -i -e 's%^check-if-root:%check-if-root:\n\ncheck-if-rootXXX:%' ${B}/Makefile
 
 #attempting hacks for compile fails...
 mkdir -p ${S}/icon-themes/export
 mkdir -p ${B}/workdir/CustomTarget/postprocess/images
 touch ${B}/workdir/CustomTarget/postprocess/images/colibre_links.txt.tmp
 touch ${B}/workdir/CustomTarget/postprocess/images/export_links.txt.tmp
}

# do_install in libreoffice.bb fails, it has been renamed to disable it.
# replaced with this one...
do_install() {
 #probably should use 'install' utility, and $datadir and $libdir,
 #but this is easier...
 
 mkdir -p ${D}/usr/lib
 cp -a ${B}/instdir ${D}/usr/lib/libreoffice
 rm -rf ${D}/usr/lib/libreoffice/sdk
 #this is broken result of hack during compile...
 rm -f ${D}/usr/lib/libreoffice/share/config/images_export.zip
 
 mkdir -p ${D}/usr/share
 cp -a ${S}/sysui/desktop/appstream-appdata ${D}/usr/share/appdata
 mkdir -p ${D}/usr/share/icons/hicolor
 cp -a ${S}/sysui/desktop/icons/hicolor/48x48 ${D}/usr/share/icons/hicolor/
 cp -a ${S}/sysui/desktop/menus ${D}/usr/share/applications
 mkdir -p ${D}/usr/share/fonts/TTF
 ln -s ../../../lib/libreoffice/share/fonts/truetype/opens___.ttf ${D}/usr/share/fonts/TTF/opens___.ttf
 
 #from original do_install...
     # install LibreOfficeKit (gobject-introspection) manually - became necessary since 6.4.x
    install -m 0755 -d ${D}${libdir}/girepository-1.0
    install -m 0644 ${B}/workdir/CustomTarget/sysui/share/libreoffice/LOKDocView-0.1.typelib ${D}${libdir}/girepository-1.0/
    install -m 0755 -d ${D}${libdir}/gir-1.0
    install -m 0644 ${B}/workdir/CustomTarget/sysui/share/libreoffice/LOKDocView-0.1.gir ${D}${libdir}/gir-1.0/
    install -m 0755 ${B}/instdir/program/liblibreofficekitgtk.so ${D}${libdir}/
    # install LibreOfficeKit headers
    install -m 0755 -d ${D}${includedir}/LibreOfficeKit
    install -m 0644 ${S}/include/LibreOfficeKit/* ${D}${includedir}/LibreOfficeKit/

    # unoconv
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/git/unoconv/unoconv ${D}/${bindir}

    # remove some unneeded files
    rm -rf ${D}${libdir}/libreoffice/readmes
    rm -rf ${D}${libdir}/libreoffice/share/theme_definitions/ios
    rmdir ${D}${libdir}/libreoffice/share/theme_definitions

}


# BK 20201218 do_install fails. the culprit seems to be doc/Makefile
# as using autotools-brokensep, to test rebuilds, may need to erase
# prior history, like this:
# # bitbake -c cleansstate e2fsprogs-native
# # bitbake -c cleansstate e2fsprogs
# # bitbake -c clean e2fsprogs-native
# # bitbake -c clean e2fsprogs
# then can build...
# # bitbake -c build e2fsprogs

#try brokensep... no...
#inherit autotools-brokensep gettext texinfo pkgconfig multilib_header update-alternatives ptest

#add texinfo-native... no...
#DEPENDS = "util-linux attr texinfo-native"

#getting a do_install error...
XXXdo_install_prepend() {
 ln -s ../git ${B}/git
 ln -s ../config ${B}/doc/config
 ln -s ../config ${B}/util/config
 ln -s ../config ${B}/lib/config
 
 #no, still getting this missing:
 #tmp/work/x86_64-linux/e2fsprogs-native/1.45.4-r0/recipe-sysroot-native/usr/share/info/libext2fs.info*
 #desparation, castrate doc/Makefile...
 echo -e 'all:\n\ncheck:\n\nfullcheck:\n\ngcc-wall-new:\n\ngcc-wall:\n\nstatic-check:\n\nstatic-check-all:\n\nMakefile:\n\ncoverage.txt:\n\nclean:\n\ndepend:\n\ninstall-doc-libs:\n\nuninstall-doc-libs:\n\nuninstall-doc-libs:\n\nlibext2fs.dvi:\n\nlibext2fs.ps:\n\nlibext2fs.pdf:\n\nlibext2fs.html:\n\nclean:\n\nclean-all:\n\nclean-final:\n\nclean-tex:\n\nclean-backup:\n\nclean-tarfiles:\n\nclean-html:\n\n' > ${B}/doc/Makefile
}

# final desparation, a manual install...
do_install () {
 install -d ${D}${usrdir}
 install -d ${D}${libdir}
 install -d ${D}${libdir}/pkgconfig
 install -d ${D}${base_libdir}
 install -d ${D}${bindir}
 install -d ${D}${base_bindir}
 install -d ${D}${sbindir}
 install -d ${D}${base_sbindir}
 install -d ${D}${includedir}
 install -d ${D}${includedir}/e2p
 install -d ${D}${includedir}/et
 install -d ${D}${includedir}/ext2fs
 install -d ${D}${includedir}/ss
 install -d ${D}/etc
 install -m755 ${B}/misc/chattr ${D}${base_bindir}/chattr.e2fsprogs
 install -m755 ${B}/misc/mke2fs ${D}${base_sbindir}/
 install -m755 ${B}/misc/mke2fs ${D}${base_sbindir}/mke2fs.e2fsprogs
 install -m755 ${B}/misc/mke2fs ${D}${base_sbindir}/mkfs.ext2
 install -m755 ${B}/misc/mke2fs ${D}${base_sbindir}/mkfs.ext2.e2fsprogs
 install -m755 ${B}/misc/mke2fs ${D}${base_sbindir}/mkfs.ext3
 install -m755 ${B}/misc/mke2fs ${D}${base_sbindir}/mkfs.ext4
 install -m755 ${B}/misc/tune2fs ${D}${base_sbindir}/tune2fs
 install -m755 ${B}/misc/tune2fs ${D}${base_sbindir}/tune2fs.e2fsprogs
 for aFILE in badblocks dumpe2fs e2freefrag e2image e2undo e4crypt e4defrag filefrag logsave
 do
  install -m755 ${B}/misc/${aFILE} ${D}${base_sbindir}/
 done
 install -m755 ${B}/misc/lsattr ${D}${bindir}/
 install -m755 ${B}/e2fsck/e2fsck ${D}${base_sbindir}/
 install -m755 ${B}/debugfs/debugfs ${D}${base_sbindir}/
 install -m755 ${B}/resize/resize2fs ${D}${base_sbindir}/
 install -m644 ${B}/misc/mke2fs.conf ${D}/etc/
 for aLIB in libcom_err.a libcom_err.so.2.1 libe2p.a libe2p.so.2.3 libext2fs.a libext2fs.so.2.4 libss.a libss.so.2.0 libsupport.a
 do
  install -m755 ${B}/lib/${aLIB} ${D}${base_libdir}/
 done
 ln -s libcom_err.so.2.1 ${D}${base_libdir}/libcom_err.so
 ln -s libcom_err.so.2.1 ${D}${base_libdir}/libcom_err.so.2
 ln -s libe2p.so.2.3 ${D}${base_libdir}/libe2p.so
 ln -s libe2p.so.2.3 ${D}${base_libdir}/libe2p.so.2
 ln -s libext2fs.so.2.4 ${D}${base_libdir}/libext2fs.so
 ln -s libext2fs.so.2.4 ${D}${base_libdir}/libext2fs.so.2
 ln -s libss.so.2.0 ${D}${base_libdir}/libss.so
 ln -s libss.so.2.0 ${D}${base_libdir}/libss.so.2
 install -m755 ${B}/misc/e2initrd_helper ${D}${libdir}/
 install -m644 ${B}/lib/ext2fs/ext2fs.pc ${D}${libdir}/pkgconfig/
 install -m644 ${B}/lib/e2p/e2p.pc ${D}${libdir}/pkgconfig/
 install -m644 ${B}/lib/ss/ss.pc ${D}${libdir}/pkgconfig/
 install -m644 ${B}/lib/et/com_err.pc ${D}${libdir}/pkgconfig/
 #headers...
 install -m644 ${S}/lib/e2p/e2p.h ${D}${includedir}/e2p/
 install -m644 ${S}/lib/et/com_err.h ${D}${includedir}/et/
 install -m644 ${S}/lib/et/com_err.h ${D}${includedir}/
 for aH in bitops.h ext2_ext_attr.h ext2_fs.h ext2fs.h ext2_io.h ext3_extents.h hashmap.h qcow2.h tdb.h
 do
  install -m644 ${S}/lib/ext2fs/${aH} ${D}${includedir}/ext2fs/
 done
 install -m644 ${B}/lib/ext2fs/ext2_types.h ${D}${includedir}/ext2fs/
 install -m644 ${B}/lib/ext2fs/ext2_err.h ${D}${includedir}/ext2fs/
 install -m644 ${S}/lib/ss/ss.h ${D}${includedir}/ss/
}

e2fsprogs_conf_fixup () {
	#for i in mke2fs mkfs.ext2 mkfs.ext3 mkfs.ext4; do
	#	create_wrapper ${D}${base_sbindir}/$i MKE2FS_CONFIG=${sysconfdir}/mke2fs.conf
	#done
	true
}

do_install_append_class-target() {
	#mv ${D}${base_sbindir}/mke2fs ${D}${base_sbindir}/mke2fs.e2fsprogs
	#mv ${D}${base_sbindir}/mkfs.ext2 ${D}${base_sbindir}/mkfs.ext2.e2fsprogs
	#mv ${D}${base_sbindir}/tune2fs ${D}${base_sbindir}/tune2fs.e2fsprogs
	true
}

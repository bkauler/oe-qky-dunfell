# Recipe created by recipetool
# recipetool create -o pup-tools_20220827.bb https://distro.ibiblio.org/easyos/source/alphabetical/p/pup-tools-20220827.tar.gz
# 20220824 bacon-native rolled back to version 3.9.3
# 20220827 new debdb2pupdb.nim

HOMEPAGE = "https://bkhome.org/news"
SUMMARY = "Core utilities used in Puppy and derivatives"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/p/pup-tools-${PV}.tar.gz \
  file://hug.bacV0.109"

SRC_URI[md5sum] = "e641995f11a633be65a99a7c99b3444f"
SRC_URI[sha256sum] = "02dbefd7f6300d9745f890fb36f41ebc018dab3d8792f87f34f28890d820bd75"

DEPENDS = "gtk+ m4-native bacon-native nim-native"

inherit gettext pkgconfig

do_configure() {
 cp -f ${WORKDIR}/hug.bacV0.109 bacon/hug.bac
}

do_compile () {
 
 #new nim app...
 #bad hack...
 cp -a -f ${WORKDIR}/recipe-sysroot-native/usr/lib/nim/* ${WORKDIR}/recipe-sysroot-native/usr/lib/
 case "${TARGET_ARCH}" in
  x86_64) xTARGET_ARCH=amd64 ;;
  aarch64) xTARGET_ARCH=arm64 ;;
  i686) xTARGET_ARCH=i386 ;;
  *) xTARGET_ARCH="${TARGET_ARCH}" ;;
 esac
 cd nim
  #compile debdb2pupdb...
  echo "${xTARGET_ARCH}.linux.gcc.exe = \"${CC/ */}\"" > debdb2pupdb.nim.cfg
  echo 'path="$lib/deprecated/core"
path="$lib/deprecated/pure"
path="$lib/pure/collections"
path="$lib/pure/concurrency"
path="$lib/impure"
path="$lib/wrappers"
path="$lib/wrappers/linenoise"
path="$lib/windows"
path="$lib/posix"
path="$lib/js"
path="$lib/pure/unidecode"
path="$lib/arch"
path="$lib/core"
path="$lib/pure"' >> debdb2pupdb.nim.cfg
  nim c --cpu:${xTARGET_ARCH} --os:linux -d:release --cc:gcc --skipCfg --skipUserCfg --skipParentCfg --compileOnly --genScript --nimcache:nimcache debdb2pupdb
  cd nimcache
  sed -i 's/gcc/$CC/' compile_debdb2pupdb.sh
  sed -i 's/$/ $CFLAGS/' compile_debdb2pupdb.sh
  # need "-ldl" appended to last line, clever sed...
  sed -i '$s/CFLAGS$/LDFLAGS -ldl/' compile_debdb2pupdb.sh
  chmod +x ./compile_debdb2pupdb.sh
  ./compile_debdb2pupdb.sh
  cd ..
 
 cd ..
 
 cd bacon
  mkdir -p build
  
  #welcome1stboot: change inbuilt hug.bac instead of hug_imports.bac & hug.so...
  sed -i -e 's%^REM INCLUDE "/usr/share/BaCon/hug.bac"%INCLUDE "/usr/share/BaCon/hug.bac"%' welcome1stboot.bac
  sed -i -e 's%^INCLUDE "/usr/share/BaCon/hug_imports.bac"%REM INCLUDE "/usr/share/BaCon/hug_imports.bac"%' welcome1stboot.bac
  sed -i -e 's%/usr/share/BaCon%.%' welcome1stboot.bac #hug.bac is local.
  
  #pup_event_ipc: fix
  sed -i -e "s%'CONST IN_MODIFY=2%CONST IN_MODIFY=2%" pup_event_ipc.bac #170608
  
  #popup: fix
  sed -i -e 's%/usr/share/BaCon%.%' popup.bac #hug.bac is local.
  
  # reqd for bacon 3.x: -a rebuild libbacon.a
  for aF in welcome1stboot debdb2pupdb find_cat popup pup_event_ipc
  do
   bacon -a -d build -c "${CC}" -o "${CFLAGS}" -l "${LDFLAGS}" ${aF}.bac
   if [ $? -eq 0 ];then
    cp -a -f build/${aF} ./
   fi
   rm -f build/*
  done
 cd ..

 #20190405 now have pup_event_frontend_d.c ...
 #20200212 now have bitflip.c ...
 #20211013 now have getpixelcolor.c ...
 #20220807 now have debdb2pupdb.c, find_cat.c ...
 #20220816 staying with bacon debdb2pupdb.bac and find_cat.bac
 cd gcc
 for aFILE in bitflip printcols truncate vercmp getlocalip pup_event_frontend_d
 do
  ${CC} -o ${aFILE} ${aFILE}.c ${CFLAGS} ${LDFLAGS}
 done
 ${CC} -lX11 getcurpos.c -o getcurpos ${CFLAGS} ${LDFLAGS}
 ${CC} -lX11 getpixelcolor.c -o getpixelcolor ${CFLAGS} ${LDFLAGS}
 cd ..
}

do_install () {
 install -d ${D}/bin
 install -d ${D}/usr/bin
 install -d ${D}/usr/sbin
 install -d ${D}/usr/local/petget
 install -d ${D}/usr/local/pup_event

 install -m755 gcc/vercmp ${D}/bin
 install -m755 gcc/truncate ${D}/usr/bin
 install -m755 gcc/bitflip ${D}/usr/sbin
 install -m755 gcc/getcurpos ${D}/usr/sbin
 install -m755 gcc/getpixelcolor ${D}/usr/sbin
 install -m755 gcc/printcols ${D}/usr/sbin
 install -m755 gcc/getlocalip ${D}/usr/sbin
 #install -m755 gcc/debdb2pupdb ${D}/usr/local/petget
 #install -m755 gcc/find_cat ${D}/usr/local/petget
 install -m755 gcc/pup_event_frontend_d ${D}/usr/local/pup_event

 install -m755 bacon/popup ${D}/usr/sbin
 install -m755 bacon/welcome1stboot ${D}/usr/sbin
 install -m755 bacon/debdb2pupdb ${D}/usr/local/petget
 install -m755 bacon/find_cat ${D}/usr/local/petget
 install -m755 bacon/pup_event_ipc ${D}/usr/local/pup_event
}

FILES_${PN} += "/usr/local/petget /usr/local/pup_event"

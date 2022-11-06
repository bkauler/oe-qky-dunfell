# Recipe created by recipetool
# recipetool create -o nim_1.6.6.bb https://nim-lang.org/download/nim-1.6.6.tar.xz
# ref: https://github.com/aguspiza/meta-nim
# ref: https://bkhome.org/news/202208/considering-adopting-nim-language.html

# 20220823 PR_NUM is defined in local.conf, currently r9
PR = "r${@int(PR_NUM) + 1}"

HOMEPAGE = "https://nim-lang.org/"
DESCRIPTION = "nim compiler"
SECTION = "languages"
SUMMARY = "nim language compiler"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://copying.txt;md5=e202ebcd59041b078a1f8cf66709081d"

DEPENDS = "nim-native flex-native m4-native"

SRC_URI = "https://nim-lang.org/download/nim-${PV}.tar.xz"

SRC_URI[md5sum] = "e316eb7f961071c6aaa7e5376c576f90"
SRC_URI[sha256sum] = "67b111ce6f3861503b9fcc1cae59fc34d0122566d3ecfef3a064a2174121a452"

# ERROR: nim-native-1.6.6-r9 do_populate_sysroot: The recipe nim-native is trying to install files into a shared area when those files already exist. Those files and their manifest location are:
#  /mnt/build/oe-builds/oe-quirky/build-amd64/tmp/sysroots-components/x86_64/nim-native/usr/bin/nim
# It could be the overlapping files detected are harmless in which case adding them to SSTATE_DUPWHITELIST may be the correct solution.
SSTATE_DUPWHITELIST = "/"

do_configure () {
 true
}

do_compile () {
 #compile nim...
 #COMP_FLAGS="${CFLAGS}" LINK_FLAGS="${LDFLAGS}" ./build.sh --os linux --cpu ${TARGET_ARCH}
 ./build.sh --os linux --cpu ${TARGET_ARCH}
 
 #bad hack...
 cp -a -f ${WORKDIR}/recipe-sysroot-native/usr/lib/nim/* ${WORKDIR}/recipe-sysroot-native/usr/lib/
 
 case "${TARGET_ARCH}" in
  x86_64) xTARGET_ARCH=amd64 ;;
  aarch64) xTARGET_ARCH=arm64 ;;
  i686) xTARGET_ARCH=i386 ;;
  *) xTARGET_ARCH="${TARGET_ARCH}" ;;
 esac
  
 #compile koch...
 echo "${xTARGET_ARCH}.linux.gcc.exe = \"${CC/ */}\"" > koch.nim.cfg
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
path="$lib/pure"' >> koch.nim.cfg
 nim c --cpu:${xTARGET_ARCH} --os:linux -d:release --cc:gcc --skipCfg --skipUserCfg --skipParentCfg --compileOnly --genScript --nimcache:nimcache koch
 cd nimcache
 sed -i 's/gcc/$CC/' compile_koch.sh
 chmod +x ./compile_koch.sh
 ./compile_koch.sh
 cd ..
  
 #koch tools -d:release
 # ...does not work, do it this way, compile nimble...
 cat koch.nim.cfg >> dist/nimble/src/nimble.nim.cfg
 nim c --noNimblePath -p:compiler --cpu:${xTARGET_ARCH} --os:linux -d:release --cc:gcc --skipCfg --skipUserCfg --skipParentCfg --compileOnly --genScript --nimcache:nimcache2  dist/nimble/src/nimble.nim
 cd nimcache2
 sed -i 's/gcc/$CC/' compile_nimble.sh
 # need "-ldl" appended to last line, clever sed...
 sed -i '$s/$/ -ldl/' compile_nimble.sh
 chmod +x ./compile_nimble.sh
 ./compile_nimble.sh
 cd ..
 
 #compile nimgrep...
 cat koch.nim.cfg >> tools/nimgrep.nim.cfg
 nim c --noNimblePath -p:compiler --cpu:${xTARGET_ARCH} --os:linux -d:release --cc:gcc --skipCfg --skipUserCfg --skipParentCfg --compileOnly --genScript --nimcache:nimcache3 tools/nimgrep.nim
 cd nimcache3
 sed -i 's/gcc/$CC/' compile_nimgrep.sh
 # need "-ldl" appended to last line, clever sed...
 sed -i '$s/$/ -ldl/' compile_nimgrep.sh
 chmod +x ./compile_nimgrep.sh
 ./compile_nimgrep.sh
 cd ..
 
 #compile nimsuggest...
 cat koch.nim.cfg >> nimsuggest/nimsuggest.nim.cfg
 # nimsuggest.nim.cfg imports compiler/options etc, but "-p:compiler" doesn't work, reports
 # "Error: cannot open file: compiler/options" hmmm, "-p:." seems to have fixed it.
 nim c --noNimblePath -p:compiler -p:. --cpu:${xTARGET_ARCH} --os:linux -d:release --cc:gcc --skipCfg --skipUserCfg --skipParentCfg --compileOnly --genScript --nimcache:nimcache4 nimsuggest/nimsuggest.nim
 # now get error "i686-poky-linux-gcc: error: linenoise.c: No such file or directory". try hack...
 cp -a lib/wrappers/linenoise/linenoise.* nimcache4/
 cd nimcache4
 sed -i 's/gcc/$CC/' compile_nimsuggest.sh
 # need "-ldl" appended to last line, clever sed...
 sed -i '$s/$/ -ldl/' compile_nimsuggest.sh
 chmod +x ./compile_nimsuggest.sh
 ./compile_nimsuggest.sh
 cd ..
}

do_install () {
 ./install.sh pkg
 install -d ${D}${bindir}
 install -d ${D}${includedir}
 install -d ${D}${libdir}/nim
 install -m 755 pkg/nim/bin/nim ${D}${bindir}/
 install -m 644 pkg/nim/lib/cycle.h ${D}${includedir}/
 install -m 644 pkg/nim/lib/nimbase.h ${D}${includedir}/
 install -m 755 nimcache2/nimble ${D}${bindir}/
 install -m 755 nimcache3/nimgrep ${D}${bindir}/
 install -m 755 nimcache4/nimsuggest ${D}${bindir}/
 install -m 755 nimcache/koch ${D}${bindir}/
 (
  cd pkg/nim/lib
  for dir in $(find . -mindepth 1 -type d);do
   install -d ${D}${libdir}/nim/${dir}
  done
  for file in $(find . -mindepth 1 -type f);do
   install -m 755 "${file}" ${D}${libdir}/nim/${file}
  done
 )
 install -d ${D}/etc
 install -d ${D}/etc/nim
 install -m 644 pkg/nim/config/nim.cfg ${D}/etc/nim/nim.cfg.new
 install -m 644 pkg/nim/config/nimdoc.cfg ${D}/etc/nim/nimdoc.cfg.new
 install -m 644 pkg/nim/config/nimdoc.tex.cfg ${D}/etc/nim/nimdoc.tex.cfg.new
 install -m 644 pkg/nim/config/rename.rules.cfg ${D}/etc/nim/rename.rules.cfg.new
}


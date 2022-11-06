# Recipe created by recipetool
# recipetool create -o nim_1.6.6.bb https://nim-lang.org/download/nim-1.6.6.tar.xz

HOMEPAGE = "https://nim-lang.org/"
DESCRIPTION = "nim compiler"
SECTION = "languages"
SUMMARY = "nim language compiler"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://copying.txt;md5=e202ebcd59041b078a1f8cf66709081d"

inherit native

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
  COMP_FLAGS="${CFLAGS}" LINK_FLAGS="${LDFLAGS}" ./build.sh --os linux --cpu ${BUILD_ARCH}
  bin/nim c -d:release koch
}

do_install () {
 ./install.sh pkg
 install -d ${D}${bindir}
 install -d ${D}${includedir}
 install -d ${D}${libdir}/nim
 install -m 755 pkg/nim/bin/nim ${D}${bindir}/
 install -m 644 pkg/nim/lib/cycle.h ${D}${includedir}/
 install -m 644 pkg/nim/lib/nimbase.h ${D}${includedir}/
 install -m 755 koch ${D}${bindir}/
 (
  cd pkg/nim/lib
  for dir in $(find . -mindepth 1 -type d);do
   install -d ${D}${libdir}/nim/${dir}
  done
  for file in $(find . -mindepth 1 -type f);do
   install -m 755 "${file}" ${D}${libdir}/nim/${file}
  done
 )
 
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
path="$lib/pure"' > nim.cfg
 install -d ${D}${sysconfdir}/nim
 install -m644 nim.cfg ${D}${sysconfdir}/nim/
}


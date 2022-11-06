# Recipe created by recipetool
# recipetool create -o limine_3.25.bb https://github.com/limine-bootloader/limine/releases/download/v3.25/limine-3.25.tar.gz

SUMMARY = "x86/x86_64 BIOS/UEFI bootloader"
HOMEPAGE = "https://limine-bootloader.org/"

LICENSE = "UCB & GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=566da3e4be60cd868e42cb88c9c0ddd3 \
                    file://stivale/LICENSE.md;md5=21e2e2557201f26b907e5694c9e179b9 \
                    file://libgcc-binaries/COPYING.RUNTIME;md5=fe60d87048567d4fe8c8a0ed2448bcc8 \
                    file://libgcc-binaries/COPYING;md5=64305ff51f34d0aca60d187e21a09d59 \
                    file://libgcc-binaries/COPYING3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://freestanding-headers/LICENSE.md;md5=9621911223dde43748ad798e3804a3ed \
                    file://limine-efi/COPYING;md5=c27a4b4a954b36c8afddf7587fd749be \
                    file://cross-detect/LICENSE.md;md5=9621911223dde43748ad798e3804a3ed"

SRC_URI = "https://github.com/limine-bootloader/limine/releases/download/v${PV}/limine-${PV}.tar.gz \
           file://remove-ext4-encrypt-warning.patch"

SRC_URI[md5sum] = "8cc338ef4d94727bd7de0989a6f10401"
SRC_URI[sha256sum] = "6945ee57746128ed96c0c1e8f60cc733b71f1a2debeff000578b90a3ce9c2c0a"

inherit autotools

DEPENDS = "mtools-native nasm-native coreutils-native"

# ref: https://github.com/limine-bootloader/limine/issues/193
export CROSS_TOOLCHAIN="${TARGET_SYS}"

#20220819 make this work for i686 host...
#EXTRA_OECONF = "--enable-all"
EXTRA_OECONF = "--enable-bios --enable-limine-deploy --enable-bios-cd --enable-bios-pxe"
EXTRA_OECONF_append_x86-64 = " --enable-uefi-ia32 --enable-uefi-x86_64 --enable-cd-efi"


# Recipe created by recipetool
# recipetool create -o refind_0.12.0.bb https://downloads.sourceforge.net/project/refind/0.12.0/refind-src-0.12.0.tar.gz

SUMMARY = "EFI boot manager software"
LICENSE = "GPLv3 & GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LICENSE.txt;md5=9396e07c1572dd19fb709d14874e00ec"

SRC_URI = "https://downloads.sourceforge.net/project/refind/${PV}/refind-src-${PV}.tar.gz \
  file://refind-ext4-fscrypt.patch"

SRC_URI[md5sum] = "673088f61ccd60535a3b2c0d607e4a7e"
SRC_URI[sha1sum] = "a6b63bbaf9c09e63c82cbd54fb5a776e0260b3f9"
SRC_URI[sha256sum] = "7bb8505bc9ff87a7b6de38bf9a91d46f4d613b30031d5eb088a4d791a31eb7c4"
SRC_URI[sha384sum] = "2961d249b4b04d746b63af8837a282e56b90ccae9393d50a5ad57437967a5b6719632dd4308641b7ab6f12ceafdfd891"
SRC_URI[sha512sum] = "3ade9642d7acf31097daa5308acb96a9fa3371314073b5a09601a886fc30444752efa08ed9fb35e421efb0b4eb9efbccd7b5c9bd6b3b9ed4af74c58f22a74729"

DEPENDS = "gnu-efi"


do_configure () {
 true
}

do_compile () {
 #oe_runmake all_gnuefi
 oe_runmake gnuefi
 #ntfs driver is not compiled automatically...
 #reiserfs sompile fail, leave it out...
 cd filesystems
 oe_runmake ext2_gnuefi
 oe_runmake ext4_gnuefi
 oe_runmake iso9660_gnuefi
 oe_runmake btrfs_gnuefi
 oe_runmake ntfs_gnuefi
 cd ..
}

do_install () {
 install -d ${D}/usr/share/refind/drivers_x64
 install -m644 filesystems/ext2_x64.efi ${D}/usr/share/refind/drivers_x64
 install -m644 filesystems/ext4_x64.efi ${D}/usr/share/refind/drivers_x64
 install -m644 filesystems/iso9660_x64.efi ${D}/usr/share/refind/drivers_x64
 install -m644 filesystems/btrfs_x64.efi ${D}/usr/share/refind/drivers_x64
 install -m644 filesystems/ntfs_x64.efi ${D}/usr/share/refind/drivers_x64
 install -m644 refind/refind_x64.efi ${D}/usr/share/refind
 install -m644 gptsync/gptsync_x64.efi ${D}/usr/share/refind
}


# Recipe created by recipetool
# recipetool create -o iotop-py_20220307.bb https://repo.or.cz/iotop.git/snapshot/a14256a3ff74eeee59493ac088561f1bafab85a7.tar.gz

SUMMARY = "Per process I/O bandwidth monitor"
HOMEPAGE = "http://guichaz.free.fr/iotop/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4325afd396febcb659c36b49533135d4"

SRC_URI = "https://repo.or.cz/iotop.git/snapshot/a14256a3ff74eeee59493ac088561f1bafab85a7.tar.gz"
SRC_URI[md5sum] = "ae3be5af07f04ae8850997478095426e"
SRC_URI[sha256sum] = "e2a9740e61405547dd7479fd59a5cd7dcca25ffec0bb25f6d369e24a23f57f68"

S = "${WORKDIR}/iotop-a14256a"

inherit distutils3

do_install_append() {
 mv -f ${D}/usr/sbin/iotop ${D}/usr/sbin/iotop-py
}

RDEPENDS_${PN} += "python3-core python3-ctypes python3-curses python3-io python3-pprint python3-profile"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    _netlink
#    hotshot
#    hotshot.stats
#    monotonic

# BK 20210122  
# BK 20210324 recompiling coz left out cups dep.

# 202101023 recompiling, bump revision...
# 20210121 PR_NUM is defined in local.conf...
#PR = "r${@int(PR_NUM) + 1}"
# 20210324 bump...  20220131 removed...
#PR = "r${@int(PR_NUM) + 2}"

#heimdahl compiles these and want to run them during compile... do not use $HOST_ARCH...
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append = "\
  file://${BUILD_ARCH}/asn1_compile \
  file://${BUILD_ARCH}/compile_et"

# 20210123 remove:  libldb --install conflicts with samba install.
# 20210324 add: cups ghostscript ijs cups-filters
DEPENDS = "readline virtual/libiconv zlib popt libtalloc libtdb libtevent libaio libtasn1 jansson libbsd gpgme acl gnutls libarchive openldap cups ghostscript ijs cups-filters"

REQUIRED_DISTRO_FEATURES = ""

SYSTEMD_PACKAGES = ""
SYSTEMD_SERVICE_${PN}-base = ""
SYSTEMD_SERVICE_${PN}-ad-dc = ""
SYSTEMD_SERVICE_winbind = ""
SYSTEMD_AUTO_ENABLE_${PN}-ad-dc = ""

PACKAGECONFIG = ""

EXTRA_OECONF = "--enable-fhs \
                 --with-piddir=/run \
                 --with-sockets-dir=/run/samba \
                 --with-modulesdir=${libdir}/samba \
                 --with-lockdir=${localstatedir}/lib/samba \
                 --with-cachedir=${localstatedir}/lib/samba \
                 --disable-rpath-install \
                 ${@oe.utils.conditional('TARGET_ARCH', 'x86_64', '', '--disable-glusterfs', d)} \
                 --with-cluster-support \
                 --with-profiling-data \
                 --with-libiconv=${STAGING_DIR_HOST}${prefix} \
                 --without-pam --without-sendfile-support \
                 --without-ad-dc  --without-ntvfs-fileserver  --nopyc --nopyo \
                 --without-fam --without-lttng --without-systemd --disable-avahi \
                 --enable-cups --with-acl-support --with-automount --with-quotas \
                 --with-syslog --with-ldap --enable-gnutls --with-gpgme --with-libbsd \
                 --with-libarchive --without-valgrind \
                 --bundled-libraries=!asn1_compile,!compile_et"

RDEPENDS_${PN}-ad-dc = ""

# ref: http://samba.2283325.n4.nabble.com/Samba4-4-5-cross-compilation-for-PowerPC-td4710972.html
export USING_SYSTEM_ASN1_COMPILE = "1"
export ASN1_COMPILE = "${WORKDIR}/${BUILD_ARCH}/asn1_compile"
export USING_SYSTEM_COMPILE_ET = "1"
export COMPILE_ET = "${WORKDIR}/${BUILD_ARCH}/compile_et"

ERROR_QA_remove = "file-rdeps"
WARN_QA_remove = "file-rdeps"

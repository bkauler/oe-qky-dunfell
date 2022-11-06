

# BK add graphite2 
DEPENDS = "glib-2.0 cairo fontconfig freetype graphite2"

# BK remove --without-graphite2
EXTRA_OECONF = " \
    --with-cairo \
    --with-fontconfig \
    --with-freetype \
    --with-glib \
    --with-graphite2 \
"

# BK 20210121 ha ha, config.log has this:
# $ ../harfbuzz-2.6.4/configure --build=x86_64-linux --host=aarch64-poky-linux --target=aarch64-poky-linux --prefix=/usr --exec_prefix=/usr --bindir=/usr/bin --sbindir=/usr/sbin --libexecdir=/usr/libexec --datadir=/usr/share --sysconfdir=/etc --sharedstatedir=/com --localstatedir=/var --libdir=/usr/lib --includedir=/usr/include --oldincludedir=/usr/include --infodir=/usr/share/info --mandir=/usr/share/man --disable-silent-rules --disable-dependency-tracking --with-libtool-sysroot=/mnt/sda1/nvme/oe-builds/oe-quirky/build-aarch64/tmp/work/aarch64-poky-linux/harfbuzz/2.6.4-r0/recipe-sysroot --disable-gtk-doc --with-cairo --with-fontconfig --with-freetype --with-glib --with-graphite2 --disable-static --with-cairo --with-fontconfig --with-freetype --with-glib --without-graphite2 --with-icu
# --with-graphite2, followed by --without-graphite2
# fix:

PACKAGECONFIG_append = " graphite"

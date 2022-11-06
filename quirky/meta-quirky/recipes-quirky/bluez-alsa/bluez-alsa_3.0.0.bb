# Recipe created by recipetool
# recipetool create -o bluez-alsa_3.0.0.bb https://github.com/Arkq/bluez-alsa/archive/v3.0.0.tar.gz

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=72d868d66bdd5bf51fe67734431de057"

SRC_URI = "https://github.com/Arkq/bluez-alsa/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "2e0a01c83dc07fc58c7dc59a29ed570b"
SRC_URI[sha1sum] = "5cf370caf966ac37dbe183201538c8bd461b0ef7"
SRC_URI[sha256sum] = "8b9bc36be922c10c6628ddf84b13dfadfeb3ab0bcf72bad842c66f3120abc6b2"
SRC_URI[sha384sum] = "b2db0a46eda91b7269c60aff7d1a176a51795f585bf0a1a82b651e8dbf5c1f8f2dd3f41f5d119c01ee780076e494b5e0"
SRC_URI[sha512sum] = "094c91636c3f1803b3eb88e295683acc604be9f86d00022c196784b4ec88aa4a6cc20bdff0fdab2a7e5af3efe95ed8bd8c5486a8cd7896ec572bfa9e8e0f13d0"

DEPENDS = "glib-2.0 libbsd bluez5 dbus ncurses lame sbc alsa-lib mpg123 readline"

inherit pkgconfig autotools gettext

EXTRA_OECONF = "--disable-manpages --enable-hcitop --disable-ldac --disable-aptx --disable-aac --enable-rfcomm --enable-mpg123 --enable-mp3lame"

HOMEPAGE = "https://github.com/Arkq/bluez-alsa"
SUMMARY = "Support bluetooth audio with alsa"

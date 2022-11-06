
# 180401 ditto, probalems as per gtk2...

# added last line... 2020-09-16 added another line...
DEPENDS = "glib-2.0 cairo pango atk jpeg libpng gdk-pixbuf \
           gdk-pixbuf-native \
           cups libxinerama xinput pixman freetype fontconfig \
           cups-native xinput-native"

# original...
#EXTRA_OECONF = " --enable-introspection --disable-gtk-doc --disable-glibtest \
#                 --disable-xinerama --enable-modules --disable-cups \
#                 --disable-colord \
#                 --enable-glx --enable-opengl --disable-wayland-backend \
#                 --enable-x11-backend --enable-nls"

EXTRA_OECONF = " --enable-introspection --disable-gtk-doc --disable-glibtest \
                 --enable-xinerama --enable-modules --enable-cups \
                 --disable-colord \
                 --enable-glx --enable-opengl --disable-wayland-backend \
                 --enable-x11-backend --enable-nls"

# 2020-09-16 problem with cups_%.bbappend, missing 'cups-native' 'xinput-native'
# this is in gtk+3.inc, empty it...
#BBCLASSEXTEND = ""
# or, modify cups_%.bbappend


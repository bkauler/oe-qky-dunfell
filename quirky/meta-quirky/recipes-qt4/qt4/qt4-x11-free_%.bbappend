
#20211217 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

#no, have patched qt4-x11-free.inc, -gtkstyle, see create-oe-quirky
##need this for qt theme to mimic current gtk+ theme...
#QT_CONFIG_FLAGS += " -gtkstyle"

DEPENDS += " openssl gtk+ jpeg libmng libpng tiff sqlite3 fontconfig zlib \
           librsvg cups cups-filters ghostscript"

#might as well throw in -gtkstyle, to be sure...
#error, remove: -openssl-linked 
QT_CONFIG_FLAGS += " -system-libjpeg -system-libmng \
                   -system-libpng -system-libtiff -system-zlib -gtkstyle \
                   -cups -no-qt3support -system-sqlite"

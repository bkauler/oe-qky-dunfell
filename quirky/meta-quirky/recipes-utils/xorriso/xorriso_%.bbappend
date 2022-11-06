
# 170506 currently do not want 'tk'...
RDEPENDS_${PN} = ""

do_install_append() {
 rm -f ${D}/usr/bin/xorriso-tcltk
}

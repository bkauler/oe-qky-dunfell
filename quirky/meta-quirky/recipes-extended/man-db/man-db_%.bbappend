
#tries to run "chown man:man" which fails the do_install...

do_install_prepend () {
 sed -i -e 's%^man_owner =.*%man_owner = %' ${B}/src/Makefile
}

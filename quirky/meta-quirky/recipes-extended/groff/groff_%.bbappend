
# 20210528 glibc jumped from 2.31 to 2.33 no longer support sys_siglist

do_compile_prepend() {
 sed -i -e 's%sys_siglist\[n\]%strsignal(n)%' ${S}/src/roff/groff/pipeline.c
}

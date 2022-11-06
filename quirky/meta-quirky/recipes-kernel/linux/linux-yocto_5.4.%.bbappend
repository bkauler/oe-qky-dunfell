#created 2020-09-17
#ref: https://www.yoctoproject.org/docs/3.1/kernel-dev/kernel-dev.html
#note: ${PN} is "linux-yocto", need folder of that name, for patches etc.

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

#complete config file. note, this is in a sub-folder 'genericx86-64'...
SRC_URI += "file://defconfig"

#or, can specify a config fragment(s):
#SRC_URI += "file://8250.cfg"







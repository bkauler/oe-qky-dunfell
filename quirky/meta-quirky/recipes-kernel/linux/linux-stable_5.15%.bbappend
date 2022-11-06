#created 2020-12-19
#have meta-kernel layer.
#ref: https://www.yoctoproject.org/docs/3.1/kernel-dev/kernel-dev.html
#note: ${PN} is "linux-stable", need folder of that name, for patches etc.

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

#complete config file. note, this is in a sub-folder 'genericx86-64'...
SRC_URI += "file://defconfig \
            file://cap_sys_mount-1.patch \
            file://cap_sys_mount-2.patch"

#or, can specify a config fragment(s):
#SRC_URI += "file://8250.cfg"







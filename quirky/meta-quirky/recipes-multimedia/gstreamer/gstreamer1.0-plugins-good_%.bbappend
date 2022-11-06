
#20220128 pitivi video editor won't run, gst gtk plgin missing...

#bump to r7
#PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

PACKAGECONFIG_append = " gtk gudev libv4l2 wavpack"


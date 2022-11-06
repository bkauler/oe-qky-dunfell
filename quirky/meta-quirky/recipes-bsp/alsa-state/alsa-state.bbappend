
#20220129 don't actually use this, it is a dep of something.

#idiot who created the recipe, hardcoded PR = "r5"
# set it to what it should be (currently r7)...
PR = "r${@int(PR_NUM)}"

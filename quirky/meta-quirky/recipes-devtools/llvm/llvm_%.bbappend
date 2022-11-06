
#the recipe doen't build 'clang' binary.
# ref: https://clang.llvm.org/get_started.html
EXTRA_OECMAKE_append_class-target = " -DLLVM_ENABLE_PROJECTS=clang"

# ERROR: llvm-9.0.1-r4 do_package_qa: QA Issue: /usr/bin/llvm9.0.1/c-index-test contained in package llvm requires libclang.so.9(LLVM_9)(64bit), but no providers found in RDEPENDS_llvm? [file-rdeps]
# ERROR: llvm-9.0.1-r4 do_package_qa: QA Issue: /usr/bin/llvm9.0.1/c-index-test contained in package llvm requires libclang.so.9()(64bit), but no providers found in RDEPENDS_llvm? [file-rdeps]
# ERROR: llvm-9.0.1-r4 do_package_qa: QA Issue: /usr/bin/llvm9.0.1/diagtool contained in package llvm requires libclang-cpp.so.9()(64bit), but no providers found in RDEPENDS_llvm? [file-rdeps]
ERROR_QA_remove = "file-rdeps"

#the final binary pkg, xz compressed, is 2GB. make it smaller:
do_install_append_class-target() {
 rm -f ${D}${libdir}/${LLVM_DIR}/*.a
}

# override .bb file...
FILES_${PN}-staticdev = ""

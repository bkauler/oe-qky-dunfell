#20211018
#before having this .bbappend, do_configure had this:
# NOTE: Executing meson -Dshared-glapi=true -Dgallium-opencl=disabled -Dglx-read-only-text=true -Dplatforms=x11,drm,surfaceless -Ddri=true -Ddri-drivers=,r100,r200,nouveau,i965,i915 -Ddri3=true -Degl=true -Delf-tls=true -Dgallium-drivers=swrast,virgl -Dllvm=false -Dgbm=true -Dgles1=true -Dgles2=true -Dopengl=true -Dosmesa=none -Dlibunwind=false -Dvulkan-drivers= -Dgallium-xa=false -Dgallium-xvmc=false -Dasm=false...
#after having this .bbappend, with "r600", do_configure has this:
# NOTE: Executing meson -Dshared-glapi=true -Dgallium-opencl=disabled -Dglx-read-only-text=true -Dplatforms=x11,drm,surfaceless -Ddri=true -Ddri-drivers=,r100,r200,nouveau,i965,i915 -Ddri3=true -Degl=true -Delf-tls=true -Dgallium-drivers=swrast,r300,svga,nouveau,,radeonsi,r600,virgl -Dllvm=true -Dshared-llvm=true -Dgbm=true -Dgles1=true -Dgles2=true -Dopengl=true -Dosmesa=none -Dlibunwind=false -Dvulkan-drivers= -Dgallium-xa=false -Dgallium-xvmc=false -Dasm=false...
#note, if leave out the "r600", do_configure has this:
# NOTE: Executing meson -Dshared-glapi=true -Dgallium-opencl=disabled -Dglx-read-only-text=true -Dplatforms=x11,drm,surfaceless -Ddri=true -Ddri-drivers=,r100,r200,nouveau,i965,i915 -Ddri3=true -Degl=true -Delf-tls=true -Dgallium-drivers=swrast,r300,svga,nouveau,virgl -Dllvm=true -Dshared-llvm=true -Dgbm=true -Dgles1=true -Dgles2=true -Dopengl=true -Dosmesa=none -Dlibunwind=false -Dvulkan-drivers= -Dgallium-xa=false -Dgallium-xvmc=false -Dasm=false...

#20211119 was "+ 1" which was "r6". bump to "r7"... 20220131 removed...
#PR = "r${@int(PR_NUM) + 2}"

#20211119 want r600...

#want these extra drivers nouveau_dri.so, r300, etc...
#PACKAGECONFIG_append_x86-64_class-target = "gallium gallium-llvm"

#this adds r600 and radeonsi...
PACKAGECONFIG_append_x86-64_class-target = "gallium gallium-llvm r600"

#20220609 compiling for rpi
PACKAGECONFIG_append_aarch64_class-target = " v3d vc4"

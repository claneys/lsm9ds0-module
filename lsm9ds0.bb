require ../../include/rcar-gen2-modules-common.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM="file://GPLv2-COPYING;md5=75859989545e37968a99b631ef42722e"


DEPENDS = "linux-renesas"
PN = "lsm9ds0"
PV = "1.0.2"
PR = "r0"
SRC_URI = "file://lsm9ds0-1.0.2.tar.bz2"
S = "${WORKDIR}/${PN}"

KERNEL_HEADER_PATH = "${KERNELSRC}/include/linux/input"

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
"

do_compile() {
    # Build kernel module
    # export LSM9DS0_CONFIG=${LSM9DS0_CFG}
    cd ${S}
    make all ARCH=arm
}

do_install() {
    # Create destination folder
    mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/drivers/input/misc ${D}/usr/src/kernel/include/linux/input

    # Copy driver and header files
    cp -f ${S}/lsm9ds0_acc_mag.ko ${S}/lsm9ds0_gyr.ko ${D}/lib/modules/${KERNEL_VERSION}/drivers/input/misc
    cp ${S}/Module.symvers ${KERNELSRC}/include/linux/input/lsm9ds0.symvers

    # Copy header files to destination
    cp -f ${KERNEL_HEADER_PATH}/lsm9ds0.h ${D}/usr/src/kernel/include/linux/input
    cp -f ${S}/Module.symvers ${D}/usr/src/kernel/include/linux/input/lsm9ds0.symvers
}


# TODO : make driver installed in those dir. For now, using easier and default dir
#FILES_${PN} = " \
#/lib/modules/${KERNEL_VERSION}/drivers/input/misc/lsm9ds0_gyr.ko \
#/lib/modules/${KERNEL_VERSION}/drivers/input/misc/lsm9ds0_acc_mag.ko \
#"

FILES_${PN} = " \
/lib/modules/${KERNEL_VERSION}/extra/lsm9ds0_gyr.ko \
/lib/modules/${KERNEL_VERSION}/extra/lsm9ds0_acc_mag.ko \
"

FILES_${PN} = " \
/usr/src/kernel/include/linux/input/lsm9ds0.h \
/usr/src/kernel/include/linux/input/lsm9ds0.symvers \
"

RPROVIDES_${PN} += "lsm9ds0-kernel-module kernel-module-lsm9ds0"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

ALLOW_EMPTY_kernel-module-lsm9ds0 = "1"

do_configure[noexec] = "1"

python do_package_ipk_prepend () {
    d.setVar('ALLOW_EMPTY', '1')
}

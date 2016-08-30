require ../../include/rcar-gen2-modules-common.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM="file://GPLv2-COPYING;md5=75859989545e37968a99b631ef42722e"

inherit module

DEPENDS = "linux-renesas"
PN = "lsm9ds0"
PV = "1.0.2"
PR = "r0"
SRC_URI = "file://lsm9ds0-${PV}.tar.gz"
S = "${WORKDIR}"

KERNEL_HEADER_PATH = "${KERNELSRC}/include/linux/input"

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
"
FILES_${PN} = " \
/lib/modules/${KERNEL_VERSION}/drivers/input/misc/lsm9ds0_gyr.ko \
/lib/modules/${KERNEL_VERSION}/drivers/input/misc/lsm9ds0_acc_mag.ko \
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

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

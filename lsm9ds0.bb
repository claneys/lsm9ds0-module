LICENSE = "GPLv2"
LIC_FILES_CHKSUM="file://GPLv2-COPYING;md5=75859989545e37968a99b631ef42722e"

inherit module

DEPENDS = "linux-renesas"
PN = "lsm9ds0"
PV = "1.0.2"
PR = "r0"
SRC_URI = "file://lsm9ds0-${PV}.tar.gz"
S = "${WORKDIR}"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

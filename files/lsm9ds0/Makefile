SRC		:= $(shell pwd)
KERNELINC 	?= $(KERNELDIR)/include/linux/input

obj-m += lsm9ds0_acc_mag.o
obj-m += lsm9ds0_gyr.o

all:
	$(CP) lsm9ds0.h $(KERNELINC)
	$(MAKE) -C $(KERNELSRC) M=$(SRC) modules

modules_install:
	$(MAKE) -C $(KERNELSRC) M=$(SRC) modules_install

clean:
	rm -f *.o *~ core .depend .*.cmd *.ko *.mod.c
	rm -f Module.markers Module.symvers modules.order
	rm -rf .tmp_versions Modules.symvers

# Makefile for Cadence "inca" "ncvhdl"  VHDL
#
# use  tcsh
#      source vhdl_cshrc
#      make
#
# must have set up  cds.lib  and  hdl.var  in default directory
# must have subdirectory "vhdl_lib",  mkdir  vhdl_lib, or just  make clean 

all: tadd32.out

tadd32.out:  add32.vhdl tadd32.vhdl tadd32.run
	ncvhdl -v93 add32.vhdl
	ncvhdl -v93 tadd32.vhdl
	ncelab -v93 tadd32:circuits
	ncsim -batch  -logfile tadd32.out -input tadd32.run tadd32


clean:
	rm -f *.log
	rm -rf vhdl_lib
	mkdir  vhdl_lib



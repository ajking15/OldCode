CC=gcc
CFLAGS = -g -O2 -D_REENTRANT -Wall
OBJ = error.o wrapsocket.o wrapio.o
DEPS = common.h

all: ftpclt

ftpclt:	$(OBJ) ftpcli.o
	$(CC) $(CFLAGS) -o $@ $^

%.o: %.c $(DEPS)
	$(CC) $(CFLAGS) -o $@ -c $<

clean:
	rm -rf *.o ftpcli
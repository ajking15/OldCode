CC=gcc
CFLAGS = -g -O2 -D_REENTRANT -Wall
OBJ = error.o wrapsocket.o wrapio.o
DEPS = common.h

all: client server

client: $(OBJ) tcpcli01.o
	$(CC) $(CFLAGS) -o $@ $^

server: $(OBJ) tcpserv01.o
	$(CC) $(CFLAGS) -o $@ $^

myftp:	$(OBJ) ftpcli01.o
	$(CC) $(CFLAGS) -o $@ $^

%.o: %.c $(DEPS)
	$(CC) $(CFLAGS) -o $@ -c $<

clean:
	rm -rf *.o client server myftp

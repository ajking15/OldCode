#ifndef __common_h
#define __common_h

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <sys/socket.h>/* basic socket definitions */
#include <netinet/in.h>/* sockaddr_in{} and other Internet defns */
#include <arpa/inet.h>/* inet(3) functions */
#include <string.h>

#define MAXLINE 4096/* max text line length */
#define LISTENQ 1024/* 2nd argument to listen() */
#define SAstruct sockaddr
#define SERV_PORT 3456

/* error.c */
void err_dump(const char *, ...);
void err_msg(const char *, ...);
void err_quit(const char *, ...);
void err_ret(const char *, ...);
void err_sys(const char *, ...);

/* wrapsocket.c */
int Socket(int family, int type, int protocol);
void Inet_pton(int family, const char *strptr, void *addrptr);
void Connect(int fd, const struct sockaddr *sa, socklen_t salen);
void Bind(int fd, const struct sockaddr *sa, socklen_t salen);
void Listen(int fd, int backlog);
int Accept(int fd, struct sockaddr *sa, socklen_t *salenptr);
void Close(int fd);

/* wrapio.c */
ssize_t Readline(int fd, void *ptr, size_t maxlen);
void Writen(int fd, void *ptr, size_t nbytes);
char* Fgets(char *ptr, int n, FILE *stream);
void Fputs(const char *ptr, FILE *stream);

#endif/* __common_h */

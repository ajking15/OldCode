#include "common.h"

void str_echo(int sockfd)
{
  ssize_t n;
  char line[MAXLINE];

  for(;;) {
    if ((n = Readline(sockfd, line, MAXLINE)) == 0)
      return; /* connection closed by other end */

    Writen(sockfd, line, n);

  }
}

int main(int argc, char **argv)
{
  int listenfd, connfd;
  socklen_t clilen;
  struct sockaddr_in cliaddr, servaddr;
  int port = atoi(argv[1]);
  
  //creates a socket
  listenfd = Socket(AF_INET, SOCK_STREAM,0);

  //empties the servaddr array
  bzero(&servaddr, sizeof(servaddr));
  servaddr.sin_family = AF_INET;
  servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
  servaddr.sin_port  =htons(port);

  Bind(listenfd, (SA *) &servaddr, sizeof(servaddr));

  Listen(listenfd, LISTENQ);
  int x;
  // this is an infinite loop condition ;;
  for(x = 0; x < 2; x++){
    clilen = sizeof(cliaddr);
    connfd = Accept(listenfd, (SA *) &cliaddr, &clilen);
    str_echo(connfd);
    Close(connfd);
  }

  exit(0);
}

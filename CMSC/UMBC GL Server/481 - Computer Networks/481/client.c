#include "common.h"
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>

void str_cli(FILE *fp, int sockfd)
{
  // printf("str_cli\n");
  char   sendline[MAXLINE], recvline[MAXLINE];
  char   string1[MAXLINE];//, string2[MAXLINE];
  // printf("before while\n");
  // something wrong with this loop
  while (Fgets(sendline, MAXLINE, fp) != NULL) {
    //  printf("in the while loop\n");
     // printf("buffer: %s", sendline);
    // so it gets the text line.
    //feof?
    //the hell does written do?
    // written writes the file to the socket
    Writen(sockfd, sendline, strlen(sendline));
    // printf("after writen\n");
    if (Readline(sockfd, recvline, MAXLINE) == 0)
      err_quit("str_cli: server terminated prematurely");
    //printf("after readline\n");
    strcpy(string1, recvline);
    char str[MAXLINE];
    int numChar = strlen(string1) - 1; // -1 to account for the newline char
    // size_t check = strlen(string1);
    if(string1[numChar] == '\n')
      {
	//printf("newline found");
	string1[numChar] = '\0';
      }

    //string1[strchr(string1, '\n')];
    // printf("random\n");
    sprintf(str, "%s (%d chars)\n", string1, numChar);
    
    //str[MAXLINE] = "goodbye";
    // = "( chars)";//, strlen(recvline)); 

    // printf("recvline: %s", recvline);

    //Fputs(recvline, stdout);
    Fputs(str, stdout);
  }
}

int
main(int argc, char **argv)
{

  /*
 int sockfd;
  //sockaddr_in for the internet family
  struct sockaddr_in servaddr;
  //struct addrinfo hints;
  struct addrinfo *res;
  //const char *result = argv[1];
  //int port = atoi(argv[2]);
  
  if(argc != 3)
    err_quit("usage: tcpcli <IPAddress>");

  //
  sockfd = Socket(AF_INET, SOCK_STREAM, 0);

   

  if(sockfd != -1)
    {
      //printf("socket created\n");
    } else {
    printf("socket failed\n");
  }

  //convert names.
  //hints->ai_flags = AI_PASSIVE;
  //puts an appropriate number of zero bytes in area pointed to by &servaddr
  bzero(&servaddr, sizeof(servaddr));
  servaddr.sin_family = AF_INET;
  servaddr.sin_port = htons(atoi(argv[2])); //SERV_PORT to *argv[2]
 

  getaddrinfo(argv[1], argv[2], NULL, &res);

  Bind(sockfd, (SA *) &servaddr, sizeof(servaddr));
  // Bind(sockfd, , sizeof(res));

  

  Connect(sockfd, (SA *) &servaddr, sizeof(servaddr));


  str_cli(stdin, sockfd);

  close(sockfd);
  */

  struct addrinfo hints, *res;
  int sockfd;

  memset(&hints, 0, sizeof hints);

  hints.ai_family = AF_UNSPEC;
  hints.ai_socktype = SOCK_STREAM;

  getaddrinfo(argv[1], argv[2], &hints, &res);

  sockfd = Socket(res->ai_family, res->ai_socktype, res->ai_protocol);

  //Bind(sockfd, res->ai_addr, res->ai_addrlen);
  
  Connect(sockfd, res->ai_addr, res->ai_addrlen);

  str_cli(stdin, sockfd);

  close(sockfd);

  exit(0);
}

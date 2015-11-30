#include "common.h"
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>


void str_cli(FILE *fp, int sockfd)
{
	char	sendline[MAXLINE], recvline[MAXLINE];
	char    string[MAXLINE];


	while (Fgets(sendline, MAXLINE, fp) != NULL) {
	   //p/rintf("sendline: %s", sendline);
		/*
		  Writen calls writen with socket, sentence, and num bytes
		*/
		Writen(sockfd, sendline, strlen(sendline));
		
		if (Readline(sockfd, recvline, MAXLINE) == 0)
		{
		   err_quit("str_cli: server terminated prematurely");
		}
		//p/rintf("after Readline\n");
		strcpy(string, recvline);
		char str[MAXLINE];
		int numChar = strlen(string) - 1; //-1 to account for newline

		if(string[numChar] == '\n')
		{
		   string[numChar] = '\0';
		}

		sprintf(str, "%s (%d chars)\n", string, numChar);

		

		Fputs(str, stdout);
		//p/rintf("after Fputs\n");
	}
}

int
main(int argc, char **argv)
{
   if(argc != 3)
   {
      err_quit("usage: tcpcli <IPAddress>");
   }

   //  p/rintf("Start!\n");
   struct addrinfo hints, *res;
   int sockfd;

   memset(&hints, 0, sizeof hints);

   hints.ai_family = AF_UNSPEC;
   hints.ai_socktype = SOCK_STREAM;

   //p/rintf("getaddrinfo!\n");
   getaddrinfo(argv[1], argv[2], &hints, &res);

   //p/rintf("Socket!\n");
   sockfd = Socket(res->ai_family, res->ai_socktype, res->ai_protocol);

   //p/rintf("Bind!\n");
   Bind(sockfd, res->ai_addr, res->ai_addrlen);

   //p/rintf("Connect!\n");
   Connect(sockfd, res->ai_addr, res->ai_addrlen);

   //p/rintf("str_cli\n");
   str_cli(stdin, sockfd);

   close(sockfd);

   exit(0);
}

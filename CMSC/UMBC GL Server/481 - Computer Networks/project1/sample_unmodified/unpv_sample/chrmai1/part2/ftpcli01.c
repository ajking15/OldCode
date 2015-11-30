#include "common.h"
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/time.h>
#include <unistd.h>
#include <netdb.h>
#include <errno.h>
#include <ctype.h>

int
main(int argc, char **argv)
{
   /*
     implement control structures that check the responses in the affirmative
   */

   if(argc != 2)
   {
      err_quit("usage: ftp <IPAddress>");
   }
   struct timeval tv;
   fd_set readfds;
   char login[MAXLINE];
   int status;
   status = getlogin_r(login, sizeof(login));
  
   tv.tv_sec = 4;
   tv.tv_usec = 500000;


   //printf("login: %s\n", login);   
      // ftp server that listens on tcp port 21 by default
   struct addrinfo hints, *res;//, loc, *local;
   //struct sockaddr_storage their_addr;
   int sockfd;//, sock, conSock;
   //socklen_t addr_size;
   
   memset(&hints, 0, sizeof hints);
   
   hints.ai_family = AF_INET;
   hints.ai_socktype = SOCK_STREAM;
   hints.ai_protocol = 0;
   
   getaddrinfo(argv[1], "21", &hints, &res);

   //make the socket
   sockfd = Socket(res->ai_family, res->ai_socktype, res->ai_protocol);
  
   //setsockopt(sockfd, SOL_SOCKET, SO_RCVTIMEO, &tv, sizeof(tv));

   Connect(sockfd, res->ai_addr, res->ai_addrlen);
   //sleep(1); 
   printf("Connected to ftp.gl.umbc.edu.\n");

   FD_ZERO(&readfds);
   //add sockfd to the set
   FD_SET(sockfd, &readfds);

   char response[MAXLINE];//  = "";
   int check;

   check = recv(sockfd, response, MAXLINE, 0);
   
   if(check == -1)
   {
      printf("error: %s\n", strerror(errno));
   }

   //this should print the 220 UMBC Central ftp server line
   printf("%s", response);
   
   //recv(sockfd, response, MAXLINE, 0);
   char user[MAXLINE];
   printf("Name (ftp.gl.umbc.edu: %s): ", login);
   
   fgets(user, sizeof user, stdin);
   
   char str[MAXLINE];
         
   sprintf(str, "USER %s\r\n", user);

   send(sockfd, str, strlen(str), 0);

   recv(sockfd, response, MAXLINE, 0);

   printf("%s", response);

   char pass[MAXLINE];
   printf("Password: ");
   fgets(pass, sizeof pass, stdin);
   
   char str2[MAXLINE];

   sprintf(str2, "pass %s\r\n", pass);
   
   //printf("%s", str2);

   send(sockfd, str2, strlen(str2), 0);

   
   memset(&response, 0, sizeof(response));
   
   while(select(sockfd + 1, &readfds, NULL, NULL,&tv) > 0)
   {
      recv(sockfd, response, MAXLINE, 0);
      printf("%s", response);
      memset(&response, 0, sizeof(response));
   }


   printf("Remote system type is UNIX.\n");
   printf("Supported system calls include: pwd, syst, and quit\n");
   
   int loop = 0;

   while(loop == 0)
   {
      printf("ftp> ");
      
      
      fgets(response, sizeof(response), stdin);
      //intf("%s", response);
      char quitCheck[sizeof(response)];
      int x;
      for(x = 0; x < strlen(response); x++)
      {
	 // printf("%d\n", x);
	 quitCheck[x] = tolower(response[x]);
      }
      //printf("Check: %s", quitCheck);
      if(strcmp(quitCheck, "quit\n") == 0)
      {
	 loop = 1;
	 // printf("quit now!\n");
      }

      memset(&str, 0, sizeof(str));
      
      sprintf(str, "%s\r\n", response);
      int help;
      
      help = send(sockfd, str, strlen(str), 0);
      //send(sockfd, str, strlen(str), 0);
      //printf("%d\n", help);
      
      memset(&response, 0, sizeof(response));
      char respond[MAXLINE];
      recv(sockfd, respond, MAXLINE, 0);
      printf("%s", respond);
      memset(&respond, 0, sizeof(respond));
      //free res with freeaddrinfo
   }
  
   close(sockfd);

   exit(0);
}

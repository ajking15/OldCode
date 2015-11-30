#include "common.h"
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/time.h>
#include <unistd.h>
#include <netdb.h>
#include <errno.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string.h>


#define MAXLINE 4096
#define LISTENQ 1024
#define SA      struct sockaddr


int
main(int argc, char **argv)
{
   /*
     implement control structures that check the responses in the affirmative
   */

   if(argc != 3)
   {
      err_quit("usage: ./myftp <IPAddress> <PortNumber>");
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
   
   getaddrinfo(argv[1], argv[2], &hints, &res);

   //make the socket
   sockfd = Socket(res->ai_family, res->ai_socktype, res->ai_protocol);
  
   //setsockopt(sockfd, SOL_SOCKET, SO_RCVTIMEO, &tv, sizeof(tv));

   Connect(sockfd, res->ai_addr, res->ai_addrlen);
   //sleep(1); 
   printf("Connected to %s\n", argv[1]);

   FD_ZERO(&readfds);
   //add sockfd to the set
   FD_SET(sockfd, &readfds);

   char response[MAXLINE];//  = "";
   int check;

   //waits for 220 signal
   check = recv(sockfd, response, MAXLINE, 0);
   
   if(check == -1)
   {
      printf("error: %s\n", strerror(errno));
   }

   //this should print the 220 UMBC Central ftp server line
   printf("%s", response);
   
   //recv(sockfd, response, MAXLINE, 0);
   char user[MAXLINE];
   printf("Name (%s: %s): ", argv[1], login);
   
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


   //printf("Remote system type is UNIX.\n");
   printf("Supported system calls include: help, ls, get, put, pwd, syst, and quit\n");
   
   int loop = 0;
   int tsend = 0;

   while(loop == 0)
   {
      tsend = 0;
      printf("ftp> ");
      
      
      fgets(response, sizeof(response), stdin);
      //intf("%s", response);
      char cmdCheck[sizeof(response)];
      int x;
      for(x = 0; x < strlen(response); x++)
      {
	 //convert to lowercase for string compare
	 cmdCheck[x] = tolower(response[x]);
      }

      //printf("Check: %s", quitCheck);
      if(strstr(cmdCheck, "quit") != NULL)
      {
	 tsend = 1;
	 loop = 1;
	 // printf("quit now!\n");
      } else if(strstr(cmdCheck, "ls") != NULL) {
	 tsend = 1;
	 //expects multi-line reply. in one string
	 //  should fit in 4096 letters
      } else if(strstr(cmdCheck, "get") != NULL) {
	 tsend = 2;
	 //read appropriate code. if error print message
	 // get/put/ls require additional code to parse correctly
	 //if there. open a file and continue to write the responses to 
	 // that file
      } else if(strstr(cmdCheck, "put") != NULL) {
	 tsend = 3;
	 //if there. open a file and continue to read in lines and send them
	 //  out
      } else if(strstr(cmdCheck, "pwd") != NULL) {
	 //works as is. single line reply
	 tsend = 1;
      } else if(strstr(cmdCheck, "syst") != NULL) {
	 //works as is. single line reply
	 tsend = 1;
      } else if(strstr(cmdCheck, "help") != NULL) {
	 //print a message locally. no need to send anything
	 //printf("%d\n", strlen(cmdCheck));
	 tsend = 1;
      }

      //length of put/get is 4 and ls is 3 includes \n or \0

      //needs to parse the incoming commands. and send appropriate calls
      //single line replies
      if(tsend == 1){
	 //printf("tsend == 1\n");
         // pwd, syst, quit, help, ls can be sent/recv here
	 // put user here. and have a bad sequence of commands response
	 memset(&str, 0, sizeof(str));
      
	 sprintf(str, "%s\r\n", response);
	 
	 send(sockfd, str, strlen(str), 0);
      
	 memset(&response, 0, sizeof(response));
	 char respond[MAXLINE];
	 memset(&respond, 0, sizeof(respond));
	 recv(sockfd, respond, MAXLINE, 0);
	 printf("%s", respond);
	 //free res with freeaddrinfo
      } else if(tsend == 2){
	 //GET
	 //printf("tsend == 2\n");
	 memset(&str, 0, sizeof(str));
	 sprintf(str, "%s\r\n", response);
	 //send the get command
	 send(sockfd, str, strlen(str), 0);
	 memset(&response, 0, sizeof(response));
	 char respond[MAXLINE];
	 //wait for 150/550 response tag. 
	 recv(sockfd, respond, MAXLINE, 0);
	 printf("%s", respond);

	 if(strstr(respond, "150") != NULL) {
	    //file found.
            // create a file and write all following receives to file
	    //  parse receives for EOF string
	    const char *dir_path="/afs/umbc.edu/users/c/h/chrmai1/home/481/project2/client/ftpfiles/";
	    char *cmd = strtok(cmdCheck, " ");  //will be get
	    char *file = strtok(NULL, " ");     //will be filename
	    FILE *myfile;
	    char cat[MAXLINE];
	    
	    memset(cat, 0, sizeof(cat));
	    sprintf(cat, "%s%s", dir_path, file);
	   
	    char *n = strchr(cat, '\n');
	    if(n){
	       *n = '\0';
	    }
	    //fopen on cat
	    myfile = fopen(cat, "w");
	    
	    if(myfile){
	       //file exists
	       int getLoop = 0;
	       while(getLoop == 0) {
		  memset(&cat, 0, sizeof(cat));
		  recv(sockfd, cat, sizeof(cat), 0);
		  if(strstr(cat, "$EOF$") != NULL) {
		     getLoop = 1;
		     fclose(myfile);
		  } else {
		     fprintf(myfile, "%s", cat);
		  }
	       }
	    } else {
	       //file doesn't exist
	       printf("Error retrieving file\n");
	    }
	 } else if(strstr(respond, "550") != NULL) {
	    //file not found
	    //do nothing
	 }
	 //open file.
      } else if(tsend == 3){
	 //PUT
	 //printf("tsend = 3\n");
	 memset(&str, 0, sizeof(str));
         sprintf(str, "%s\r\n", response);

         send(sockfd, str, strlen(str), 0);
         memset(&response, 0, sizeof(response));
         char respond[MAXLINE];
         //wait for 150/550 response tag.
         recv(sockfd, respond, MAXLINE, 0);
         printf("%s", respond);

         if(strstr(respond, "150") != NULL) {
            //file found.
            // create a file and write all following receives to file
            //  parse receives for EOF string
	    const char *dir_path="/afs/umbc.edu/users/c/h/chrmai1/home/481/project2/client/ftpfiles/";

	    char *cmd = strtok(cmdCheck, " ");  //will be get
	    char *file = strtok(NULL, " ");     //will be filename
	    FILE *myfile;
	    char cat[MAXLINE];
	    memset(cat, 0, sizeof(cat));
	    sprintf(cat, "%s%s", dir_path, file);
	    
	    char *n = strchr(cat, '\n');
	    if(n){
	       *n = '\0';
	    }

	    //fopen on cat
	    myfile = fopen(cat, "r");

	    if(myfile) {
	       //file exists
	       //loop over feof
	       while(!feof(myfile)){
		  memset(&cat, 0, sizeof(cat));
		  fgets(cat, MAXLINE, myfile);
		  //printf("%s", cat);
		  send(sockfd, cat, strlen(cat), 0);
	       }
	       memset(&cat, 0, sizeof(cat));
	       sprintf(cat, "$EOF$");
	       send(sockfd, cat, strlen(cat), 0);
	       fclose(myfile);
	    }
         } else if(strstr(respond, "550") != NULL) {
            //file not found. message already printed no need to 
	    // do anything else
         }

         } else {
	 printf("Invalid Command\n");
      }
   }
  
   close(sockfd);

   exit(0);
}

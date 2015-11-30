#include <string.h>
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
#include <netdb.h>
#include <arpa/inet.h>
#include <dirent.h>
#include "common.h"

#define MAXLINE 4096
#define BACKLOG 5
#define SA      struct sockaddr

void ftpconnect(char* buf, int sock);
void *get_in_addr(struct sockaddr *sa);

void *get_in_addr(struct sockaddr *sa) {
   if(sa->sa_family == AF_INET) {
      return &(((struct sockaddr_in*)sa)->sin_addr);
   }

   return &(((struct sockaddr_in6*)sa)->sin6_addr);
}

int main (int argc, char **argv){
   //check for proper number of cmd line args
   if(argc != 2) {
      //2 as the calling script is argv[0];

      printf("usage: ./ftpsrv <port number>\n");

      return -1;
   }

   char message[4096];
   int currentConnections = 0;
   fd_set master;   // master file descriptor list
   fd_set read_fds; // temp file descriptor list for select()
   int fdmax;       // maximum file descriptor number
   
   int listener;    // listening socket descriptor
   int newfd;       // newly accept()ed socket descriptor
   struct sockaddr_storage remoteaddr; // client address
   socklen_t addrlen;

   char buf[4096]; //buffer for client data
   int nbytes;

   char remoteIP[INET6_ADDRSTRLEN];

   int yes = 1; // for setsockopt() SO_REUSEADDR, below
   int i, j, rv;

   struct addrinfo hints, *ai, *p;

   FD_ZERO(&master);  //clear the master and temp sets
   FD_ZERO(&read_fds);

   // get us a socket and bind it
   memset(&hints, 0, sizeof(hints));

   hints.ai_family = AF_UNSPEC;
   hints.ai_socktype = SOCK_STREAM;
   hints.ai_flags = AI_PASSIVE;
   if((rv = getaddrinfo(NULL, argv[1], &hints, &ai)) != 0) {
      fprintf(stderr, "getaddrinfo error: %s\n", gai_strerror(rv));

      return -1;
   }
  
   for(p = ai; p != NULL; p = p->ai_next) {
      listener = socket(p->ai_family, p->ai_socktype, p->ai_protocol);
      if(listener < 0) {
	 continue;
      }

      // lose the "address already in use" error message
      setsockopt(listener, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(int));

      if(bind(listener, p->ai_addr, p->ai_addrlen) < 0) {
	 close(listener);
	 continue;
      }

      break;
   }

   // if we got here, it means we didn't get bound
   if(p == NULL) {
      fprintf(stderr, "failed to bind\n");
      
      return -1;
   }

   freeaddrinfo(ai); //done with this struct

   // listen
   if(listen(listener, 5) == -1) {
      perror("listen");

      return -1;
   }

   FD_SET(listener, &master);

   // keep track of the biggest file descriptor
   fdmax = listener; // so far, it's this one

   // main loop
   for(;;) {
      read_fds = master; //copy master set
      if(select(fdmax+1, &read_fds, NULL, NULL, NULL) == -1) {
	 perror("select");
	 return -1;
      }

      // run through existing connections looking for data to read
      for(i = 0; i <= fdmax; i++) {
	 if(FD_ISSET(i, &read_fds)) { //something trying to send
	    if(i == listener) {
	       //new connection
	       addrlen = sizeof(remoteaddr);
	       newfd = accept(listener, (struct sockaddr *)&remoteaddr, &addrlen);

	       if(newfd == -1) {
		  perror("accept\n");
	       } else {
		  if(currentConnections < 5) {
		     
		     // connection added
		     currentConnections++;
		     FD_SET(newfd, &master); //add to master set
		     if(newfd > fdmax) { //keep trak of the max
			fdmax = newfd;
		     }
		     //have to send the 220 connect sign here
		     memset(&message, 0, sizeof(message));
		     sprintf(message, "220 Welcome to my FTP Server\r\n");
		     send(newfd, message, strlen(message), 0);
		     // printf("New Connection! Huzzah!\n");
		     //printf("Current Connections: %d\n", currentConnections);
		  } else {
		     //more than allowed. close the accepted connection
		     printf("Too many connections. closed\n");
		     close(newfd);
		  }
	       }
	    } else {
	       printf("got something\n");
	       // a current connection
	       if((nbytes = recv(i, buf, sizeof(buf), 0)) <= 0) {
		  // error or connection closed
		  if(nbytes == 0) {
		     //connection closed
		     
		     printf("connection closed\n");
		  } else {
		     perror("recv");
		  }
		  currentConnections--;
		  close(i); // no point in keeping this connection
		  FD_CLR(i, &master); //remove from master set
	       } else {
		  // parses commands from connected people
		  ftpconnect(buf, i);
	       }
	    }
	 }
      }

   } //end main loop

   return 0;
}

void ftpconnect(char* buf, int sock) {
   printf("ftpconnect\n");
   //220 cmd sent. filter incoming codes
   //this method will be what takes care of all connections
   char message[MAXLINE];
   char response[MAXLINE];
   //printf("ftpconnect\n");
   //printf("Socket: %d\n", sock);
   
   memset(&response, 0, sizeof(response));
   //this gets called each time there is a message   
   //recv(sock, response, MAXLINE, 0);
   //printf("%s", response);
   char cmdCheck[strlen(buf)];
   int x;
   printf("%s\n", buf);
   for(x = 0; x < strlen(buf); x++){
      //convert to lowercase for string compare
      cmdCheck[x] = tolower(buf[x]);
   }
   
   memset(&message, 0, sizeof(message));
   //printf("%s\n", cmdCheck);
   //printf("%s", cmdCheck);
   if(strstr(cmdCheck, "pass") != NULL) {
      printf("pass\n");
      sprintf(message, "230 User Granted Anonymous Access, Proceed\r\n");
      send(sock, message, strlen(message), 0);
   } else if(strstr(cmdCheck, "user") != NULL) {
      printf("user\n");
      //assumes user is ftp. only case we're supposed to handle
      sprintf(message, "331 Anonymous login ok, Send Password\r\n");
      send(sock, message, strlen(message), 0);
   } else if(strstr(cmdCheck, "pwd") != NULL) {
      printf("pwd\n");
      //Use the system command or just send back "/" as root since I have no
      // lower directories
      sprintf(message, "257 \"/\" is the current directory\r\n");
      send(sock, message, strlen(message), 0);
   } else if(strstr(cmdCheck, "syst") != NULL) {
      printf("syst\n");
      //Use the system command or just send back a canned response.
      sprintf(message, "215 UNIX Type: L8\r\n");
      send(sock, message, strlen(message), 0);
   } else if(strstr(cmdCheck, "help") != NULL) {
      printf("help\n");
      sprintf(message, "Supported Commands:\r\nuser\r\nls <dir(optional)>\r\nget <filename>\r\nput <filename>\r\npwd\r\nsyst\r\nquit\r\nhelp\r\n");

      send(sock, message, strlen(message), 0);

   } else if(strstr(cmdCheck, "quit") != NULL) {
      printf("quit\n");
      sprintf(message, "221 Goodbye.\r\n");
      send(sock, message, strlen(message), 0);
   } else if(strstr(cmdCheck, "ls") != NULL) {
      printf("ls\n");
      struct dirent *dp;

      const char *dir_path="/afs/umbc.edu/users/c/h/chrmai1/home/481/project2/server/ftpfiles";
      DIR *dir = opendir(dir_path);
      sprintf(message, "150 Opening ASCII mode data connection for file list\n");
      while ((dp=readdir(dir)) != NULL) {
	 if(strcmp(dp->d_name, ".") == 0 || strcmp(dp->d_name, "..") == 0) {

	 } else {
	    sprintf(message, "%s%s\n", message, dp->d_name);
	    //printf("%s\n", tmp);

	 }

      }
      // prints files in the dir
      sprintf(message, "%s\r\n", message);
      send(sock, message, strlen(message), 0);
   } else if(strstr(cmdCheck, "get") != NULL) {
      printf("get\n");
      //need to split the cmdCheck string on space to get the filename
      //loops over recv
      const char *dir_path="/afs/umbc.edu/users/c/h/chrmai1/home/481/project2/server/ftpfiles/";

      char *cmd = strtok(cmdCheck, " ");  //will be get
      char *file = strtok(NULL, " ");     //will be filename
      FILE *myfile;
      //check that they actually gave a file name. this isn't supposed to happen
      if(file != NULL){
	 printf("file isn't null\n");
	 char cat[MAXLINE];
	 memset(&cat, 0, sizeof(cat));
	 sprintf(cat, "%s%s", dir_path, file);
	 printf("Filepath: %s\n", cat);

	 //cat[strcspn(cat, '\n')] = '\0';
	 //strip new line
	 char *n = strchr(cat, '\n');
	 if(n){
	    *n = '\0';
	 }

	 myfile = fopen(cat, "r");
	 
	 
	 if(myfile){
	    //file exists
	    printf("file exists\n");
	    memset(&cat, 0, sizeof(cat));
	    sprintf(cat, "150 File Transferred\r\n");
	    send(sock, cat, sizeof(cat), 0);
	    //loops over send
	    
	    while(!feof(myfile)){
	      memset(&cat, 0, sizeof(cat));
	      //recv(sock, cat, sizeof(cat), 0);
	      //sent by itself
	      fgets(cat, MAXLINE, myfile);
	      //printf("%s", cat);
	      send(sock, cat, strlen(cat), 0);
	    }
	    memset(&cat, 0, sizeof(cat));
	    sprintf(cat, "$EOF$");
	    send(sock, cat, strlen(cat), 0);
	    fclose(myfile);
	 } else {
	    //file doesn't exist
	    printf("File doesn't exist\n");
	    memset(&cat, 0, sizeof(cat));
	    sprintf(cat, "550 File not Found/Available\r\n");
	    send(sock, cat, strlen(cat), 0);
	 }
	 
      } 
      //try to open file.
      //file exists send 150 code. file clear
      //file doesn't exist. send 550 code File unavailable
   } else if(strstr(cmdCheck, "put") != NULL) {
      printf("put\n");
      //need to split the chmCheck string on space to get the filename
      //loops over send
      const char *dir_path="/afs/umbc.edu/users/c/h/chrmai1/home/481/project2/server/ftpfiles/";

      char *cmd = strtok(cmdCheck, " ");  //will be put
      char *file = strtok(NULL, " ");     //will be filename
      FILE *myfile;
      char cat[MAXLINE];

      //check that a file name was given
      if(file != NULL) {
	 printf("file isn't null\n");
	 memset(cat, 0, sizeof(cat));
	 sprintf(cat, "%s%s", dir_path, file);
	 printf("Filepath: %s\n", cat);

	 //strip newline
	 char *n = strchr(cat, '\n');
	 if(n){
	    *n = '\0';
	 }

	 myfile = fopen(cat, "w");
	 
	 if(myfile){
	    //file exists
	    memset(&cat, 0, sizeof(cat));
	    sprintf(cat, "150 File Transferred\r\n");
	    send(sock, cat, strlen(cat), 0);
	    //loop over receive 
	    int putLoop = 0;
	    while(putLoop == 0) {
	       memset(&cat, 0, sizeof(cat));
	       recv(sock, cat, sizeof(cat), 0);
	       printf("recv: %s", cat);
	       if(strstr(cat, "$EOF$") != NULL) {
		  //file ended
		  printf("File Ended\n");
		  putLoop = 1;
		  fclose(myfile);
	       } else {
		  fprintf(myfile, "%s", cat);
		  //printf("%s", cat);
	       }
	    }
	 } else {
	    //file doesn't exist
	    printf("error creating file\n");
	    memset(&cat, 0, sizeof(cat));
	    sprintf(cat, "550 File Could Not be Created\r\n");
	    send(sock, cat, strlen(cat), 0);
	 }
      }
   }
}

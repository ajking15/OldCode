#include <stdio.h>
#include <stdlib.h>

#define REQUIRED     1
#define RECOMMENDED  0
#define MIN          1
#define MAX         19
#define EXIT        -1

typedef struct bookstore
{
      int bookNumber;
      int courseNumber;
      int status;
      char isbn[18];
      char title[45];
      char author[34];
      char publisher[20];
      double price;
      int copies;
      int purchased;
      int bookTotal;
}BOOKSTORE;

int main()
{
   int numBooks, *numBooksPtr;
   numBooksPtr = &numBooks;
   int i = 0;
   char filename[20];
   BOOKSTORE *information;
   FILE *ifp;
   printf("Enter the name of the text file to be examined: ");
   scanf("%s", filename);
   printf("\n");

   ifp = fopen(filename, "r");

   /* check to see if file opened correctly */
   if(ifp == NULL)
   {
      fprintf(stderr, "Error: file inventory.dat failed to open\n");

      exit(-1);
   }

   fscanf(ifp, "%d", numBooksPtr);

   /* Get a block of memory big enough to hold numItems BOOKSTORE */
   information = (BOOKSTORE *) calloc(*numBooksPtr, sizeof(BOOKSTORE));
   
   /* Check to make sure memory is available */
   if(information == NULL)
   {
      printf("Not enough memory\n");
      
      exit(-2);
   }
   for(i = 1; i <= *numBooksPtr; i++)
   {
      fscanf(ifp, "%d %d %d %s %s %s %s %lf %d",
	     &information[i].bookNumber,
	     &information[i].courseNumber, &information[i].status,
	     information[i].isbn,
	     information[i].title,  information[i].author,
	     information[i].publisher, &information[i].price,
	     &information[i].copies);
   }

   fclose(ifp);

   for(i = 1; i <= *numBooksPtr; i++)
   {
      printf("%d %d %d %s %s %s %s %.2f %d\n", 
	     information[i].bookNumber, information[i].courseNumber,
	     information[i].status,  information[i].isbn,
	     information[i].title, information[i].author,
	     information[i].publisher, information[i].price,
	     information[i].copies);
   }

   free(information);

   return 0;
}

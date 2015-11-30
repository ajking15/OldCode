/******************************************************************************
 ** File:      bookstore.h
 ** Author:    Christopher Mai
 ** Date:      10/31/07
 ** Section:   0203
 ** E-mail:    chrmai1@umbc.edu
 **
 **  This file holds all #defines and function prototypes to be used in 
 ** conjunction with bookstore.c and proj3.c
 *****************************************************************************/

#include "bookstore.h"

/******************************************************************************
 ** PrintGreeting --
 **  Prints greeting for this program and describes its function to the user
 **   Input:  void
 **   Output: void
 *****************************************************************************/

void PrintGreeting()
{
   printf("\nGood Day, this program simulates an online book store.\n");
   printf("You may browse through our selection and purchase whatever\n");
   printf("you want. If you purchase anything an invoice will be created\n");
   printf("if you would like to see what you have bought\n");
}

/******************************************************************************
 ** PrintInstructions --
 **  Prints instructions to the user to detail how to use this program
 **   Input:  void
 **   Output: void
 *****************************************************************************/

void PrintInstructions()
{
   printf("\n1 -  enter the name of the file from which to draw all the\n");
   printf("       information from, inventory.dat\n");
   printf("2 - using integers navigate the menu to select a book\n");
   printf("3 - still using integers select how many books you would like\n");
   printf("    to buy and if you find that you do not with to purchase\n");
   printf("    that book select zero\n");
   printf("4 -  exit the program by inputting -1 and if you have made a\n");
   printf("     purchase an invoice will be created\n");
}

/******************************************************************************
 ** OpenInventoryFile --
 **  Opens the inventory file from which all information concerning books are
 **  scanned
 **   Input:  number of books pointer so it can be modified
 **   Output: incoming file pointer
 *****************************************************************************/

FILE* OpenInventoryFile(int *numBooksPtr)
{
   FILE *ifp;
   char fileName[15];
   
   /* gain name of inventory file from user */
   printf("\nEnter the name of the inventory file: ");
   scanf("%s", fileName);
   printf("\n");
   
   /* open the document to the program */
   ifp = fopen(fileName, "r");
   
   /* check to see if file opened correctly */
   if(ifp == NULL)
   {
      fprintf(stderr, "Error: file %s failed to open.\n", fileName);
      
      exit(-1);
   }
   
   /* scan the number of books */
   fscanf(ifp, "%d", numBooksPtr);
   
   return ifp;
}

/******************************************************************************
 ** GetMemory --
 **  Obtains a specific amount of memory for use by this program
 **   Input:  number of books and information structure
 **   Output: updated structure
 *****************************************************************************/

BOOKSTORE* GetMemory(int *numBooksPtr, BOOKSTORE *information)
{  
   /* Get a block of memory big enough to hold all the necessary information */
   information  = (BOOKSTORE *) calloc(*numBooksPtr, sizeof(BOOKSTORE));
   
   /* Check to make sure memory is available */
   if(information == NULL)
   {
      fprintf(stderr, "Error not enough memory\n");
      
      exit(-2);
   }
   
   return information;
   
}

/******************************************************************************
 ** ScanFile --
 **  Scans rest of inventory.dat for all information and is stored in struct
 **   Input:  *ifp, the number of books, bookstore structure
 **   Output: bookstore structure
 *****************************************************************************/

BOOKSTORE* ScanFile(FILE *ifp, int *numBooksPtr, BOOKSTORE *information)
{
   int i = 0;
   
   /* loop to absorb each line of information for every book */
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
   
   /* closes the file as all information has already been absorbed */
   fclose(ifp);

   return information;
}

/******************************************************************************
 ** PrintMenu --
 **  Prints the menu which will be used for interfacing with the bookstore
 **   Input:  structure with all the information
 **   Output: void
 *****************************************************************************/

void PrintMenu(BOOKSTORE *information)
{
   int i = 1;

   /* loop to print out the menu interface */
   for(i = MIN; i <= MAX; i++)
   {
      printf("%2d -", information[i].bookNumber);
      printf(" %-45s ", information[i].title);
      printf("CMSC %d ", information[i].courseNumber);
      
      /* if statements change scanned values of 1 and 0 to their **
      ** Required and Reccomended values                         */
      if(information[i].status == 1)
      {
	 printf("- Required\n");
      }
      
      if(information[i].status == 0)
      {
	 printf("- Recommended\n");
      }
   }  
   
   printf("\n");
   
}

/*****************************************************************************
 ** GetValidBook --
 **  Ensures an integer that is usable by the program to either display a book
 **  or quit the program
 **   Input:  void
 **   Output: integer for use by DisplayBook
 *****************************************************************************/

int GetValidBook()
{
   /* ensures loop is entered */
   int book = -5;
   
   /* loop ensures a number selected that will correspond to a book */
   while(book < MIN || book > MAX)
   {
      printf("To view a book's information enter "); 
      printf("its number or -1 to quit: ");
      scanf("%d", &book);
      
      /* checks for exit */
      if(book == EXIT)
      {
	 /* returns negative one because its invalid in the loop */
	 return book;
      } 
      
   }
   
   return book;
   
}

/*****************************************************************************
 ** DisplayBook --
 **  Displays the book to the user with all relevant information
 **   Input:  information structure and bookNumber
 **   Output: void
 *****************************************************************************/

void DisplayBook(int bookNumber, BOOKSTORE *information)
{
   /* displays required information for book choice */
   printf("\nInventory #: %d\n",  bookNumber);
   printf("ISBN:        %-s\n",   information[bookNumber].isbn);
   printf("Title:       %-s\n",   information[bookNumber].title);
   printf("Author:      %-s\n",   information[bookNumber].author);
   printf("Publisher:   %-s\n",   information[bookNumber].publisher);
   printf("Price:       $%.2f\n", information[bookNumber].price);
   printf("Number of copies avaiable:  %d\n", information[bookNumber].copies);
}

/*****************************************************************************
 ** GetValidSale --
 **  Ensures a proper integer input for a variable stock
 **   Input:  information structure and number of book
 **   Output: void
 *****************************************************************************/

void GetValidSale(int bookNumber, BOOKSTORE *information)
{
   /* ensures loop is entered */
   int numPurchased = -1;
   
   printf("\nHow many would you like to order?\n");
   
   /* makes sure # input is valid in context of the changing # of copies */ 
   while(numPurchased < 0 ||
	 numPurchased > information[bookNumber].copies)
   {
      printf("Please enter an integer between 0 and %d: ", 
	     information[bookNumber].copies);
      scanf("%d", &numPurchased);
   }
   
   /* alters numPurchased member of the structure for use in CalculateCost */
   information[bookNumber].numPurchased += numPurchased;
   
   /* decreases number of copies to reflect sales */
   information[bookNumber].copies -= numPurchased;
}

/****************************************************************************
 ** CalculateCost --
 **  Calculates cost upon exiting the program
 **   Input:  information structure and book number
 **   Output: subtotal without tax
 *****************************************************************************/

double CalculateCost(BOOKSTORE *information, int bookNumber)
{
   int i = 1;
   double total = 0;
   
   /* makes sure number selected is not the signal to quit the program */
   if(bookNumber != EXIT)
   {
      /* alters subtotal for each book individually */
      information[bookNumber].bookTotal =
         (information[bookNumber].numPurchased
          * information[bookNumber].price);
   } else {
      /* loops to add up all values for individual subtotals to come up **
      ** with an overall subtotal for the program                       */
      for(i = 1; i <= MAX; i++)
      {
	 total += information[i].bookTotal;
      }
      
   }    
   
   return total;
   
}

/****************************************************************************
 ** CreateInvoice --
 **  Creates invoice upon exiting the program if something has been purchased
 **   Input:  subtotal calculated by CalculateCost function and bookstore
 **           structure and output file pointer
 **   Output: void
 ****************************************************************************/

void CreateInvoice(double subTotal, BOOKSTORE *information, FILE *ofp)
{
   int i = 0;
   double tax, total;
   
   /* opens a file for the invoice to be written to */
   ofp = fopen("invoice.txt", "w");
   
   /* makes sure files opens correctly */
   if(ofp == NULL)
   {
      fprintf(stderr, "couldn't open invoice.txt\n");
      
      exit(-3);
   }
   
   /* prints invoice */
   fprintf(ofp, "\nTitle                                          QTY");
   fprintf(ofp, "   Unit Price\n\n");
   
   /* checks all books for purchases */
   for(i = 1; i <= MAX; i++)
   {
      /* determines if a sale has been made for this book */
      if(information[i].numPurchased != 0)
      {
	 /* prints out necessary information for sold books only */
	 fprintf(ofp, "%-46s %3d @", information[i].title, 
		 information[i].numPurchased);
	 fprintf(ofp, "   %6.2f        %10.2f\n", information[i].price,
		 information[i].bookTotal);
      }
      
   }
   
   /* prints a line to separate books from subtotals and tax */
   for(i = 0; i < 79; i ++)
   {
      fprintf(ofp, "-");
   }
   
   fprintf(ofp, "\n");
   
   /* prints subTotal whitespace line */
   for(i = 0; i <52; i++)
   {
      fprintf(ofp, " ");
   }
   
   fprintf(ofp, " Subtotal        %10.2f\n", subTotal);
   
   /* prints sales tax whitespace line */
   for(i = 0; i <52; i++)
   {
      fprintf(ofp, " ");
   }
   
   tax = subTotal * 0.05;
   
   fprintf(ofp, "Sales Tax        %10.2f\n", tax);
   
   /* prints whitespace line for subtotal and tax from final total*/
   for(i = 0; i <52; i++)
   {
      fprintf(ofp, " ");
   }
   
   /* prints seperator line for subtotal and tax from final total*/
   fprintf(ofp, "---------------------------");
   
   fprintf(ofp, "\n");
   
   /* prints final total line whitespace */
   for(i = 0; i <52; i++)
   {
      fprintf(ofp, " ");
   }
   
   /* calculates final total */
   total = subTotal + tax;
   
   fprintf(ofp, "  Total $        %10.2f", total);
   
   fprintf(ofp, "\n");
   
   /* closes invoice file */
   fclose(ofp);
}
/****************************************************************************
 ** CreateNewInventory --
 **  Creats newInventory upon exiting program with updated values
 **   Input:  information structure, number of books, output file pointer
 **   Output: void
 *****************************************************************************/

void CreateNewInventory(BOOKSTORE *information, FILE *ofp, int numBooks)
{
   int i = 0;
   
   /* creates a file for the new inventory */
   ofp = fopen("newInventory.dat", "w");
   
   /* prints all information in the same format as old inventory file */
   fprintf(ofp, "%d\n", numBooks);
   
   for(i = MIN; i <= MAX; i++)
   {
      fprintf(ofp, "%d %d %d %s %s %s %s %.2f %d\n",
	      information[i].bookNumber,
	      information[i].courseNumber, information[i].status,
	      information[i].isbn,
	      information[i].title,  information[i].author,
	      information[i].publisher, information[i].price,
	      information[i].copies);
   }
   
   /* closes new inventory file */
   fclose(ofp);
   
}

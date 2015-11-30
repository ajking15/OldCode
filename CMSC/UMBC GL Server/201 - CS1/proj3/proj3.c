/*****************************************************************************
 ** File:       proj3.c
 ** Author:     Christopher Mai
 ** Date:       10/31/07
 ** Section:    0203
 ** E-mail:     chrmai1@umbc.edu
 **
 **  This is the main file for project 3. This program simulates an online
 ** bookstore and will make use of pointers and structures to run. This 
 ** program also makes use of stdlib.h to open and close outside files and
 ** scan information from them.
 **
 ** Files required to run:
 **   1. inventory.dat --  contains data to be read in by fscanf
 **   2. bookstore.h   -- contains function prototypes and #defines
 **   3. bookstore.c   -- contains function definitions
 *****************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include "bookstore.h"

int main()
{
   int numBooks = 0, bookNumber, infinite = 0;
   double sale = 0.0;
   BOOKSTORE *information;
   FILE *ifp, *ofp;
   
   /* Greets and prints instructions for the program */
   PrintGreeting();
   
   PrintInstructions();
   
   /* opens the inventory file indicated by user */
   ifp = OpenInventoryFile(&numBooks);
   
   /* secures an exact amount of memory necessary for the program */
   information = GetMemory(&numBooks, information);
   
   /* scans the inventory file of all information */
   information = ScanFile(ifp, &numBooks, information);
   
   /* reprints the book list until the user indicates end */
   while(infinite == 0)
   {
      /* prints menu of all books */
      PrintMenu(information);
      
      /* makes sure number given by user can be used by the program */
      bookNumber = GetValidBook(information);
      
      /* checks to see if user indicated that they wanted to quit */
      if(bookNumber != EXIT)
      {
	 /* displays book information to aid in buying choice */
	 DisplayBook(bookNumber, information);
	 
	 /* checks to make sure that number bought is feasible */
	 GetValidSale(bookNumber, information);
	 
	 /* keeps track of books currently bought */
	 sale = CalculateCost(information, bookNumber);
      } else {
	 /* gives instructions on how to access invoice **
	 ** invoice only avaible if purchase is made    */
	 printf("To view the invoice type - more invoice.txt -");
	 printf(" at Linux prompt\n");
	 
	 /* makes a final tally of the cost */
	 sale = CalculateCost(information, bookNumber); 
	 
	 /* checks if anything has been purchased */
	 if(sale != 0)
	 {
	    /* creates an invoice with sales tally */
	    CreateInvoice(sale, information, ofp);
	    
	    /* creates a newInventory.dat file with updated numbers */
	    CreateNewInventory(information, ofp, numBooks);
	 }	 
	 /* changes the value of the loop control to terminate */
	 infinite = EXIT;
      }
      
   }
   
   /* returns all allocated back to the systerm */
   free(information);
   
   return 0;
}

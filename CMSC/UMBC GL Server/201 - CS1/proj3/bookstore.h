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

#include <stdio.h>
#include <stdlib.h>

#define REQUIRED     1
#define RECOMMENDED  0
#define MIN          1
#define MAX         19
#define EXIT        -1

typedef struct bookstore
{
      int    bookNumber;
      int    courseNumber;
      int    status;
      char   isbn[18];
      char   title[45];
      char   author[34];
      char   publisher[20];
      double price;
      int    copies;
      int    numPurchased;
      double bookTotal;
}BOOKSTORE;


/******************************************************************************
 ** PrintGreeting --
 **  Prints greeting for this program and describes its function to the user
 **   Input:  void
 **   Output: void
 *****************************************************************************/

void PrintGreeting();

/******************************************************************************
 ** PrintInstructions --
 **  Prints instructions to the user to detail how to use this program
 **   Input:  void
 **   Output: void
 *****************************************************************************/

void PrintInstructions();

/******************************************************************************
 ** OpenInventoryFile --
 **  Opens the inventory file from which all information concerning books are
 **  scanned
 **   Input:  number of books pointer so it can be modified
 **   Output: numBooksPtr with value scanned from file
 *****************************************************************************/

FILE* OpenInventoryFile(int *numBooksPtr);

/******************************************************************************
 ** GetMemory --
 **  Obtains a specific amount of memory for use by this program
 **   Input:  number of books and bookstore structure
 **   Output: void
 *****************************************************************************/

BOOKSTORE* GetMemory(int *numBooksPtr, BOOKSTORE *information);

/******************************************************************************
 ** ScanFile --
 **  Scans rest of inventory.dat for all information and is stored in struct
 **   Input:  *ifp, the number of books, bookstore structure
 **   Output: data for books
 *****************************************************************************/

BOOKSTORE* ScanFile(FILE *ifp, int *numBooksPtr, BOOKSTORE *information);

/******************************************************************************
 ** PrintMenu --
 **  Prints the menu which will be used for interfacing with the bookstore
 **   Input:  structure with all the information
 **   Output: void
 *****************************************************************************/

void PrintMenu(BOOKSTORE *information);

/*****************************************************************************
 ** GetValidBook --
 **  Ensures an integer that is usable by the program to either display a book
 **  or quit the program
 **   Input:  void
 **   Output: integer for use by DisplayBook
 *****************************************************************************/

int GetValidBook();

/*****************************************************************************
 ** DisplayBook --
 **  Displays the book to the user with all relevant information
 **   Input:  information structure and bookNumber
 **   Output: void
 *****************************************************************************/

void DisplayBook(int bookNumber, BOOKSTORE *information);

/*****************************************************************************
 ** GetValidSale --
 **  Ensures a proper integer input for a variable stock
 **   Input:  information structure and number of book
 **   Output: void
 *****************************************************************************/

void GetValidSale(int bookNumber, BOOKSTORE *information);

/****************************************************************************
 ** CalculateCost --
 **  Calculates cost upon exiting the program
 **   Input:  information structure and book number
 **   Output: subtotal
 *****************************************************************************/

double CalculateCost(BOOKSTORE *information, int bookNumber);

/****************************************************************************
 ** CreateInvoice --
 **  Creates invoice upon exiting the program if something has been purchased
 **   Input:  subtotal calculated by CalculateCost function and book number
 **   Output: void
 ****************************************************************************/

void CreateInvoice(double subTotal, BOOKSTORE *information, FILE *ofp);

/****************************************************************************
 ** CreateNewInventory --
 **  Creats newInventory upon exiting program with updated values
 **   Input:  information structure
 **   Output: void
 *****************************************************************************/

void CreateNewInventory(BOOKSTORE *information, FILE *ofp, int numBooks);

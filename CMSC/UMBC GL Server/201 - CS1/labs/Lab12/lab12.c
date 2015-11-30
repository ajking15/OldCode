
/*******************************************************
* Filename:     lab12.c                                *
* Author:       Carrie Desmond                         *
* Modified by:  Natalie Podrazik   	  	       *
* Date Written: 03/18/03                               *
* Date Modified:05/01/05			       *
* Section:      101                                    *
* SSN:          123-45-6789                            *
* EMail:        carrie1@cs.umbc.edu/                   *
*               natalie2@umbc.edu                      *
*                                                      *
* This file is the main driver for Lab 12, which will  *
* demonstrate opening files and validating command line*
* arguments in addition the following linked list ops: *
*   - creating a linked list with a separate head node *
*   - deleting a specific node from the linked list    *
*   - printing the linked list                         *
*   - calling a function that deletes the linked list  * 
********************************************************/

#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"

/****************** Step 7a ***************/
#include "destroylist.h"

void ReadFile(FILE* ifp, HEADSTRUCTURE* headStructurePtr);

int main(int argc, char** argv)
{
   FILE*   ifp;
   int     id;           /* the id of the student to delete */
   

   /******************* Step 2a *******************/
   HEADSTRUCTURE headStructure;
   
   /* Check the command line arguments */
   if(argc != 2)
   {
      fprintf(stderr,"Not enough command line arguments!\n");
      fprintf(stderr,"Use %s <filename>\n", argv[0]);
      exit(-1);
   }
   
   /* Make sure the datafile given at cmdline is valid. */
   ifp = fopen(argv[1], "r");
   if(ifp == NULL)
   {
      fprintf(stderr,"File %s could not be opened!\n", argv[1]);
      exit(-2);
   }


   printf("Welcome to the last lab of the semester! :) \n\n");

   /******************* Step 2b ********************/

  
   headStructure.numNodes = 0;
   headStructure.classSum = 0;
   headStructure.head     = NULL;
   

   ReadFile(ifp, &headStructure);
  
   /******************* Step 3b ******************/


   fclose(ifp);

   /******************* Step 3c ******************/
   PrintList(&headStructure);

   /******************* Step 4a ********************/
   
   
   printf("\n\tPlease enter the id# to delete from the list: ");
   scanf("%d", &id);
   

   /******************* Step 4b ********************/

  if (Delete(&headStructure, id) < 0 )
   {
      printf("\tStudent was NOT in the class list.\n\n");
   }
   else
   {
      printf("\tStudent was successfully deleted from class list.\n\n");
   } 
   

  /******************* Step 4c *********************/
  PrintList(&headStructure);
  
  /******************* Step 7b **********************/
  DestroyList(&headStructure.head);
 
   return 0;
}

void ReadFile(FILE* ifp, HEADSTRUCTURE* headStructurePtr)
{
   STUDENT tempStudent;  /* to hold contents just read in loop */ 
   NODEPTR tempNode;     /* ptr to a node just created */

   /******************* Step 3a *******************/
   while(fscanf(ifp, "%d", &tempStudent.id) != EOF)
   {
      fscanf(ifp, "%s", tempStudent.firstName);
      fscanf(ifp, "%s", tempStudent.lastName);
      fscanf(ifp, "%d", &tempStudent.grade);

      tempNode = CreateNode();

      SetData(tempNode, tempStudent);
      Insert(headStructurePtr, tempNode);
   }
}

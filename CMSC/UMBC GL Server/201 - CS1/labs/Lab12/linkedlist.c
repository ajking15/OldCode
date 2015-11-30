/*************************************************
* Filename:     linkedlist.c                     *
* Author:       Sue Bogar                        *
* Modified by:  Natalie Podrazik		 *
* Date Written: 11/17/98                         *
* Date Modified:05/01/05			 *
* Section:      101                              *
* SSN:          123-45-6789                      *
* EMail:        bogar@cs.umbc.edu/               *
*               natalie2@umbc.edu                *
*                                                *
* Description:  This file contains the functions *
* necessary to work with a linked list that uses *
* a separate headStructure.                           *
*   This set of functions provide the operations *
* needed including creation of a node, insertion,*
* deletion, determining if a list is empty and   *
* the printing of the list.                      *
**************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "linkedlist.h"


/********************************************
* Function: CreateNode                      *
* Input:    none                            *
* Output:   memory for a node is malloced   *
*            and a pointer (nodePtr) to the *
*            memory is returned to the user;*
*            the fields of the newly        *
*            created struct are initialized *
* NOTE:     Exits if there is insufficient  *
*            memory.                        *
*********************************************/ 
NODEPTR CreateNode (void)
{
   NODEPTR newNode;

   /* Get the space needed for the node */
   newNode = (NODEPTR) malloc (sizeof(NODE));
   if (newNode == NULL)
   {
      fprintf (stderr, "Out of Memory - CreateNode\n");
      exit (-1);
   }

   /* Initialize the members to blank or 0. */
   strcpy(newNode -> data.firstName, "");
   strcpy(newNode -> data.lastName,  "");
   newNode -> data.grade = 0;
   newNode -> data.id    = 0;
   newNode -> next = NULL;

   return newNode;
}


/********************************************
* Function: SetData                         *
* Input:    a pointer to a node (NODEPTR),  *
*            and the data to be placed into *
*            the node (STUDENT)             *
* Output:   the data member of the node     *
*            pointed to (temp->data) is     *
*            populated with the value       *
*            passed in                      *
*********************************************/ 
void SetData (NODEPTR temp, STUDENT newStudent)
{
   temp -> data = newStudent;
} 


/*************************************************
* Function: Insert                               *
* Input:    a pointer to the address of the      *
*            head node (NODEPTR* head);          *
*            the address of the node to          *
*            add (NODEPTR temp).                 *
* Output:   the node 'temp' is added to the      *
*            linked list at the end; the         *
*            headStructure's number of nodes is  *
*            incremented and class sum is        *
*            adjusted to reflect the             *
*            addition of the new student.        *
*************************************************/  
void Insert (HEADSTRUCTURE* headStructurePtr, NODEPTR temp)
{
   NODEPTR prev, curr;

   /* If the list is empty, add the new node at *
    * the headStructure's head. */
   if ( IsEmpty (headStructurePtr -> head))
   {
       headStructurePtr -> head = temp;
   }
   else
   {
      prev = NULL;
      curr = headStructurePtr -> head;

      /* traverse the list until the end */
      while (curr != NULL)
      {
	 prev = curr;
	 curr = curr -> next;
      }
  
      /* insert the node, temp, at the end */
      prev -> next = temp;
     
   }

   /******************* Step 5b *******************/
   
   headStructurePtr->numNodes++;
   headStructurePtr->classSum += temp->data.grade;
}


/******************************************************
* Function: Delete                                    *
* Input:    a pointer to a pointer to the             *
*            address of the headStructure             *
*            (HEADSTRUCTURE*); social security #      *
*            of student to be deleted (int)           *
* Output: 1.if found, the first node with             *
*            the corresponding value                  *
*            (target) is deleted from the             *
*            linked list                              *
*         2.if the node is deleted, the               *
*            value found in the deleted               *
*            node (target) is returned and            *
*            the headStructure's number of nodes      *
*            is decremented, and its class            *
*            sum is also adjusted.                    *
*         3.if no node is deleted, -1 is              *
*            returned                                 *
******************************************************/
int Delete (HEADSTRUCTURE* headStructurePtr, int target)
{
   double foundGrade;
   NODEPTR prev = NULL, curr = NULL;
   int found = -1;

   if (IsEmpty (headStructurePtr -> head))
   {
      /* Error deleting on an empty list */
      return -1;
   }
   
   /* traverse the list until the 
   target value is found */
   
   prev = NULL;
   curr = (headStructurePtr) -> head;

   /* first check to see if the node we want is *
    * the first in the list..because then we'd  *
    * have to modify the headStructurePtr            */
   if(curr->data.id == target)
   {
      headStructurePtr->head = curr->next;
      foundGrade = curr->data.grade;
      free(curr);
      found = target;
   }  

   /* otherwise, traverse through the list *
    * to look for the target               */
   else
   {
      while ((curr != NULL) && 
             (curr->data.id != target))
      {
         prev = curr;
         curr = curr -> next;
      }
   
      /* the target was found */      
      if(curr != NULL)
      {
         /* delete the node the contains
	    the target value */
         prev -> next = curr -> next;
         foundGrade = curr -> data.grade;
         free(curr);
     
         found = target;
      }

      /* the target was not found */
      else
      {
         found = -1;
      }
   }     

   /* if the target was found, found will *
    * not be -1...so adjust the values in *
    * the headStructure.                       */
   if (found != -1)
   {
      /******************* Step 5a *******************/

      
      headStructurePtr->numNodes--;
      headStructurePtr->classSum -= foundGrade;
      
   }
      
   return found;
}


/********************************************
* Function: IsEmpty                         *
* Input:    a pointer to the head of the    *
*            linked list                    *
* Output:   returns 1 (true) if the list is *
*            empty; returns 0 (false) if    *
*            the list is not empty          *
*********************************************/
int IsEmpty (NODEPTR head)
{
   /* If the pointer to the list is
   NULL then there is no list.  The
   list is empty, so we return true.
   */
   return (head == NULL);
}


/*************************************************
* Function: PrintList                            *
* Input:    a pointer to the headStructure of the*
*            list                                *
* Ouput:    each node in the list is             *
*            printed according to the            *
*            format specified                    *
*************************************************/ 
void PrintList (HEADSTRUCTURE * headStructurePtr)
{
    NODEPTR curr;
    double aveGrade = 0;

    if (IsEmpty (headStructurePtr->head))
    {
        printf ("The list is empty\n");
    }
    else
    {
        /* set the current pointer to the first node of the list */
        curr = headStructurePtr->head;

        printf("First Name           Last Name            ID           Grade\n");
        printf("-------------------- -------------------- ------------ -------\n");

        /*Until the end of the list*/
        while (curr != NULL)
        {
            /* print the current data item */
            printf("%-20s %-20s %-12d %6d%%\n",   
	            curr -> data.firstName, 
                    curr -> data.lastName, 
                    curr -> data.id, 
                    curr -> data.grade);
	    
            /* move to the next node */
            curr = curr -> next;
        }
	
	/************** Step 6 *******************/            
	aveGrade = (double)headStructurePtr->classSum / headStructurePtr->numNodes;

	printf("Number of students in the class: %d", headStructurePtr->numNodes);
	printf("\nAverage grade : %.2f%%\n", aveGrade);
    }   
}






/**********************************************************
 * Filename:  linkedlist.h                                *
 * Author:    Sue Bogar                                   *
 * Written:   11/17/98                                    *
 * Section:   201staff                                    *
 * SSN:       123-45-6789                                 *
 * Email:     bogar@cs.umbc.edu                           *
 *                                                        *
 * This file contains the structure definition of a node, *
 * and typedefs of the types NODE and NODEPTR.  It also   *
 * contains the function prototypes for the functions     *
 * defined in linkedlist.c, which are the linked list     *
 * implementation of a list abstract data type.           *
 **********************************************************/


/************ Step 1 ***************/
#ifndef _LINKEDLIST_H
#define _LINKEDLIST_H


#define NAMESIZE 20

/* NODEPTR is defined as an alias for
 * "struct node *" so it can be used
 * within the structure before it is
 * completely defined. */
typedef struct node * NODEPTR;



/* The STUDENT node will store info *
 * about one student in a class.    */
typedef struct student
{
   char  firstName[NAMESIZE];
   char  lastName[NAMESIZE];
   int   grade;        
   int   id;
} STUDENT;


/* A NODE is a structure that allows us to build a linked
** list since it has both a data portion and a NODEPTR as
** its members.  The NODEPTR called next is known as the
** link.
*/
typedef struct node
{
    STUDENT data;
    NODEPTR next;    /* OR struct node *next; */
}NODE;


/* The HEADSTRUCTURE is meant to encapsulate extra information
 * about the linked list.  */
typedef struct headStructure
{
   int     numNodes;   /* number of nodes in the list */
   int     classSum;   /* sum of grades in the entire list. */
   NODEPTR head;       /* will point to the start of list. */
} HEADSTRUCTURE;


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
NODEPTR CreateNode (void);


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
void SetData (NODEPTR temp, STUDENT newStudent);


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
void Insert (HEADSTRUCTURE* headStructure, NODEPTR temp);


/*************************************************
* Function: Delete                               *
* Input:    a pointer to a pointer to the        *
*            address of the headStructure        *
*            (HEADSTRUCTURE*); social security # *
*            of student to be deleted (int)      *
* Output: 1.if found, the first node with        *
*            the corresponding value             *
*            (target) is deleted from the        *
*            linked list                         *
*         2.if the node is deleted, the          *
*            value found in the deleted          *
*            node (target) is returned and       *
*            the headStructure's number of nodes *
*            is decremented, and its class       *
*            sum is also adjusted.               *
*         3.if no node is deleted, -1 is         *
*            returned                            *
*************************************************/ 
int Delete (HEADSTRUCTURE* headStructurePtr, int target);


/********************************************
* Function: IsEmpty                         *
* Input:    a pointer to the head of the    *
*            linked list                    *
* Output:   returns 1 (true) if the list is *
*            empty; returns 0 (false) if    *
*            the list is not empty          *
*********************************************/
int IsEmpty (NODEPTR head);


/*************************************************
* Function: PrintList                            *
* Input:    a pointer to the headStructure of the*
*            list                                *
* Ouput:    each node in the list is             *
*            printed according to the            *
*            format specified                    *
*************************************************/ 
void PrintList (HEADSTRUCTURE * headStructurePtr);



/** !!! Don't forget to finish guarding this header file!!! **/
#endif

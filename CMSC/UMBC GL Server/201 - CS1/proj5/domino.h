/******************************************************************************
 ** File:          domino.h
 ** Author:        Christopher Mai
 ** Date:          12/2/07
 ** Section:       0203
 ** E-mail:        chrmai1@umbc.edu
 ** 
 **  This file is the .h file for project 5 as such contains #includes, 
 ** #defines, function prototypes and structures to be used by the program.
 *****************************************************************************/

#include <stdio.h>
#include <stdlib.h>

#define TRUE         1
#define TRUEREVERSE  9
#define FALSE        0
#define MODIFIER    48
#define MAX          7
#define MIN          1
#define PRINTDOMINO  1
#define INSERTLEFT   2
#define INSERTRIGHT  3
#define DRAWDOMINO   4 
#define PASSTURN     5
#define PRINTMENU    6
#define QUIT         7
#define LEFT        19
#define RIGHT       20
#define USER        11
#define SYSTEM      12
#define OVER         0
#define CONTINUE     3
#define WIN          2
#define LOSE         1

typedef struct node * NODEPTR;

/* domino */
typedef struct domino
{
      int left;   /* the value of the left half of the domino */
      int right;  /* the value of the right half of the domino */
}DOMINO;

/* a node in the linked list */
typedef struct node
{
      DOMINO data;       /* the data                             */
      struct node *next; /* pointer to the next node in the list */
}NODE;

/* the linked list */
typedef struct linkedList
{
   int nrNodes;   /* how many nodes are in the linked list */
   NODE *head;    /* points to the first node or NULL      */
   NODE *tail;    /* points to the last node or NULL       */
}LIST;

/*****************************************************************************
 ** PrintGreeting --
 **  Prints out a message to greet the user
 **   Input:  void
 **   Output: void
 *****************************************************************************/

void PrintGreeting();

/******************************************************************************
 ** PrintInstructions --
 **  Prints out the instructions on how to use this program
 **   Input:  void
 **   Output: void
 *****************************************************************************/

void PrintInstructions();

/*****************************************************************************
 ** CreateBoneyard --
 **  Scans domino from opened file and stores them in a linked list
 **   Input:  ifp pointer and structure
 **   Output: number of dominoes
 ****************************************************************************/

int CreateBoneyard(LIST* bone, FILE *ifp);

/*****************************************************************************
 ** DealUser --
 **  Creates linked lists for user and for system to use in play
 **   Input:  head of boneyard, user, and num dominoes
 **   Output: void
 *****************************************************************************/

void DealUser(LIST* bone, LIST* user, int handSize);

/*****************************************************************************
 ** DealSystem --
 **  Creates a linked list for the system to play
 **   Input:  head of boneyard, head of system and hand size
 **   Output: void
 *****************************************************************************/

void DealSystem(LIST* bone,LIST* system, int handSize);

/*****************************************************************************
 ** PrintMenu --
 **  Prints out the menu for user interface
 **   Input:  void
 **   Output: void
 ****************************************************************************/

void PrintMenu();

/*****************************************************************************
 ** GetValidInt --
 **   Ensures valid int for menu navigation
 **    Input:  void
 **    Output: a valid int
 *****************************************************************************/

int GetValidInt();

/******************************************************************************
 ** CreateNode --
 **  Creates a Node and initializes the members and exits if there is 
 **  insufficient memory
 **   Input:  void
 **   Output: NODEPTR
 *****************************************************************************/

NODEPTR CreateNode();

/*****************************************************************************
 ** SetData --
 **  sets the data into the linked list node
 **   Input:  a pointer to the node and the data
 **   Output: void
 *****************************************************************************/

void SetData(NODEPTR temp, int left, int right);

/*****************************************************************************
 ** SelectDominoLeft --
 **  Removes a domino from your linked list to be placed in the train
 **   Input:  train and list of either user or system
 **   Output: node that is selected
 *****************************************************************************/

NODEPTR SelectDominoLeft(LIST* train, LIST* list);

/*****************************************************************************
 ** SelectDominoRight --
 **  Removes a domino from your linked list to be placed in the train
 **   Input:  train and list of user/system
 **   Output: node that is selected
 *****************************************************************************/

NODEPTR SelectDominoRight(LIST* train, LIST* list);

/****************************************************************************
 ** CheckPlacement --
 **  Checks if the domino you choose will correctly attach to the train
 **   Input:  the train and your domino, and which side
 **   Output: true or false?
 **************************************************************************/

int CheckPlacement(LIST* train, DOMINO* domino, int side);

/*****************************************************************************
 ** InsertLeft --
 **  inserts a new node into the list 
 **   Input:  pointer to the head of the linked list and a pointer to the 
 **           node to be inserted
 **   Output: void
 *****************************************************************************/

void InsertLeft(LIST* list, NODEPTR temp);

/*****************************************************************************
 ** InsertRight --
 **  inserts a new node on the end of the list
 **   Input:  pointer to tail of list and pointer to node to be inserted
 **   Output: void
 ****************************************************************************/

void InsertRight(LIST *list, NODEPTR temp);
 
/****************************************************************************
 ** CheckDraw --
 **  Checks to see if player can play a domino before allowing to draw
 **  from a boneyard
 **   Input:  pointer to the head of a list, train and whats calling it
 **   Output: true or false
 ****************************************************************************/

int CheckDomino(LIST* train, LIST* list, int distinguish);

/****************************************************************************
 ** DrawDomino --
 **  Draws a domino from the boneyard
 **   Input:  pointer to head of list, and the boneyard
 **   Output: void
 ***************************************************************************/

void DrawDomino(LIST* list, LIST* boneyard);

/******************************************************************************
 ** IsEmpty --
 **  Checks to see if the list is empty
 **   Input:  a pointer to the head of the linked list
 **   Output: T/F
 *****************************************************************************/

int IsEmpty(NODEPTR head);

/*****************************************************************************
 ** PrintList -- 
 **  prints out the linked list to display to the user
 **   Input:  a pointer to the head of the list 
 **   Output: void
 *****************************************************************************/

void PrintList(NODEPTR head);

/*****************************************************************************
 ** DestroyList --
 **  Destroy the linked list at end of use
 **   Input:  list struct
 **   Output: void
 *****************************************************************************/

void DestroyList(LIST* list);

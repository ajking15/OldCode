/******************************************************************************
 ** File:          domino.c
 ** Author:        Christopher Mai
 ** Date:          12/2/07
 ** Section:       0203
 ** E-mail:        chrmai1@umbc.edu
 ** 
 **  This file is the .c file for project 5 and so includes all function 
 ** definitions.
 *****************************************************************************/

#include "domino.h"

/*****************************************************************************
 ** PrintGreeting --
 **  Prints out a message to greet the user
 **   Input:  void
 **   Output: void
 *****************************************************************************/

void PrintGreeting()
{
   /* print greeting to user */
   printf("\nGood day user. This is a game program; please enjoy yourself\n");
}

/******************************************************************************
 ** PrintInstructions --
 **  Prints out the instructions on how to use this program
 **   Input:  void
 **   Output: void
 *****************************************************************************/

void PrintInstructions()
{
   /* print instructions on how to use the game */
   printf("\n1 - Read menu to choose the option that you want\n");
   printf("2 - When entering domino input as you see the domino\n");
   printf("3 - For entering domino do not reverse the number\n");
   printf("    when entering program will do it for you\n");
   printf("4 - If neither player can place a domino when the\n");
   printf("    boneyard is empty, up to the user to quit the program\n");
   printf("5 - If you can play a domino you must play it\n");
   printf("6 - You cannot draw a domino if you can play one\n");
   printf("7 - You cannot pass your turn if you can play a domino\n");
   printf("    or if the boneyard isnt empty\n\n");
}

/*****************************************************************************
 ** CreateBoneyard --
 **  Scans domino from opened file and stores them in a linked list
 **   Input:  ifp pointer and structure
 **   Output: number of dominoes 
 ****************************************************************************/

int CreateBoneyard(LIST* bone, FILE *ifp)
{
   /* scan value and call setData, then call insert, then thats all i think */
   NODEPTR temp;
   char cLeft, cRight, space;
   int left, right, numDom = 0;
   
   /* scans until file ends */
   while((cLeft = fgetc(ifp)) != EOF)
   {
      space = fgetc(ifp);
      cRight = fgetc(ifp);
      space = fgetc(ifp);

      /* converts from ascii to int */
      cLeft -= MODIFIER;
      cRight -= MODIFIER;

      left = (int)cLeft;
      right = (int)cRight;
      
      /* creates node for the data */
      temp = CreateNode(); 
  
      /* inserts scanned data into the node */
      SetData(temp, left, right);
      
      /* inserts node into boneyard */
      InsertRight(bone, temp);
      
      /* counts the number of dominoes */
      numDom++;
   }

   /* close the file */
   fclose(ifp);

   return numDom;

}

/*****************************************************************************
 ** DealUser --
 **  Creates linked lists for user and for system to use in play
 **   Input:  head of boneyard, user, and num dominoes
 **   Output: void
 *****************************************************************************/

void DealUser(LIST* bone, LIST* user, int handSize)
{
   NODEPTR curr, prev;
   int i;

   /* deals user until handsize has been reached */
   for(i = 0; i < handSize; i++)
   {
      curr = bone->head;
      prev = curr;
      InsertRight(user, curr);
      prev = prev -> next;
      bone->head = prev;
      curr->next = NULL;
   }

}

/*****************************************************************************
 ** DealSystem --
 **  Creates a linked list for the system to play
 **   Input:  head of boneyard, head of system and hand size
 **   Output: void
 *****************************************************************************/

void DealSystem(LIST* bone, LIST* system, int handSize)
{
   NODEPTR curr, prev;
   int i;

   /* deals system until handsize has been reached */
   for(i = 0; i < handSize; i++)
   {
      curr = bone->head;
      prev = curr;
      InsertRight(system, curr);
      prev = prev -> next;
      bone->head = prev;
      curr->next = NULL;
   }

}

/*****************************************************************************
 ** PrintMenu --
 **  Prints out the menu for user interface
 **   Input:  void
 **   Output: void
 ****************************************************************************/

void PrintMenu()
{
   printf("Domino Menu\n");
   printf("1. Print your dominoes\n");
   printf("2. Add a domino to the head of the train\n");
   printf("3. Add a domino to the tail of the train\n");
   printf("4. Pick a domino from the bone yard\n");
   printf("5. Pass your turn\n");
   printf("6. Print this menu\n");
   printf("7. Quit\n\n");
}

/*****************************************************************************
 ** GetValidInt --
 **   Ensures valid int for menu navigation
 **    Input:  void
 **    Output: a valid int
 *****************************************************************************/

int GetValidInt()
{
   /* ensures loop entry */
   int input = MAX +1;

   while(input < MIN || input > MAX)
   {
      printf("Please enter a menu choice: ");
      scanf("%d", &input); 
   }

   return input;

}

/******************************************************************************
 ** CreateNode --
 **  Creates a Node and initializes the members and exits if there is 
 **  insufficient memory
 **   Input:  void
 **   Output: NODEPTR
 *****************************************************************************/

NODEPTR CreateNode()
{
   NODEPTR newNode;

   /* Get the space needed for the node */
   newNode = (NODEPTR) malloc(sizeof(NODEPTR));

   /* checks if memory was successfully allocated */
   if(newNode == NULL)
   {
      fprintf(stderr, "Out of Memory - CreateNode\n");

      exit(-5);
   }

   /* Initialize the members */
   newNode->data.left =  0;
   newNode->data.right = 0;
   newNode->next = NULL;

   return newNode;
}

/*****************************************************************************
 ** SetData --
 **  sets the data into the linked list node
 **   Input:  a pointer to the node and the data
 **   Output: void
 *****************************************************************************/

void SetData(NODEPTR temp, int left, int right)
{
   /* inserts data into node */
   temp->data.left = left;
   temp->data.right = right;
}

/*****************************************************************************
 ** SelectDominoLeft --
 **  Removes a domino from your linked list to be placed in the train
 **   Input:  train and list of either user or system
 **   Output: node that is selected
 *****************************************************************************/

NODEPTR SelectDominoLeft(LIST* train, LIST* list)
{
   DOMINO data;
   NODEPTR prev, curr, temp;
   int left, right, status, tLeft, tRight;

   /* scans in domino values from the user */
   printf("Enter domino values: ");
   scanf("%d %d", &left, &right);
   
   /* sets data into a domino */
   data.left = left;
   data.right = right;
   
   /* checks if enterd data matches head of train */
   if(left == list->head->data.left && right ==
      list->head->data.right)
   {
      /* checks domino placement to the train */
      status = CheckPlacement(train, &data, LEFT);
      
      if(status == FALSE)
      {
	 printf("Domino [%d|%d] does not match the train\n", left, right);
	 return NULL;
      }

      temp = list->head;
      list->head = list->head->next;
     
      /* checks if status returned domino must be reversed */
      if(status == TRUEREVERSE)
      {
	 tLeft = temp->data.left;
         tRight = temp->data.right;

         temp->data.left = tRight;
         temp->data.right = tLeft;
      }

      temp->next = NULL;

      return temp;
   } else {
      /* checks status of entered domino compared to train */
      status = CheckPlacement(train, &data, LEFT);
      if(status == FALSE)
      {
         printf("Domino [%d|%d] does not match the train\n", left, right);
         return NULL;
      }

      prev = list->head;
      curr = list->head->next;
      
      /* traverses the list until the domino is found */
      while(curr != NULL && curr->data.left != left && 
	    curr->data.right != right)
      {
	 prev = curr;
	 curr = curr->next;
      }

      /* checks if domino couldnt be found in list */
      if(curr != NULL)
      {
	 temp = curr;
	 prev->next = curr->next;

	 if(status == TRUEREVERSE)
	 {
	    tLeft = temp->data.left;
	    tRight = temp->data.right;

	    temp->data.left = tRight;
	    temp->data.right = tLeft;
	 }

	 temp->next = NULL;

	 return temp;
      } else {
	 printf("You do not have the domino [%d|%d]\n",
		temp->data.left, temp->data.right);

	 return NULL;
      }

   }
  
   printf("Domino %d %d does not match train\n", left, right);
   return NULL;

}

/*****************************************************************************
 ** SelectDominoRight --
 **  Removes a domino from your linked list to be placed in the train
 **   Input:  train and list of user/system
 **   Output: node that is selected
 *****************************************************************************/

NODEPTR SelectDominoRight(LIST* train, LIST* list)
{
   DOMINO data;
   NODEPTR prev, curr, temp;
   int left, right, status, tLeft, tRight;

   /* scans in domino values from user */
   printf("Enter domino values: ");
   scanf("%d %d", &left, &right);

   data.left = left;
   data.right = right;

   curr = list->head;
   
   /* checks my list at the head of the code */
   if(left == curr->data.left && right == curr->data.right)
   {
      status = CheckPlacement(train, &data, RIGHT);
      if(status == FALSE)
      {
	 printf("Domino [%d|%d] does not match the train\n", left, right);
	 return NULL;
      }
      
      temp = list->head; /* temp equals the first node */
      temp->next = NULL; /* breaks node connection to list */
      list->head = list->head->next; /* the first node is moved past */
      
      /* checks if the data needs to be reversed */
      if(status == TRUEREVERSE)
      {
	 tLeft = temp->data.left;
	 tRight = temp->data.right;
	 
	 temp->data.left = tRight;
	 temp->data.right = tLeft;
      } 
      
      return temp;
   } else {
      /* checks if selected domino will match train */
      status = CheckPlacement(train, &data, RIGHT); 
      
      /* domino doesn't match the train */
      if(status == FALSE)
      {
	 printf("Domino [%d|%d] does not match the train\n", left, right);
	 return NULL;
      }
      /* prev equals first node in the list*/
      prev = list->head;
      /* curr equals second node */
      curr = list->head->next;
      
      while(curr != NULL && curr->data.left != left &&
            curr->data.right != right)
      {
	 /* advances until input nums equals a node in the list */
         prev = curr;
         curr = curr->next;
      }
      
      /* checks just in case the node wasn't in the list */
      if(curr != NULL)
      {
	 /* temp is the node being looked for */
         temp = curr;
	 /* bridges the gap that temp will make */
         prev->next = curr->next; /*prev->next?*/
	 
	 /* temp no longer points to the list */
	 temp->next = NULL;
	 
	 /* check status of node */
	 
	 /* checks status */
	 if(status == FALSE)
	 {
	    printf("Domino [%d|%d] does not match the train\n", left, right);
	    
	    return NULL;
	 }
	 
	 if(status == TRUEREVERSE)
	 {
	    tLeft = temp->data.left;
	    tRight = temp->data.right;

	    temp->data.left = tRight;
	    temp->data.right = tLeft;
	 }

         return temp;
	 
      } else {
         printf("You do not have the domino [%d|%d]\n",
                temp->data.left, temp->data.right);

         return NULL;
      }

   }

   printf("Domino [%d|%d] does not match your list\n", left, right);
   
   return NULL;

}


/****************************************************************************
 ** CheckPlacement --
 **  Checks if the domino you choose will correctly attach to the train
 **   Input:  the train and your domino, and which side
 **   Output: true or false?
 **************************************************************************/

int CheckPlacement(LIST* train, DOMINO* domino, int side)
{
   /* checks if called bye SelectDominoLeft */
   if(side == LEFT)
   {
      /* checks if domino is fine the way it is */
      if(domino->right == train->head->data.left)
      {
	 return TRUE;
      }
      
      /* checks if the domino needs to be flipped */
      if(domino->left == train->head->data.left)
      { 
	 printf("trueReverse\n");

	 return TRUEREVERSE;
      }
      
      /* domino does not match train */
      return FALSE;
      
   }

   /* checks if function was called by SelectDominoRight */
   if(side == RIGHT)
   {
      /* domino matches fine */
      if(domino->left == train->tail->data.right)
      {
         return TRUE;
      } 

      /* domino needs to be reversed */
      if(domino->right == train->tail->data.right)
      {
         return TRUEREVERSE;
      } 

      /* do not have the domino */
      return FALSE;

   }

   return FALSE;

}

/*****************************************************************************
 ** InsertLeft --
 **  inserts a new node into the list 
 **   Input:  pointer to the head of the linked list and a pointer to the 
 **           node to be inserted
 **   Output: void
 *****************************************************************************/

void InsertLeft(LIST* list, NODEPTR temp)
{
   NODEPTR curr;

   if(IsEmpty(list->head))
   {
      list->head = temp; /* head points at node */
      temp->next = NULL; /* node points at NULL */
      list->tail = temp; /* tail points at node */
      list->nrNodes++;   /* number of nodes goes up */
   } else {
      curr = list->head; /* curr = first node */
      list->head = temp; /* head points at new node */
      temp->next = curr; /* new node points at old node */
      list->nrNodes++;   /* number of nodes goes up */
   }
}

/*****************************************************************************
 ** InsertRight --
 **  inserts a new node on the end of the list
 **   Input:  pointer to tail of list and pointer to node to be inserted
 **   Output: void
 ****************************************************************************/

void InsertRight(LIST* list, NODEPTR temp)
{
   if (IsEmpty (list->head)) 
   {
      list->head = temp;
      list->tail = temp; /* tail points at new node */
      list->nrNodes++;   /* number of nodes goes up */
   } else { 
      list->tail->next = temp;
      list->tail = temp;
      list->nrNodes++;   /* number of nodes goes up */
   }
   
}
 
/****************************************************************************
 ** CheckDomino --
 **  Checks to see if player can play a domino before allowing to draw
 **  from a boneyard
 **   Input:  train and linked list and whats calling the function
 **   Output: true or false
 ****************************************************************************/

int CheckDomino(LIST* train, LIST* list, int distinguish)
{
   NODEPTR prev, curr;
   int tLeft, tRight;

   curr = list->head;
   prev = curr;

   /* continues while list exists */
   while(curr != NULL)
   {
      /* checks if domino can be attached to head of train train */
      if(curr->data.left == train->head->data.left ||
	 curr->data.right == train->head->data.left)
      {
	 /* checks if its the computers turn */
	 if(distinguish == SYSTEM)
	 {
	    /* reverses domino if it has to be to be played on left */
	    if(curr->data.left == train->head->data.left)
	    {
	       tLeft = curr->data.left;
	       tRight = curr->data.right;
	       
	       curr->data.left = tRight;
	       curr->data.right = tLeft;
	    }
	    
	    /* inserts domino on to head of train */
	    curr->next = NULL;
	    
	    InsertLeft(train, curr);
	    printf("System played [%d|%d]\n",
		   curr->data.left, curr->data.right);
	    
	 }
	 
	 return FALSE; /* means they do have a domino to match the train */
      }
      
      if(curr->data.right == train->tail->data.right ||
	 curr->data.left == train->tail->data.right)
      {
	 if(distinguish == SYSTEM)
	 {
	    if(curr->data.right == train->tail->data.right)
	    {
	       tLeft = curr->data.left;
	       tRight = curr->data.right;
	       
	       curr->data.left = tRight;
	       curr->data.right = tLeft;
	    }
	    
	    curr->next = NULL;
	    InsertRight(train, curr);
	    printf("System played  [%d|%d]\n", 
		   curr->data.right, curr->data.left);
	 }
	 
	 return FALSE;
      }
      
      curr = curr->next;
   }
   
   return TRUE;
   
}

/****************************************************************************
 ** DrawDomino --
 **  Draws a domino from the boneyard
 **   Input:  pointer to head of list, and boneyard
 **   Output: void
 ***************************************************************************/

void DrawDomino(LIST* list, LIST* boneyard)
{
   NODEPTR curr;

   curr = boneyard->head;
   boneyard->head = boneyard->head->next;
   curr->next = NULL; 
   InsertLeft(list, curr);
}

/******************************************************************************
 ** IsEmpty --
 **  Checks to see if the list is empty
 **   Input:  a pointer to the head of the linked list
 **   Output: 1 or 0 depending on list status
 *****************************************************************************/

int IsEmpty(NODEPTR head)
{
   /* if pointer to the list is NULL then return false. 
      if the list is empty return true. */

   if(head == NULL)
   {
      return TRUE;
   } else {
      return FALSE;
   }
}

/*****************************************************************************
 ** PrintList -- 
 **  prints out the linked list to display to the user
 **   Input:  a pointer to the head of the list 
 **   Output: void
 *****************************************************************************/

void PrintList(NODEPTR head)
{
   NODEPTR curr;
   int i = 0, j = 0;
   
   if(IsEmpty(head))
   {
      printf("The list is empty\n");
   } else {
      /* set the current pointer to the first node of the list */
      curr = head;
      
      /* until the end of the list */
      while(curr != NULL)
      {
	 /* print the current data item */
	 printf("[%d|%d]", curr->data.left, curr->data.right);
	 
	 /* move to the next node */
	 curr = curr->next;
	 i++;
	 if(j == 0)
	 {
	    if(i == 4)
	    {
	       printf("\n");
	       j++;
	       i = 0;
	    }
	 } else {
	    if(i % 5 == 0)
	    {
	       printf("\n");
	    }
	 }
	 
      } 
   }
   
}

/*****************************************************************************
 ** DestroyList --
 **  Destroy the linked list at end of use
 **   Input:  list
 **   Output: void
 *****************************************************************************/

void DestroyList(LIST* list)
{
   NODEPTR prev, curr;
   
   curr = list->head;
   
   /* traveres list if it exists */
   while(curr != NULL)
   {
      prev = curr;
      curr = curr->next;
      
      /* cuts off node from list */
      prev->next = NULL;
      
      /* frees memory from my control */
      free(prev);
   }

}

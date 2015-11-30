/*****************************************************************************
 ** File:            proj5.c
 ** Author:          Christopher Mai
 ** Date:            12/2/07
 ** Section:         0203
 ** E-mail:          chrmai1@umbc.edu
 ** 
 **  This is the main file for program 5 and requires both domino.c 
 ** and domino.h to run properly.
 *****************************************************************************/

#include "domino.h"

int main(int argc, char *argv[])
{
   NODEPTR headBone = NULL, tailBone = NULL;
   NODEPTR headUser = NULL, tailUser = NULL;
   NODEPTR headSystem = NULL, tailSystem = NULL;
   NODEPTR headTrain = NULL, tailTrain = NULL; 
   NODEPTR curr = NULL, choice = NULL;
   LIST *boneyard, *user, *system, *train;
   FILE *ifp;
   int numDom, menu, turn = CONTINUE, status;
   int player = CONTINUE;
   
   /* check for correct number of arguments */
   if(argc != 3)
   {
      /* print message to standard error */
      fprintf(stderr, "incorrect number of arguments.\n");
      fprintf(stderr, "enter a.out, name of file, and number of starting\n");
      fprintf(stderr, "dominoes.\n");

      exit(-1);
   }

   int handSize = atoi(argv[2]);
   char *fileName = argv[1];
   
   ifp = fopen(fileName, "r");

   /* check if the file opened correctly */
   if(ifp  == NULL)
   {
      fprintf(stderr, "%s is incorrect\n", fileName);

      exit (-2);
   }

   /* makes sure num dominoes is positive */
   if(atoi(argv[2]) <= 0)
   {
      /* print message to standard error */
      fprintf(stderr, "number of dominoes must be positive\n");

      exit(-3);
   }

   /* gather necessary memory for the LIST structs */
   boneyard = (LIST *) malloc(sizeof(LIST));

   if(boneyard == NULL)
   {
      fprintf(stderr, "Out of Memory - boneyard\n");

      exit(-6);
   }

   user = (LIST *) malloc(sizeof(LIST));

   if(user == NULL)
   {
      fprintf(stderr, "Out of Memory - user\n");

      exit(-7);
   }

   system   = (LIST *) malloc(sizeof(LIST));

   if(system == NULL)
   {
      fprintf(stderr, "Out of Memory - system\n");
   
      exit(-8);
   }

   train    = (LIST *) malloc(sizeof(LIST));

   if(train == NULL)
   {
      fprintf(stderr, "Out of Memory - train\n");
      
      exit(-9);
   }

   /* point the head and tail to the list struct */
   boneyard->head = headBone;
   boneyard->tail = tailBone;
   boneyard->nrNodes = 0; 
   
   user->head = headUser;
   user->tail = tailUser;
   headUser = user->head;
   tailUser = user->head;
   user->nrNodes = 0;

   system->head = headSystem;
   system->tail = tailSystem;
   system->nrNodes = 0;

   train->head = headTrain;
   train->tail = tailTrain;
   train->nrNodes = 0;
      
   /* Creates the boneyard which holds all dominoes in the beginning */
   numDom = CreateBoneyard(boneyard, ifp);
  
   /* calculates max hand size for game of dominoes */
   if(handSize >= (numDom - 1) / 2)
   {
      fprintf(stderr, "%d is too large for number of dominoes\n", handSize);
      fprintf(stderr, "largest number of dominoes is %d\n", (numDom - 1) / 2);

      exit(-4);
   }

   /* prints greeting to the user */
   PrintGreeting();
   
   /* prints program instructions */
   PrintInstructions();
   
   /* gives the user a linked list of dominoes */
   DealUser(boneyard, user, handSize);
   
   /* gives the system a linked list of dominoes */
   DealSystem(boneyard, system, handSize);
   /* takes the first node from the boneyard to be used as the train */
   curr = boneyard->head;
   boneyard->head = boneyard->head->next;
   InsertLeft(train, curr);
  
   /* prints the menu for user interface */
   PrintMenu();

 
   /* Game Loop */  
   while(player == CONTINUE)
   {
      /* player turn loop */
      while(turn != OVER)
      {
	 printf("The train (%d): ", train->nrNodes);
	 PrintList(train->head);
	 printf("\n\n");
	 
	 printf("Your turn...(enter six for menu)\n");	 
	 menu = GetValidInt(); /* int used for menu nav */
	 
	 switch(menu)
	 {
	    case PRINTDOMINO:
	       printf("Your Dominoes: ");
	       PrintList(user->head);
	       printf("\n");
	       turn = CONTINUE; /* print domino doesnt use up your turn */
	       break;
	    case INSERTLEFT:
	       /* selects a domino to play */
	       choice = SelectDominoLeft(train, user);
	       if(choice != NULL)
	       {
		  InsertLeft(train, choice);
		  turn = OVER;
	       } else {
		  turn = CONTINUE; /* if can't play, turn isnt lost */
	       }
	       break;
	    case INSERTRIGHT:
	       /* selects a domino to play */
	       choice = SelectDominoRight(train, user);
	       if(choice != NULL)
	       {
		  choice->next = NULL;
		  InsertRight(train, choice);
		 
		  turn = OVER;
	       } else {
		  turn = CONTINUE;
	       }
	       break;
	    case DRAWDOMINO:
	       /* checks if boneyard is empty */
	       if(IsEmpty(boneyard->head) == FALSE)
	       {
		  status = CheckDomino(train, user, USER);
		  if(status == TRUE)
		  {
		     DrawDomino(user, boneyard);
		     turn = OVER;
		  } else {
		     printf("\nYou had a domino to play\n\n");
		     turn = CONTINUE;
		  }
		  
	       } else {
		  printf("Boneyard is empty\n");
	       }
	       break;
	    case PASSTURN:
	       /* checks if you can play a domino and for empty boneyard */
	       if(CheckDomino(train, user, USER) == TRUE && 
		  IsEmpty(boneyard->head) == TRUE)
	       {
		  printf("\nuser passes his turn\n");
		  turn = OVER;
	       } else 
		  if(IsEmpty(boneyard->head) == FALSE)
		  {
		     printf("\nCant pass, you can draw from the boneyard\n\n");
		     turn = CONTINUE;
		  } else {
		     printf("Can't pass you still have a domino to play");
		  }
	       break;
	    case PRINTMENU:
	       PrintMenu();
	       turn = CONTINUE; 
	       break;
	    case QUIT:
	       turn = OVER;
	       player = QUIT;
	       break;
	    default:
	       printf("Dios Mios!!, esta numero no existan!!!\n");
	       break;
	 }
	 /* checks if player ran out of dominoes */
	 if(IsEmpty(user->head) == TRUE)
	 {
	    player = WIN;
	 } 
      }
      
      turn = CONTINUE;
      
      printf("The Train: ");
      PrintList(train->head);
      printf("\n");

      /* computers turn */
      if(player == CONTINUE)
      {
	 printf("System's Hand: ");
	 PrintList(system->head);
	 printf("\n");
	 printf("\nSystem's turn...");

	 /* tries to play a domino for the system */
	 if(CheckDomino(train, system, SYSTEM) == FALSE)
	 {
	    system->nrNodes--;
	    printf("System has %d dominoes left\n\n", system->nrNodes);
	 } else 
	    if(IsEmpty(boneyard->head) == FALSE) /* tries to draw domino */
	    {
	       DrawDomino(system, boneyard);
	       printf("System drew a domino from the boneyard\n");
	       printf("System has %d dominoes left\n\n", system->nrNodes);
	    } else {
	       /* last passes if can do neither of the above */
	       printf("System has passed this turn\n");
	       printf("System has %d dominoes left\n\n", system->nrNodes);
	    }
	 
	 /* checks for empty hand */
	 if(IsEmpty(system->head) == TRUE)
	 {
	    player = LOSE;
	 }
	 
      }
   }

   if(player == WIN)
   {
      printf("Player Wins!\n");
      /* destroys all linked lists */
      DestroyList(user);
      DestroyList(boneyard);
      DestroyList(system);
      DestroyList(train);
   } else 
      if(player == LOSE)
      {
	 /* destroys all linked lists */
	 printf("System Wins! You Lose!");
	 DestroyList(user);
	 DestroyList(boneyard);
	 DestroyList(system);
	 DestroyList(train);
      } else {
	 /* destroys all linked lists */
	 printf("Hope you had a good game, Play again soon!\n");
	 DestroyList(user);
         DestroyList(boneyard);
         DestroyList(system);
         DestroyList(train);
      }

   return 0;
}

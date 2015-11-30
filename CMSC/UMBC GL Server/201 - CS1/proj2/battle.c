/****************************************************************************
** File:        battle.c
** Author:      Christopher Mai
** Date:        10/08/07
** Section:     0203
** E-mail:      chrmai1@umbc.edu
**
**  This is the .c file for project 2. So this document contains
** all function definitions whose prototypes are in battle.h
*****************************************************************************/

#include <stdio.h>
#include "battle.h"

/****************************************************************************
** PrintGreeting --
**  Prints the greeting for this program
**   Input:  void
**   Output: void
*****************************************************************************/

void PrintGreeting()
{
   printf("\nHello, this is a game called Naval Battle\n");
   printf("You will only have 40 turns to sink all five\n");
   printf("enemy ships. Instructions to follow\n\n");
}

/****************************************************************************
** PrintInstructions --
**  Prints instructions for the game naval battle
**   Input:  void
**   Output: void
*****************************************************************************/

void PrintInstructions()
{
   printf("1 - You will choose a horizontal and a vertical coordinate\n");
   printf("    for your missle strike.\n\n");
   printf("2 - You can only strike a particular point one time.\n");
   printf("    it will not be penalized if you try\n\n");
   printf("3 - If you hit a ship it will be marked with a X\n");
   printf("    and if you miss a O.\n\n");
   printf("4 - The Game will end when you have sunk all five ships\n");
   printf("    hidden on the board there is the Aircraft Carrier,\n");
   printf("    Battleship, Cruiser, Destroyer, and Submarine.\n\n");
   printf("5 - Happy Hunting!\n\n");
}

/****************************************************************************
** InitializeArray --
**  Initializes the array of chars to hold all spaces
**   Input:  void
**   Output: initialized array with ship placement
****************************************************************************/

void InitializeArray(char array[][10])
{
   int i, j;

   /* sets all points in array to spaces */
   for(i = MIN; i <= MAX; i++)
   {
      for(j = 0; j <= MAX; j++)
      {
	 array[i][j] = ' ';
      }
   }

}

/****************************************************************************
** PlaceSubmarine --
**  Places submarine into the master array
**   Input:  master array
**   Output: void
*****************************************************************************/

void PlaceSubmarine(char array[][10])
{
   int i;

   /* uses ship coordinates to place submarine into the master array */
   for(i = BOWROWSUB; i <= STERNROWSUB; i++)
   {
	 array[i][BOWCOLSUB] = 'S';
   }

}

/****************************************************************************
** PlaceAircraftCarrier --
**  Places aircraft carrier into the master array
**   Input:  master array
**   Output: void
*****************************************************************************/

void PlaceAircraftCarrier(char array[][10])
{
   int i;

   /* places carrier in the correct spot in the array */
   for(i = BOWROWCARRIER; i <= STERNROWCARRIER; i++)
   {
      array[i][BOWCOLCARRIER] = 'A';
   }
}

/****************************************************************************
** PlaceDestroyer --
**  Places destroyer into the master array
**   Input:  master array
**   Output: void
*****************************************************************************/

void PlaceDestroyer(char array[][10])
{
   int i;

   /* places destroyer in correct spot in the array */
   for(i = BOWCOLDESTROYER; i <= STERNCOLDESTROYER; i++)
   {
      array[BOWROWDESTROYER][i] = 'D';
   }
}

/****************************************************************************
** PlaceCruiser --
**  Places cruiser into the master array
**   Input:  master array
**   Output: void
*****************************************************************************/

void PlaceCruiser(char array[][10])
{
   int i;

   /* places cruiser in correct spots in the array */
   for(i = BOWCOLCRUISER; i <= STERNCOLCRUISER; i++)
   {
      array[BOWROWCRUISER][i] = 'C';
   }
}

/****************************************************************************
** PlaceBattleship --
**  Places battleship into the master array
**   Input:  master array
**   Output: void
*****************************************************************************/

void PlaceBattleship(char array[][10])
{
   int i;

   /* places battleship into appropriate spots in the array */
   for(i = BOWCOLBATTLE; i <= STERNCOLBATTLE; i++)
   {
      array[BOWROWBATTLE][i] = 'B';
   }
}

/***************************************************************************
** GetValidInt --
**  Gets an int within a desired range
**   Input:  min and max values for desired range
**   Output: a validated int
****************************************************************************/

int GetValidInt(int min, int max)
{
   /* set greater than max to ensure loop entry */
   int input = max + 1;

   /* ensures valid entry */
   while(input < min || input > max)
   {
      printf("Please enter an integer between");
      printf(" %d and %d : ", min, max);
      scanf("%d", &input);
   }

   return input;

}

/***************************************************************************
** GetValidAttack --
**  Controls turn counter and when while loop will terminate in main,
**  and decides hit/miss or already hit and place the corresponding mark
**  into both the master array and board array, wether or not a ship is sunk 
**   Input:  turn counter
**   Output: modified turn counter
****************************************************************************/

char GetValidAttack(char turnCounter, char master[][10], char board[][10])
{
   /* necessary ints declared and initialized */
   int row, col, valid, aircraft, battle, cruiser, destroyer, sub, temp;
   aircraft = 0;
   battle = 0;
   cruiser = 0;
   destroyer = 0;
   sub = 0;
   valid = 0;
   int total = 0;
   printf("Row number:\n");
   row = GetValidInt(MIN, MAX);
   printf("Column Number:\n");
   col = GetValidInt(MIN, MAX);
   
   /* insanely long make sure hit is valid and what to do if it is statement */
   if(master[row][col] != ' ')
   {
      /* switch takes care of any and all eventualities that can occur */
      switch(master[row][col])
      {
	 case AIRCRAFTCARRIER:
	    master[row][col] = 'X'; /* Stores hit to both arrays */
	    board[row][col] = 'X';
	    aircraft = CheckAircraft(board, aircraft);
	    valid++;
	    if(aircraft == LENGTHCARRIER) /* checks if ship is sunk */
	    {
	       /* Prints Board */
	       PrintBoard(board);
	       temp = turnCounter;
	       
	       printf("You sunk MY Aircraft Carrier!\n");
	       
	       total = CheckShips(board); /* checks number of ships sunk */
	       if(total == 5) /* if all ships sunk ends loop */
	       {
		  turnCounter = 0; /* ends game if all ships sunk */
	       } else {
		  while(temp <= turnCounter)
		  {
		     printf("you have %d turns remaining\n", turnCounter);
		     turnCounter = GetValidAttack(turnCounter, master,
						  board);
		  }
		  
	       }
	    }
	    break;
	 case BATTLESHIP:
	    master[row][col] = 'X'; /* Stores hit to both arrays */
	    board[row][col] = 'X';
	    battle = CheckBattleship(board, battle);
	    valid++;
	    if(battle == LENGTHBATTLE) /* checks if ship is sunk */
	    {
	       /* Prints Board */
	       PrintBoard(board);
	       temp = turnCounter;
	       
	       printf("You sunk MY Battleship!\n");
	       
	       total = CheckShips(board); /* checks number of ships sunk */
	       if(total == 5)
	       {
		  turnCounter = 0; /* ends game if all ships sunk */
	       } else {
		  
		  while(temp <= turnCounter)
		  {
		     printf("you have %d turns remaining\n", turnCounter);
		     turnCounter = GetValidAttack(turnCounter, master,
						  board);
		  }
	       }
	       
	    }
	    
	    break;
	 case CRUISER:
	    master[row][col] = 'X'; /* Stores hit to both arrays */
	    board[row][col] = 'X';
	    cruiser = CheckCruiser(board, cruiser);
	    valid++;
	    if(cruiser == LENGTHCRUISER)
	    {
	       /* Prints Board */
	       PrintBoard(board);
	       temp = turnCounter;
	       
	       printf("You sunk MY Cruiser!\n"); /* checks if ship is sunk */
	       
	       total = CheckShips(board); /* checks number of ships sunk */
	       if(total == 5)
	       {
		  turnCounter = 0; /* ends game if all ships sunk */
	       } else {
		  
		  while(temp <= turnCounter)
		  {
		     printf("you have %d turns remaining\n", turnCounter);
		     turnCounter = GetValidAttack(turnCounter, master,
						  board);
		  }
		  
	       }
	       
	    }
	    
	    break;
	 case DESTROYER:
	    master[row][col] = 'X'; /* Stores hit to both arrays */
	    board[row][col] = 'X';
	    destroyer = CheckDestroyer(board, destroyer);
	    valid++;
	    if(destroyer == LENGTHDESTROYER) /* checks if ship is sunk */
	    {
	       /* Prints Board */
	       PrintBoard(board);
	       temp = turnCounter;
	       
	       printf("You sunk MY Destroyer!\n");
	       
	       total = CheckShips(board); /* checks number of ships sunk */
	       if(total == 5)
	       {
		  turnCounter = 0; /* ends game if all ships sunk */
	       } else {
		  
		  while(temp <= turnCounter)
		  {
		     printf("you have %d turns remaining\n", 
			    turnCounter);
		     turnCounter = GetValidAttack(turnCounter, master,
						  board);
		  }
		  
	       }
	       
	    }
	    
	    break;
	 case SUBMARINE:
	    master[row][col] = 'X'; /* Stores hit to both arrays */
	    board[row][col] = 'X';
	    sub = CheckSubmarine(board, sub);
	    valid++;
	    if(sub == LENGTHSUB) /* checks if ship is sunk */
	    {
	       /* Prints Board */
	       PrintBoard(board);
	       temp = turnCounter;
	       
	       printf("You sunk MY Submarine!\n");
	       
	       total = CheckShips(board); /* checks number of ships sunk */
	       if(total == 5)
	       {
		  turnCounter = 0; /* ends loop in main if all ships sunk */
	       } else {
		  while(temp <= turnCounter)
		  {
		     printf("you have %d turns remaining\n", turnCounter);
		     turnCounter = GetValidAttack(turnCounter, master,
						  board);
		  }
		  
	       }
	       
	    }
	    
	    break;
	 case 'X':
	    printf("You have already hit this spot. Try Again.\n");
	    turnCounter++; /* avoid penalization */
	    break;
	 case 'O':
	    printf("You have already hit this spot. Try Again.\n");
	    turnCounter++; /* avoid penalization */ 
	    break;
	 default:
	    printf("Dios Mios, Yo no entiendo!");
	    break;
      }
      
   } else {
      board[row][col] = 'O'; /* Stores miss to both arrays */
      master[row][col] = 'O';
   } 
   
   return turnCounter - 1;

}

/***************************************************************************
 ** PlaceShips --
 **  Takes the array calls five other place ship functions to place into
 **  the master array
 **   Input:  array
 **   Output: void
 ***************************************************************************/

void PlaceShips(char array[][10])
{
   PlaceAircraftCarrier(array);
   PlaceCruiser(array);
   PlaceSubmarine(array);
   PlaceBattleship(array);
   PlaceDestroyer(array);
}

/***************************************************************************
 ** PrintBoard --
 **  Prints out the board for the player to see uses board array during game
 **  play and master array to print out everthing if you lose
 **   Input:  the array values to fill the board
 **   Output: void
 ***************************************************************************/

void PrintBoard(char array[][10])
{
   int row, col;
   
   printf("\n    0   1   2   3   4   5   6   7   8   9   \n");
   printf("  -----------------------------------------\n");

   for(row = MIN; row <= MAX; row++)
   {
      printf("%d |", row);

      for(col = MIN; col <= MAX; col++)
      {
	 printf(" %c |", array[row][col]);
      }

      printf("\n");
      printf("  -----------------------------------------\n");

   }
}

/***************************************************************************
 ** CheckShips --
 **  Calls five other check ship functions and if all five are hit signals
 **  games end
 **   Input:  board array
 **   Output: total number of hits
 ***************************************************************************/

int CheckShips(char array[][10])
{
   int a, b, c, d, s, total;

   /* initializes all five hit values for the ships to 0 */
   a = 0;
   b = 0;
   c = 0;
   d = 0;
   s = 0;
   total = 0;

   a = CheckAircraft(array, a); /* Checks number of hits on Carrier */
   if(a == LENGTHCARRIER)
   {
      total++;
   }

   b = CheckBattleship(array, b); /* Checks number of hits on Battleship */
   if(b == LENGTHBATTLE)
   {
      total++;
   }

   c = CheckCruiser(array, c); /* Checks number of hits on Cruiser */
   if(c == LENGTHCRUISER)
   {
      total++;
   }

   d = CheckDestroyer(array, d); /* Checks number of hits on destroyer */
   if(d == LENGTHDESTROYER)
   {
      total++;
   }

   s = CheckSubmarine(array, s); /* Checks number of hits on Submarine */
   if(s == LENGTHSUB)
   {
      total++;
   }

   return total;
} 

/***************************************************************************
 ** CheckAircraft --
 **  Checks for aircraft hit condition
 **   Input:  array and ship counter
 **   Output: number to help determine game end
 ***************************************************************************/

int CheckAircraft(char array[][10], int ship)
{
   int i;

   /* checks aircraft carrier hit condition */
   for(i  = BOWROWCARRIER; i <= STERNROWCARRIER; i++)
   {
      if(array[i][STERNCOLCARRIER] != ' ')
      {
	 ++ship;
      }
   }

   return ship;

}

/***************************************************************************
 ** CheckBattleship --
 **  Checks battleship hit condition
 **   Input:  board array and ship counter
 **   Output: number to help determine game end
 ***************************************************************************/

int CheckBattleship(char array[][10], int ship)
{
   int i;

   /* checks battleship for how many times it has been hit */
   for(i = BOWCOLBATTLE; i <= STERNCOLBATTLE; i++)
   {
      if(array[STERNROWBATTLE][i] != ' ')
      {
	 ++ship;
      }
   } 
   
   return ship;

}

/****************************************************************************
 ** CheckCruiser --
 **  Checks cruiser hit condition
 **   Input:  board array and ship counter
 **   Output: number to help determine game end
 ****************************************************************************/

int CheckCruiser(char array[][10], int ship)
{
   int i;

   /* checks number of times the cruiser has been hit */
   for(i = BOWCOLCRUISER; i <= STERNCOLCRUISER; i++)
   {
      if(array[STERNROWCRUISER][i] != ' ')
      {
	 ++ship;
      }
   }

   return ship;

}

/****************************************************************************
 ** CheckDestroyer --
 **  Checks destroyer hit condition
 **   Input:  board array and ship counter
 **   Output: number to help determine game end
 ****************************************************************************/

int CheckDestroyer(char array[][10], int ship)
{
   int i;
   /* checks destroyer for the number of times it has been hit */
   for(i = BOWCOLDESTROYER; i <= STERNCOLDESTROYER; i++)
   {
      if(array[STERNROWDESTROYER][i] != ' ')
      {
	 ++ship;
      }
   }

   return ship;

}

/****************************************************************************
 ** CheckSubmarine --
 **  Checks submarine hit condition
 **   Input:  board array and ship counter
 **   Output: number to help determine game end
 ***************************************************************************/

int CheckSubmarine(char array[][10], int ship)
{
   int i;

   /* checks board array for the number of times the sub has been hit */
   for(i = BOWROWSUB; i <= STERNROWSUB; i++)
   {
      if(array[i][STERNCOLSUB] != ' ')
      {
	 ++ship;
      }
   }
   
   return ship;

}

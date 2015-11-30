/******************************************************************************
 ** File:                      proj2.c
 ** Author:                    Christopher Mai
 ** Date:                      10/07/07
 ** Section:                   0203
 ** E-mail:                    chrmai1@umbc.edu
 ** 
 ** This is the main file for the program and is used in conjunction with
 ** files battle.h battle.c and util.c to run properly. When run this program
 ** Will simulate a naval battle with a computer opponent
 *****************************************************************************/

#include <stdio.h>
#include "battle.h"

int main()
{
   /* two arrays one holds ships and hits the other just **
   ** holds the hits and misses for the player to see    */
   char masterArray[10][10];
   char boardArray[10][10];

   /* variables used to control the games flow */
   int temp;
   char turnCounter = TURNS;
   int  total = 0;

   /* Prints Greeting */
   PrintGreeting();

   /* Prints Instructions for the game */
   PrintInstructions();
  
   /* initialize all spaces in the arrays to zero */
   InitializeArray(masterArray);
   InitializeArray(boardArray);

   /* Places all five ships into the masterArray */
   PlaceShips(masterArray);

   /* Game keeps going until turn counter hits zero */
   while(turnCounter > 0)
   {
      
   /* Prints Board */ 
   PrintBoard(boardArray);
   temp = turnCounter;

   /* makes sure that the turn has been decreased by GetValidAttack **
   ** so repeat hit is not penalized                                */
   while(temp <= turnCounter)
   {
      printf("you have %d turns remaining\n", turnCounter);
      /* GetValidAttack makes sure the attack is valid and also checks **
      ** conditions of the five individual ships via other functions   **
      ** to print out ship sunk and when all five are sunk sets        **
      ** turnCounter to zero to end the while loop                     */
      turnCounter = GetValidAttack(turnCounter, masterArray, boardArray);
   }

   }
   
   
   /* checks conditions of five ships at games end to see wether won or lost */
   total = CheckShips(boardArray);

   if(total < 5)
   {
      printf("You Lose, T_T\n");
      printf("See where the ships are");
      PrintBoard(masterArray);
   } else {
  
      printf("\nYOU WIN!!!\n");

   }

   return 0;

}

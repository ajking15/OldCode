/*****************************************
 * File:              lab11.c
 * Created by:        Katherine Gibson
 * Created on:        04/24/2007
 * Modified by:       Deepak Chinavle
 * Class:             CMSC201
 * Section:           0999
 * Email:             k38@umbc.edu
 *
 *  This file will demonstrate how to
 *  implement ADTs using struct typedefs,
 *  structs, pointers, and pointers to 
 *  structs, as well as afford practice in
 *  guarding header files.
 *
 *****************************************/

#include <stdio.h>
#include <stdlib.h>
#include "person.h"

int main ()
{
   int i, numPeople;
   PERSON *students;   /* pointer which we will later
			  allocate memory for */

   students = ReadInPeople(&numPeople);

   /* creates three teams of three people */
   CreateTeam(&students[0], &students[1], &students[2]);
   CreateTeam(&students[3], &students[4], &students[5]);
   CreateTeam(&students[6], &students[7], &students[8]);

   /*******************************/
   /* STEP 5A:                    */
   /* uncomment the call to the   */
   /* GiveExtraCredits function   */
   /* below                       */
   /*******************************/
  
   
   GiveExtraCredits(&students[3]);
   

   PrintPeople(students, numPeople);

   /* free the memory allocated to NAME */
   for (i = 0; i < numPeople; i++)
   {
      free(students[i].namePtr);
   }

   return 0;
}



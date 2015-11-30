/*****************************************
 * File:              lab10.c
 * Created by:        Katherine Gibson
 * Created on:        04/24/2007
 * Modified on:       11/06/07
 * Class:             CMSC201
 * Section:           TA
 * Email:             k38@umbc.edu
 *
 *  This file will demonstrate how to
 *  use struct typedefs, structs, 
 *  pointers, and pointers to structs.
 *
 *****************************************/

#include <stdio.h>
#include <stdlib.h>
#include "person.h"

int main ()
{
   int i;
   PERSON students [NUMPPL];   /* array of PERSONs of size 9 */

   /*******************************/
   /* STEP 5:                     */
   /* uncomment the call to the   */
   /* ReadInPeople function below */
   /*******************************/

   
   ReadInPeople(students);
   

   /* creates three teams of three people */
   CreateTeam(&students[0], &students[1], &students[2]);
   CreateTeam(&students[3], &students[4], &students[5]);
   CreateTeam(&students[6], &students[7], &students[8]);

   PrintPeople(students);

   /* free the memory allocated to NAME */
   for (i = 0; i < NUMPPL; i++)
   {
      free(students[i].namePtr);
   }

   return 0;
}


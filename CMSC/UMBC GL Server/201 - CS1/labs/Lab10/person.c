/*****************************************
 * File:              person.c
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
#include <string.h>
#include "person.h"

/****************************************
  ReadInPeople
  Reads in people from a file

  Input(s):  an array of type PERSON
  Output(s): none
****************************************/
void ReadInPeople(PERSON people[])
{
   int i;
   FILE *ifp;  /* file pointer */
   
   /****************************/
   /* STEP 1:                  */
   /* open the file people.dat */
   /****************************/

   ifp = fopen("people.dat", "r");

   /* check to make sure it opened */
   if (ifp == NULL)
   {
      fprintf(stderr, "The file could not be opened, exiting...\n\n");
      exit(-1);
   }

   /* read in each of the students */
   for (i = 0; i < NUMPPL; i++)
   {
      /*****************************************/
      /* STEP 2:                               */
      /* uncomment the following lines of code */
      /*****************************************/

      
      people[i].namePtr = (NAME*) malloc (sizeof(NAME));
      

      /* we should set all three member pointers to NULL */
      people[i].member1 = people[i].member2 = people[i].member3 = NULL;
      
      /*****************************/
      /* STEP 3:                   */
      /* use fscanf to read in the */
      /* students from the file    */
      /*****************************/
      fscanf(ifp, "%s", people[i].namePtr->firstName);
      fscanf(ifp, "%s", people[i].namePtr->lastName);
      fscanf(ifp, "%d", &people[i].age);

   }
   /******************/
   /* STEP 4:        */
   /* close the file */
   /******************/
   fclose(ifp);
}

/**********************************************************
  CreateTeam()
  Links together the three team members in the order in
    which they were passed in (first is member1, etc.)

  Input(s):  three pointers to variables of type PERSON
  Output(s): none
**********************************************************/
void CreateTeam(PERSON *first, PERSON *second, PERSON *third)
{
   /********************************/
   /* STEP 6:                      */
   /* write the lines to assign    */
   /* members 2 and 3 to the group */
   /* (member 1 is done for you)   */
   /********************************/
   first->member1 = second->member1 = third->member1 = first;
   first->member2 = second->member2 = third->member2 = second;
   first->member3 = second->member3 = third->member3 = third;

}


/****************************************
  PrintPeople()
  Prints out the name, age, and team
   members of all people in the array

  Input(s):  an array of type PERSON
  Output(s): none
****************************************/
void PrintPeople(PERSON people[NUMPPL])
{
   int i;

   for (i = 0; i < NUMPPL; i++)
   {
      /****************************************/
      /* STEP 7:                              */
      /* print out the student's name and age */
      /*    Name: xLastx, xFirstx             */
      /*    Age: xx                           */
      /****************************************/
      printf("\nName: %s, %s\n", people[i].namePtr->lastName,
	     people[i].namePtr->firstName);
      printf("Age: %d\n", people[i].age);

      /* if one of the members is NULL, the team isn't complete */
      if (people[i].member1 == NULL || people[i].member2 == NULL
	  || people[i].member3 == NULL)
      {
	 printf("\tIncomplete Team\n");
      }
      /* if all team members are present */
      else
      {


	 /********************************/
	 /* STEP 8:                      */
	 /* uncomment the code below     */
	 /********************************/
	 
	 printf("\tTeam:\n");
	 printf("\t\t%s %s\n", people[i].member1->namePtr->firstName,
		people[i].member1->namePtr->lastName);
	 printf("\t\t%s %s\n", people[i].member2->namePtr->firstName,
		people[i].member2->namePtr->lastName);
	 printf("\t\t%s %s\n", people[i].member3->namePtr->firstName,
		people[i].member3->namePtr->lastName);
	 
      }
   }
}

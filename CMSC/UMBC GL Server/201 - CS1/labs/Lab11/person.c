/*****************************************
 * File:              person.c
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
#include <string.h>
#include "person.h"

/****************************************
  ReadInPeople
  Reads in people from a file

  Input(s):  an array of type PERSON
  Output(s): number of students
****************************************/
PERSON* ReadInPeople(int *numPeople)
{
   int i;
   FILE *ifp;  /* file pointer */
   PERSON *people;

   /* open the file people.dat */
   ifp = fopen("people.dat", "r");

   /* check to make sure it opened */
   if (ifp == NULL)
   {
      fprintf(stderr, "The file could not be opened, exiting...\n\n");
      exit(-1);
   }
   
   fscanf(ifp, "%d", numPeople);
   /**************************/
   /* STEP 2:                */
   /* allocate enough memory */ 
   /* to store the students  */
   /**************************/

   people = (PERSON *) malloc(*numPeople * sizeof(PERSON));
   
   /* read in each of the students */
   for (i = 0; i < *numPeople; i++)
   {
      /* allocate memory for the teamGrade and name variables */
      people[i].namePtr = (NAME*) malloc (sizeof(NAME));
      
      /* we should set all three member pointers to NULL */
      people[i].member1 = people[i].member2 = people[i].member3 = NULL;
      
      /* read in their names, ages, and grade */
      fscanf(ifp, "%s %s %d %d", people[i].namePtr->firstName, 
	     people[i].namePtr->lastName, &people[i].age, &people[i].grade);
   }
   fclose(ifp);

   return people;
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
   first->member1 = second->member1 = third->member1 = first;
   first->member2 = second->member2 = third->member2 = second;
   first->member3 = second->member3 = third->member3 = third;

   first->teamGrade = (int *) malloc (sizeof(int));

   /*****************************/
   /* STEP 3:                   */
   /* determine the team grade  */
   /* and store it in teamGrade */
   /*                           */
   /* don't forget teamGrade is */
   /* a pointer to an integer!! */
   /*****************************/
   *first->teamGrade = (first->grade + second->grade + third->grade) / 3;
   first->member1->teamGrade = first->teamGrade;
   first->member2->teamGrade = first->teamGrade;
   first->member3->teamGrade = first->teamGrade;
}


/****************************************
  PrintPeople()
  Prints out the name, age, and team
   members of all people in the array

  Input(s):  an array of type PERSON
  Output(s): none
****************************************/
void PrintPeople(PERSON people[], int numPeople)
{
   int i;

   for (i = 0; i < numPeople; i+=3)
   {
      printf("Name: %s, %s\nAge: %d\n\n", 
	     people[i].namePtr->lastName, 
	     people[i].namePtr->firstName,
	     people[i].age);
      
      /* if one of the members is NULL, the team isn't complete */
      if (people[i].member1 == NULL || people[i].member2 == NULL
	  || people[i].member3 == NULL)
      {
	 printf("\tIncomplete Team\n");
      }
      /* if all team members are present */
      else
      {
	 printf("\tTeam:\n");
	 /****************************/
	 /* STEP 4:                  */
	 /* print out the team grade */
	 /****************************/
	 printf("\t\tTeam Grade = %d\n", *people[i].member1->teamGrade);
	 printf("\t\t%s %s\n", people[i].member1->namePtr->firstName,
		people[i].member1->namePtr->lastName);
	 printf("\t\t%s %s\n", people[i].member2->namePtr->firstName,
		people[i].member2->namePtr->lastName);
	 printf("\t\t%s %s\n", people[i].member3->namePtr->firstName,
		people[i].member3->namePtr->lastName);
      }
   }
}

/****************************************
  GiveExtraCredit()
  Gives extra credit to the entire team that has the 
    passed in student as member1
    (extra credit = change grade to 115)

  Input(s):  a PERSON
  Output(s): none
****************************************/

void GiveExtraCredits(PERSON *leader)
{  
   /***********************************/
   /* STEP 5b:                        */
   /* write the code to give extra    */ 
   /* credit to the team in which     */ 
   /* "leader" is member1             */
   /*                                 */
   /* HINT: you can access the other  */
   /*       team members through the  */
   /*       pointers in the structure */
   /***********************************/
   *leader->member1->teamGrade = 115;
   *leader->member2->teamGrade = 115;
   *leader->member3->teamGrade = 115;
}

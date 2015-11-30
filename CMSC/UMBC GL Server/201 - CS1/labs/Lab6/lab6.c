/***************************************************** 
 * File: lab6.c
 * Created By: Brandon Wilson
 * Created on: March 14, 2007
 * Email: bwilson1@umbc.edu
 * Section: All
 * 
 * Description: This file contains the driver for lab 6
 ******************************************************/

#include <stdio.h>
#include "student.h"

#define MAX_STUDENTS 50

int main()
{
   STUDENT students[MAX_STUDENTS];
   int numStudents = 0;
   char firstName[MAX_NAME_LENGTH];
   char lastName[MAX_NAME_LENGTH];
   char newFirstName[MAX_NAME_LENGTH];
   char newLastName[MAX_NAME_LENGTH];

   printf("Welcome to Lab 6! Structures and Strings Galore!\n\n");

   /*
   ** Step 3b
   ** Uncommment this call.
   */

    numStudents = ReadStudents(students);

   /*
   ** Step 4b
   ** Uncomment this call.
   */

   
   CalculateScores(students, numStudents);
   PrintStudents(students, numStudents);
   

   /*
   ** Step 5b
   ** Uncomment the remainder of this code
   */

   
   printf("\n\nEditing the roster...\n\n");
   
   scanf("%s", firstName);
   scanf("%s", lastName);

   scanf("%s", newFirstName);
   scanf("%s", newLastName);
   
   ChangeName(students, numStudents, firstName, lastName, newFirstName,
              newLastName);

   PrintStudents(students, numStudents);
   
   

   return 0;
}

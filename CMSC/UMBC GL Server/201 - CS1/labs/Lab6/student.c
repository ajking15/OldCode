/***************************************************** 
 * File: student.c
 * Created By: Brandon Wilson
 * Created on: March 14, 2007
 * Email: bwilson1@umbc.edu
 * Section: All
 * 
 * Description: This file contains some functions for manipulating arrays
 *              of STUDENT structures
 ******************************************************/

#include <stdio.h>
#include <string.h>
#include "student.h"

#define EXAM_WEIGHT 0.5

/*************************************************
 * ReadStudents -
 * Reads the student roster from the input file and store it in an array
 *   of STUDENT structures.
 *
 * Output: int - the number of students that were in the file
 * Input:  STUDENT students[] - the array of student structures
 *************************************************/
int ReadStudents(STUDENT students[])
{
   int numStudents = 0;
   int i = 0;

   /*
   ** Read the first line of the data file -- The number of student
   ** entries in the file.
   */
   scanf("%d", &numStudents);

   /*
   ** Step 3a
   ** Read each line of the file and store it in the the array of students.
   */
   for(i = 0; i < numStudents; i++)
   {
      scanf("%s", students[i].firstName); 
      scanf("%s", students[i].lastName);
      scanf("%lf", &students[i].midterm);
      scanf("%lf", &students[i].finalExam);
   }
   return numStudents;
   /* Don't forget the return value. */

}

/*************************************************
 * CalculateScores -
 * Calculates the final grade for each student using the defined constant
 *    weight for each exam.
 *
 * Output: NONE
 * Input:  STUDENT students[] - the array of student structures
 * Input:  int numStudents - the number of students in the array
 *************************************************/
void CalculateScores(STUDENT students[], int numStudents)
{
   int i = 0;
   

   /*
   ** Step 4a
   ** Calculate the the final score for student assuming each of the exam
   ** grades contributes 50% of their final grade.
   */
   for(i = 0; i < numStudents; i++)
   {
      students[i].finalGrade = 0.5 * (students[i].midterm + 
				      students[i].finalExam);
   }
}

/*************************************************
 * PrintStudents -
 * Prints each student's first name, last name, and final grade as a 
 *    percentage.
 *
 * Output: NONE
 * Input:  STUDENT students[] - the array of student structures
 * Input:  int numStudents - the number of students in the array
 *************************************************/
void PrintStudents(STUDENT students[], int numStudents)
{
   int i = 0;

   printf("All Students\n\n");

   printf("%12s %12s %13s\n", "First Name", "Last Name", "Final Grade");
   
   /*
   ** Print each student's name in the array in a table. Print their name 
   ** then their final grade one row at a time.
   */
   for(i = 0; i < numStudents; i++)
   {
      printf("%12s %12s %12.2lf%%\n", students[i].firstName, 
	     students[i].lastName, students[i].finalGrade);
   }
}

/*************************************************
 * ChangeName -
 * Searches for a student with the specified first and last name in the array
 *    and changes their name to the new first and last name. Does nothing
 *    if the first and last name do not match any student.
 *
 * Output: NONE
 * Input:  STUDENT students[] - the array of student structures
 * Input:  int numStudents - the number of students in the array
 * Input:  char originalFirstName[] - the student's original first name
 * Input:  char originalLastName[] - the student's original last name
 * Input:  char newFirstName[] - the student's new first name
 * Input:  char newLastName[] - the student's new last name
 *************************************************/
void ChangeName(STUDENT students[], int numStudents, 
		char originalFirstName[], char originalLastName[], 
		char newFirstName[], char newLastName[])
{
   int i = 0;

   for(i = 0; i < numStudents; i++)
   {

      /* 
      ** Step 5a
      ** For this step, insert the proper condition for the if-statement.
      ** The statement is checking to see if the current element in the 
      ** student array has the originalFirstName AND the originalLastName.
      ** Then it can change the first and last name appropriately.
      **
      ** HINT: Try using the function strcmp.
      */
      
      if(strcmp(students[i].firstName, originalFirstName) == 0 &&
	 strcmp(students[i].lastName, originalLastName) == 0)  
      {
	 strcpy(students[i].firstName, newFirstName);
	 strcpy(students[i].lastName, newLastName);
      }
      
   }
}

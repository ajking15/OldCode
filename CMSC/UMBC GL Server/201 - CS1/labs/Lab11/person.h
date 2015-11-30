/*****************************************
 * File:              person.h
 * Created by:        Katherine Gibson
 * Created on:        04/24/2007
 * Class:             CMSC201
 * Modified by:       Deepak Chinavle
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


/*************************/
/* STEP 1A:              */
/* Guard the header file */
/*************************/
#ifndef _person_h
#define _person_h

/* struct defining a name */
typedef struct name {
      char firstName[20];
      char lastName[20];
} NAME;

/* typedef for person pointer */
typedef struct person *PERSONPTR;

/* new struct with typedef person pointers */
typedef struct person {
      NAME *namePtr;
      int  age;
      int  grade;
      int* teamGrade;
      PERSONPTR member1;
      PERSONPTR member2;
      PERSONPTR member3;
} PERSON;


/****************************************
  ReadInPeople
  Reads in people from a file

  Input(s):  an array of type PERSON
  Output(s): number of students
****************************************/
PERSON* ReadInPeople(int *numPeople);

/**********************************************************
  CreateTeam()
  Links together the three team members in the order in
    which they were passed in (first is member1, etc.)

  Input(s):  three pointers to variables of type PERSON
  Output(s): none
**********************************************************/
void CreateTeam(PERSON *first, PERSON *second, PERSON *third);

/****************************************
  PrintPeople()
  Prints out the name, age, and team
   members of all people in the array

  Input(s):  an array of type PERSON
  Output(s): none
****************************************/
void PrintPeople(PERSON people[], int numPeople);

/****************************************
  GiveExtraCredit()
  Gives extra credit to the entire team that has the 
    passed in student as member1
    (extra credit = change grade to 115)

  Input(s):  a PERSON
  Output(s): none
****************************************/
void GiveExtraCredits(PERSON *leader);


/********************/
/* STEP 1B:         */
/* Guard the bottom */
/********************/


#endif

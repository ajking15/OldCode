/*****************************************
 * File:              person.h
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

#define NUMPPL 9

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
      PERSONPTR member1;
      PERSONPTR member2;
      PERSONPTR member3;
} PERSON;


/****************************************
  ReadInPeople
  Reads in people from a file

  Input(s):  an array of type PERSON
  Output(s): none
****************************************/
void ReadInPeople(PERSON people[]);

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
void PrintPeople(PERSON people[NUMPPL]);

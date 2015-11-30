/****************************************************************************
** File:        battle.h
** Author:      Christopher Mai
** Date:        10/08/07
** Section:     0203
** E-mail:      chrmai1@umbc.edu
**
**  This is the .h file for project 2. So this document contains
** all function prototypes & #defines which are used in both files.
*****************************************************************************/

/* #defines for core game */
#define MIN    0
#define MAX    9
#define TURNS  40

/* #defines for carrier placement */
#define LENGTHCARRIER      5
#define BOWROWCARRIER      0
#define BOWCOLCARRIER      5
#define STERNROWCARRIER    4
#define STERNCOLCARRIER    5
#define AIRCRAFTCARRIER   'A'

/* #define for battleship placement */
#define LENGTHBATTLE       4
#define BOWROWBATTLE       7
#define BOWCOLBATTLE       6
#define STERNROWBATTLE     7
#define STERNCOLBATTLE     9
#define BATTLESHIP        'B'

/* #define for cruiser placement */
#define LENGTHCRUISER      2
#define BOWROWCRUISER      1
#define BOWCOLCRUISER      8
#define STERNROWCRUISER    1
#define STERNCOLCRUISER    9
#define CRUISER           'C'

/* #define for destroyer placement */
#define LENGTHDESTROYER    3
#define BOWROWDESTROYER    4
#define BOWCOLDESTROYER    1
#define STERNROWDESTROYER  4
#define STERNCOLDESTROYER  3
#define DESTROYER         'D'

/* #define for submarine placement*/
#define LENGTHSUB          3
#define BOWROWSUB          7
#define BOWCOLSUB          0
#define STERNROWSUB        9
#define STERNCOLSUB        0
#define SUBMARINE         'S'

/****************************************************************************
** PrintGreeting --
**  Prints the greeting for this program
**   Input:  void
**   Output: void
*****************************************************************************/

void PrintGreeting();

/****************************************************************************
** PrintInstructions --
**  Prints instructions for naval battle
**   Input:  void
**   Output: void
*****************************************************************************/

void PrintInstructions();

/****************************************************************************
** InitializeArray --
**  Initializes the array of chars to hold all spaces
**   Input:  array
**   Output: void
****************************************************************************/

void InitializeArray(char array[][10]);

/****************************************************************************
** PlaceSubmarine --
**  Places submarine into the master array
**   Input:  master array which holds ship positions
**   Output: void
*****************************************************************************/

void PlaceSubmarine(char array[][10]);

/****************************************************************************
** PlaceAircraftCarrier --
**  Places aircraft carrier into the master array
**   Input:  master array which holds ship positions
**   Output: void
*****************************************************************************/

void PlaceAircraftCarrier(char array[][10]);

/****************************************************************************
** PlaceDestroyer --
**  Places destroyer into the master array
**   Input:  master array which holds ship positions
**   Output: void
*****************************************************************************/

void PlaceDestroyer(char array[][10]);

/****************************************************************************
** PlaceCruiser --
**  Places cruiser into the master array
**   Input:  master array which holds ship positions
**   Output: void
*****************************************************************************/

void PlaceCruiser(char array[][10]);

/****************************************************************************
** PlaceBattleship --
**  Places battleship into the master array
**   Input:  master array which holds ship positions
**   Output: void
*****************************************************************************/

void PlaceBattleship(char array[][10]);

/***************************************************************************
** GetValidInt --
**  Gets an int within a desired range
**   Input:  min and max values for desired range
**   Output: a validated int
****************************************************************************/

int GetValidInt(int min, int max);

/***************************************************************************
** GetValidAttack --
**  Controls turn counter and when while loop will terminate in main,
**  and decides hit/miss or already hit and place the corresponding mark
**  into both the master array and board array, wether or not a ship is sunk
**   Input:  turn counter
**   Output: modified turn counter
****************************************************************************/

char GetValidAttack(char turnCounter, char master[][10], char board[][10]);

/****************************************************************************
 ** PlaceShips --
 **  Takes the array and calls five other place ships functions to place into
 **  the master array
 **   Input:  master array
 **   Output: void
 ****************************************************************************/

void PlaceShips(char array[][10]);

/****************************************************************************
 ** PrintBoard --
 **  Prints out the board for the player to see uses board array during game
 **  play and master array to print out everything if you lose
 **   Input:  the array values to fill the board
 **   Output: void
 ****************************************************************************/

void PrintBoard(char array[][10]);

/***************************************************************************
 ** CheckShips --
 **  Calls five other check ship functions and if all five are hit signals
 **  games end
 **   Input:  board array
 **   Output: total number of arrays
 ***************************************************************************/

int CheckShips(char array[][10]);

/***************************************************************************
 ** CheckAircraft --
 **  Checks for aircraft hit condition
 **   Input:  board array and ship counter
 **   Output: number to help determine game end
 ***************************************************************************/

int CheckAircraft(char array[][10], int ship);

/***************************************************************************
 ** CheckBattleship --
 **  Checks battleship hit condition
 **   Input:  board array and ship counter
 **   Output: number to help determine game end
 ***************************************************************************/

int CheckBattleship(char array[][10], int ship);

/****************************************************************************
 ** CheckCruiser --
 **  Checks cruiser hit condition
 **   Input:  board array and ship counter
 **   Output: number to help determine game end
 ****************************************************************************/

int CheckCruiser(char array[][10], int ship);

/****************************************************************************
 ** CheckDestroyer --
 **  Checks destroyer hit condition
 **   Input:  board array and ship conditions
 **   Output: number to help determine game end
 ****************************************************************************/

int CheckDestroyer(char array[][10], int ship);

/****************************************************************************
 ** CheckSubmarine --
 **  Checks submarine hit condition
 **   Input:  board array and ship conditions
 **   Output: number to help determine game end
 ***************************************************************************/

int CheckSubmarine(char array[][10], int ship);

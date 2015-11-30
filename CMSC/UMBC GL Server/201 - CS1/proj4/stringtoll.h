               
/*****************************************************************************\
* Filename:     stringtoll.h                                                  *
* Author:       Sue Evans                                                     *
* Date Written: 10/26/07                                                      *
* Section:      All                                                           *
* Email:        bogar@cs.umbc.edu                                             *
*                                                                             *
* This file contains the symbolic constant of the modifier necessary for      *
* changing ascii digits into their numeric equivalents and the function       *
* prototype for a function called StringToLongLong that is a module for       *
* changing a string comprised of ascii digits into its numeric, long long     *
* equivalent.                                                                 *
******************************************************************************/


/* Subtracting this modifier from an ascii digit 
*  will produce its numeric equivalent */
#define MODIFIER    48


/************************************
* StringToLongLong() constructs a long long from its string version 
* by modifying the ascii value into its numeric equivalent and working 
* backwards, multiplying by increasing powers of 10 
*
* Inputs: the string to be changed into a long long
* Output: the long long equivalent of the string passed in
************************************/
long long StringToLongLong(char string[]);

/************************************************
 * File: geometry.c
 * Created by: Brandon Wilson 
 * Created on: February 22, 2006
 * Section: 9999
 * Email: bwilson1@umbc.edu
 *
 * Description: holds the function definition(s) listed in geometry.h
 * - TriangleArea() calculates the area of a triangle, 
 *   based on the base and height parameters given.
 *************************************************/


#include <stdio.h>
#include "geometry.h"


/*************************************************
 * TriangleArea -
 * Computes and returns the area of a triangle 
 *   with the given dimensions.
 *
 * Output: float - the area of the triangle
 * Input:  float base - the width of the base of the 
 *                      triangle
 *         float height - the height of the triangle
 *************************************************/

/*------------------Step 5--------------------*
 * Type out the definition for the TriangleArea
 * function below
 *--------------------------------------------*/
double TriangleArea(float  base, float height)
{
   return ( (base * height) / 2.0);
}

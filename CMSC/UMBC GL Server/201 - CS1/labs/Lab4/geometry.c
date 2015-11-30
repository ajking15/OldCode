/****************************************************
 * File: geometry.c
 * Created by: Brandon Wilson 
 * Created on: February 22, 2006
 * Modified by: Corey Weidenhammer
 * Modified on: March 2, 2006
 * Section: All
 * Email: Corey Weidenhammer
 *
 * Description: This file contains functions for Lab 4, and is a 
 * continuation of Lab 3's geometry.c  It now contains 3 
 * functions for calculating the area and perimeter of 
 * rectangles.
 * 
 *****************************************************/


#include "geometry.h"


/*************************************************
 * TriangleArea -
 * Computes and returns the area of the right 
 * triangle with the given dimensions.
 *
 * Output: double - the area of the triangle
 * Input:  double height - the height of the triangle
 *         double base - the width of the base of the 
 *                      triangle
 *************************************************/
double TriangleArea( double base, double height )
{
   return base * height / 2.0;

}


/************************************
 * CalculateArea -
 * Calculates the area of rectangle
 *
 * Output:  int - area of rectangle
 * Inputs:  int length - length of rectangle 
 *          int width - width of rectangle
 ************************************/
int CalculateArea( int length, int width )
{
   return length * width;
}


/*****************************************
 * CalculatePerimeter -
 * Calculates the perimeter of rectangle
 *
 * Output:  int - perimeter of rectangle
 * Inputs:  int length - length of rectangle 
 *          int width - width of rectangle
 *****************************************/
int CalculatePerimeter( int length, int width )
{
   return length * 2 + width * 2;
}





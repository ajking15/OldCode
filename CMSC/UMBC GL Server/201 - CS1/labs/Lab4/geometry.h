/***************************************************** 
 * File:  geometry.h
 * Created By: Brandon Wilson
 * Created on: February 22, 2006
 * Modified By: Corey Weidenhammer
 * Modified on: March 2, 2006
 * Section: All
 * Email: corey1@umbc.edu
 * 
 * Description: This file contains functions for Lab 4, and is a 
 * continuation of Lab 3's geometry.h  It now contains 3 
 * functions for calculating the area and perimeter of 
 * rectangles.
 ******************************************************/



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
double TriangleArea( double base, double height );


/************************************
 * CalculateArea -
 * Calculates the area of rectangle
 *
 * Output:  int - area of rectangle
 * Inputs:  int length - length of rectangle
 *          int width - width of rectangle
 ************************************/
int CalculateArea( int length, int width );


/************************************
 * CalculatePerimeter -
 * Calculates the perimeter of rectangle
 *
 * Output:  int - perimeter of rectangle
 * Inputs:  int length - length of rectangle
 *          int width - width of rectangle
 ************************************/
int CalculatePerimeter( int length, int width );



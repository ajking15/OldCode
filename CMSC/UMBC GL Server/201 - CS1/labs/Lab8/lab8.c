/**********************************************
 *  File: lab8.c
 *  Author: Carrie Desmond
 *  Last Modified By: Natalie Podrazik
 *  Date:    3/18/2003; 04/06/2006
 *  Section: 0207, 0208, 0301, 0302, 0303
 *  Email:   carrie1@cs.umbc.edu
 *
 *  This file will demonstrate opening files, 
 *  reading from files, writing to files, 
 *  closing files and dynamic memory allocation.
***********************************************/

#include <stdio.h>
#include <stdlib.h>


typedef struct coordinates
{
   int x;
   int y;
}COORDINATES;


int main()
{
   int numItems = 0;
   int i = 0;

   /************************
    *      STEPS 1 - 5     * 
    ************************/
 
   COORDINATES *points;
   FILE *ifp;
   ifp = fopen("data0.txt", "r");
   
   if(ifp == NULL)
   {
      fprintf(stderr, "Error: file data0.txt is not opening\n");
 
      exit(-1);
   }

   fscanf(ifp, "%d", &numItems);

   /* Get a block of memory big enough to hold numItems COORDINATES */
   points = (COORDINATES *) malloc (numItems * sizeof( COORDINATES ));
   
   /* Check to make sure memory is available */
   if (points == NULL)
   {
      printf ("Not enough memory\n");
      exit (-2);
   }

   /**************************
    *      STEPS 6 - 7       *
    **************************/
   for(i = 0; i < numItems; i++)
   {
      fscanf(ifp, "%d, %d", &points[i].x, &points[i].y);
   }

   fclose(ifp);

   for(i = 0; i < numItems; i++)
   {
      printf("Point %d: (%2d, %d)\n", i, points[i].x, points[i].y);  
   }

   /**************************
    *      STEPS 9       *
    **************************/
   free(points);
   
   return 0;
}

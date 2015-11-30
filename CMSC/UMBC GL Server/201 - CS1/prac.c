#include <stdio.h>
#include <string.h>

int main()
{
   int m = 5, n = 6, *p, *q;

   q = &n;
   p = &m;

   *p = *q * 2;
 
   printf("%d %d %d %d\n", m , n, *p, *q);

   return 0;
}

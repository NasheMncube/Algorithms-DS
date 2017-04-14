#include <stdio.h>
#include <stdlib.h>
#include "binary.h"

int main(int n, char *argv[n])
{
  int a=testBinary();
  if(a==16) printf("All tests pass\n");
  else printf("%d tests passed\n",a);
  return 0;
}

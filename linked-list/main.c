#include <stdio.h>
#include <stdlib.h>

#include "linkedlist.h"

int main(int argc, char *argv[argc]){
  List *newList = createList();
  print_list(newList);

  free(newList);
  return 0;
}

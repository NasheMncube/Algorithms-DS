#include <stdio.h>
#include <stdlib.h>

#include "linkedlist.h"

int main(int argc, char *argv[argc]){
  List *list = createList();

  

  print_list(list);
  free(list);
  return 0;
}

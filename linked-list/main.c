#include <stdio.h>
#include <stdlib.h>

#include "linkedlist.h"

int main(int argc, char *argv[argc]){
  List *list = createList();
  print_list(list);
  for(int *i = 0; *i<5; *i++)
    insert_in_place(list, (void*)i);

  print_list(list);
  delete_list(list);
  return 0;
}

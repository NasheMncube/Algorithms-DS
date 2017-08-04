#include <stdio.h>
#include <stdlib.h>

#include "linkedlist.h"

//TODO List creation and initilisation
//TODO Inserting into the list
//TODO Deleting from list (remember freeing)
//TODO Destroying list (remember FREEING)
//TODO Printing for debugging

void print_list(List *list){
  int counter = 0;
  printf("Head: %02x\n", list->head->val);
  if(list->current == NULL)
    printf("List is empty\n");
}

Node *init_node(void *val){
  Node *node = (Node*)malloc(sizeof(Node));
  node->val  = val;
  node->next = NULL;

  return node;
}

List* createList(){
  List *list = (List*)malloc(sizeof(List));
  list->head = init_node(NULL);
  list->current = NULL;
  return list;
}




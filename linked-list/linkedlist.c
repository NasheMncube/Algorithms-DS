#include <stdio.h>
#include <stdlib.h>

#include "linkedlist.h"

//TODO List initilisation

List* createList(){
  List *list = malloc(sizeof(List));

  Node *head = malloc(sizeof(Node));
  *head      = (Node){0, malloc(sizeof(Node))};
  Node *tail = malloc(sizeof(Node));
  *tail      = (Node){0, malloc(sizeof(Node))};

  *list = (List){head, head, tail};

  return list;
}

void print_list(List *l){
  printf("Head: %02x\n`", (int)l->head->val);
  printf("Current: %02x\n", (int)l->current->val );
  printf("Tail: %02x\n", (int)l->tail->val );
}

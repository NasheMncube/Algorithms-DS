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
  while(l->current->next != l->tail){
    counter++;
    l->current = l->current->next;
    printf("%d: %02x\n", counter, l->current->value);
  }
  printf("Tail: %02x\n", list->tail->val);
  
  
}

Node *init_node(void *val, Node *next_node){
  Node *node = (Node*)malloc(sizeof(Node));
  node->val  = val;
  node->next = next_node;

  return node;
}

List* createList(){
  List *list = (List*)malloc(sizeof(List));
  list->tail = init_node(NULL, NULL);
  list->head = init_node(NULL, list->tail);
  list->current = list->head;

  return list;
}

void insert_in_place(List *l, void *val){
  Node *new_node   = init_node(val, l->current->next);
  l->current->next = new_node;
}

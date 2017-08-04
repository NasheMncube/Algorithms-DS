/* 
 Author: Nashe Mncube
 
 In here we define the primary functions used in the implementation of a linked list.

*/

typedef struct Node{
  void *val;
  struct Node *next;
}Node;

typedef struct List{
  struct Node *head;
  struct Node *current;
}List;

List* createList();
void destroy_list(List *l);

void insert_in_place(List *l, void *val);
void insert_at_end(List *l, void *val);

void delete_in_place(List *l);
void print_list(List *list);

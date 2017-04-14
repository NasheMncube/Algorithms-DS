#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "binary.h"

//Functions todo
//DONE: newTree
//DONE: insert
//NODE: lookup
//DONE: deleteLeftNode, deleteRightNode
//DONE: setLeftNode, setRightNode
//DONE: write
//DONE: deleteLeftNodeData, deleteRightNodeData
//DONE: goLeft, goRight
//DONE: testBinary

struct node {
  struct node *left;
  struct node *right;
  void *data;
};
struct tree{
  struct node* current;
  struct node* root;
  int sizeItem;
};


tree *newTree(size_t a)
{
  tree *newTree=malloc(sizeof(tree));
  *newTree=(tree){malloc(sizeof(node)), malloc(sizeof(node)), a};
  newTree->current=NULL; newTree->root=NULL;
  return newTree;
}
//Returns a newNode structure
node *new(tree *t, void *p)
{
  node *new=malloc(sizeof(node));
  *new=(node){malloc(sizeof(node)), malloc(sizeof(node)), malloc(t->sizeItem)};
  new->left=NULL; new->right=NULL;
  new->data=p;
  return new;
}
void setLeftNode(tree *t, void *p)
{
  node *newNode=malloc(sizeof(node));
  newNode=new(t,p);

  t->current->left=newNode;
}

void setRightNode(tree *t, void *p)
{
  node *newNode=malloc(sizeof(node));
  newNode=new(t,p);

  t->current->right=newNode;
}
void write(tree *t, void *p)
{
  free(t->current->data);
  t->current->data=p;
}
void insert(tree *t, void *p) //Must work recursively.
{
  node *newNode=malloc(sizeof(node));
  newNode=new(t,p);

  if(t->current == NULL && t->root ==NULL)
  {
    t->current=newNode;
    t->root=newNode;
    return;
  }
  else if(t->current == NULL)
  {
    t->current = newNode;
    return;
  }
  else if(memcmp(p,t->current->data, t->sizeItem) == 0) //Node already in tree
  {
    t->current=t->root;
    return;
  }
  else if((memcmp(p,t->current->data,t->sizeItem)<0) && t->current->left != NULL)
  {
    t->current=t->current->left;
    insert(t, p);
    return;
  }
  else if((memcmp(p,t->current->data,t->sizeItem)>0) && t->current->right != NULL)
  {
    t->current=t->current->right;
    insert(t, p);
    return;
  }
  else if(t->current->left == NULL && (memcmp(p,t->current->data,t->sizeItem))<0)
  {
    setLeftNode(t, p);
    t->current=t->root;
    return;
  }
  else
  {
    setRightNode(t, p);
    t->current=t->root;
    return;
  }
}

node *lookup(tree *t, void *p)
{
  if(t->current->data == p)
  {
    void* returnVal=malloc(sizeof(node));
    memcpy(returnVal, t->current, sizeof(node));
    t->current=t->root;
    return returnVal;
  }
  else if(memcmp(p, t->current->data, t->sizeItem) <0)
  {
    t->current=t->current->left;
    return lookup(t, p);
  }
  else
  {
    t->current=t->current->right;
    return lookup(t,p);
  }
}

void deleteLeftNode(tree *t)
{
  t->current->left=NULL;
}
void deleteRightNode(tree *t)
{
  t->current->right=NULL;
}

void goLeft(tree *t)
{
  if(t->current->left == NULL) fprintf(stderr, "Error: calling go left with no left node on current node\n");
  else t->current=t->current->left;
}

void goRight(tree *t)
{
  if(t->current->right == NULL) fprintf(stderr, "Error: calling goRight with no right node on current node\n");
  else t->current=t->current->right;
}

void goRoot(tree *t)
{
  if(t->root == NULL) fprintf(stderr, "Error: calling goRoot with no root in tree\n");
  else t->current=t->root;
}

int testBinary()
{
  int n=0;
  tree *testTree=malloc(sizeof(tree));
  testTree=newTree(sizeof(int));

  //Test the newTree functions initializes correctly 1-2
  // Tree: {}
  if(testTree->current == NULL) n++;
  if(testTree->current== NULL) n++;

  if(n==2) printf("newTree function pass\n");

  //Test the insert function 3-9
  void *a=malloc(sizeof(int)), *b=malloc(sizeof(int)), *c=malloc(sizeof(int));
  void *d=malloc(sizeof(int)), *e=malloc(sizeof(int)), *f=malloc(sizeof(int));
  *(int*)a=42;*(int*)b=3;*(int*)c=320; *(int*)d=21; *(int*)e=1020; *(int*)f=1;
  insert(testTree, a); insert(testTree,b); insert(testTree,c);
  insert(testTree, d); insert(testTree, e); insert(testTree, f);
  /*        {42}
          /     \
        3       320
      /   \         \
     1    21        1020
  */
  if(*(int*)testTree->current->data == 42) n++;
  if(*(int*)testTree->current->right->data == 320) n++;
  if(*(int*)testTree->current->right->right->data == 1020) n++;
  if(testTree->current->right->left == NULL) n++;
  if(*(int*)testTree->current->left->right->data == 21) n++;
  if(*(int*)testTree->current->left->left->data == 1) n++;
  if(testTree->current->left->left->left == NULL) n++;

  if(n==9) printf("insert function pass\n");

  //Test the lookup function 10-11
  node *nodeD=malloc(sizeof(node));
  nodeD=lookup(testTree, d);
  node *nodeE=malloc(sizeof(node));
  nodeE=lookup(testTree, e);
  if(*(int*)nodeD->data == 21) n++;
  if(*(int*)nodeE->data == 1020) n++;

  if(n==11) printf("lookup function pass\n");

  //Test the write function 12-13
  //BEFORE
  /*        {42}
          /     \
        3       320
      /   \         \
     1    21        1020
  */
  write(testTree, c);
  //AFTER
  /*        {320}
          /     \
        3       320
      /   \         \
     1    21        1020
  */
  if(*(int*)testTree->root->data == 320) n++;
  if(*(int*)testTree->current->data == 320) n++;

  if(n==13) printf("write function pass\n");

  //Test goLeft, goRight and goRight 14-16
  /*        {320}
          /     \
        3       320
      /   \         \
     1    21        1020
  */
  goLeft(testTree);
  if(*(int*)testTree->current->data == 3) n++;
  goRight(testTree);
  if(*(int*)testTree->current->data == 21) n++;
  goRoot(testTree);
  if(*(int*)testTree->current->data == 320) n++;

  if(n==16) printf("goRoot, goLeft and goRight functions pass\n");





  return n;

}

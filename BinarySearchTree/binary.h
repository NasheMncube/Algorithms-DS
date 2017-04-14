/* This is an API which implements a binary search tree for numbers. The user
can insert values into the tree and lookup values*/

//Opaque object. Contains the tree search tree
struct tree;
typedef struct tree tree;
struct node;
typedef struct node node;

//Creates the tree structure which is initially empty with an empty root.
//The argument is the size of the type of number value eg int, long int, short etc
tree *newTree(size_t a);


//Insert nodes into tree
//Argument is pointer to the number and tree structure
void insert(tree *t, void *p);

//Write over data within current node
void write(tree *t, void *p);

//Go to left node of current node
void goLeft(tree *t);
//Go to right node of current node
void goRight(tree *t);
//Go to the root of tree
void goRoot(tree *t);

///Lookup values within the tree. Argument is tree structure and pointer
//to value being searched for. Returns pointer to node holding value
node *lookup(tree *t, void *p);

//Deletes the left node of current node and all subtrees of left node
void deleteLeftNode(tree *t);
//Delete the right node of current node and all subtrees of right node
void deleteRightNode(tree* t);

//Set left node. Argument is pointer to memory of value to be copied into node
void setLeftNode(tree *t, void *p);
//Set right node. Argument is pointer to memory of value to be copied into node
void setRightNode(tree *t, void *p);


//Test the tree structure
int testBinary();

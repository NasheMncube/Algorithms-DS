//
//  hash.h
//  This program implements a basic HashTable for string values.
//  The programm follows this tutorial https://www.youtube.com/watch?v=MfhjkfocRR0&index=1&list=PLTxllHdfUq4f7-uHOpxXnBUbsuLiI9pmb
//  Feel free to modify.
//
//  Created by nashe mncube on 14/04/2017.
//
//

#include <cstdlib>
#include <iostream>
#include <string>

#ifndef hash_h
#define hash_h

using std::string;

class hash{
    
private:
    static const int tableSize = 4;
    
    struct item{
        string key;
        string value;
        item* next;
    };
    
    item* HashTable[tableSize];
    
public:
    hash();
    ~hash();
    int Hash(string key);
    void AddItem(string key, string value);
    int NumberOfItemsInBucket(int index);
    void PrintTable();
    void PrintItemsInIndex(int index);
    void FindItem(string key);
    void RemoveItem(string key);
    
};

#endif /* hash_h */

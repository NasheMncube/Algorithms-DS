//
//  Hash.cpp
//  
//
//  Created by nashe mncube on 13/04/2017.
//
//

#include <cstdlib>
#include <iostream>
#include <string>

#include "hash.h"

using std::string; using std::cout; using std::endl;

hash::hash()
{
    for(int i = 0; i < tableSize; i++)
    {
        HashTable[i] = new item;
        HashTable[i] -> key = "empty";
        HashTable[i] -> value = "empty";
        HashTable[i] -> next = NULL;
    }
}

int hash::Hash(string key)
{
    int hash = 0;
    int index;

    
    for(int i = 0; i < key.length(); i++)
    {
        hash += (int)key[i];
        cout << "hash = " << hash << endl;
    }
    
    index = hash % tableSize;
    
    return index;
}

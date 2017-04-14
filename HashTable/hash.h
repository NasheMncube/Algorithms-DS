//
//  hash.h
//  
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

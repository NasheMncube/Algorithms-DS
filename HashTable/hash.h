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
    static const int tableSize = 10;
    
    struct item{
        string key;
        string value;
        item* next;
    };
    
    item* HashTable[tableSize];
    
public:
    hash();
    int Hash(std::string key);
    
};

#endif /* hash_h */

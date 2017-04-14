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

hash::~hash()
{
    item* Ptr;
    for(int i=0;i<tableSize;i++)
    {
        while(HashTable[i] != NULL)
        {
            Ptr = HashTable[i];
            HashTable[i] = HashTable[i]->next;
            delete Ptr;
        }
    }
}
void hash::AddItem(string key, string value)
{
    int bucket = hash::Hash(key);
    
    if(HashTable[bucket] -> key == "empty")
    {
        //The initial configuration of a bucket.
        HashTable[bucket] -> key = key;
        HashTable[bucket] -> value = value;
    }
    else
    {
        //Adding new item to bucket
        item* ptr = HashTable[bucket];
        item* n = new item;
        
        n -> key = key;
        n -> value = value;
        n -> next = NULL;
        
        while(ptr -> next != NULL) //Traverse bucket until we get last item in a bucket
            ptr  = ptr -> next;
        
        ptr -> next = n;
    }
}


int hash::Hash(string key)
{
    int hash = 0;
    int bucket;

    
    for(int i = 0; i < key.length(); i++)
        hash += ((int)key[i]*17);

    bucket = hash % tableSize;
    
    return bucket;
}

int hash::NumberOfItemsInBucket(int bucket)
{
    int count = 0;
    
    if(HashTable[bucket] -> key == "empty") return count; //No items added to bucket
    else
    {
        count++;
        item* ptr = HashTable[bucket];
        while(ptr -> next != NULL) //While we're not at end of bucket list
        {
            count++;
            ptr = ptr -> next;
        }
    }
    
    return count;
}

void hash::PrintTable()
{
    int number;
    for(int i = 0; i < tableSize; i++)
    {
        number = NumberOfItemsInBucket(i);
        cout << "---------------------------\n";
        cout << "index = " << i << endl;
        cout << HashTable[i] -> key << endl;
        cout << HashTable[i] -> value << endl;
        cout << "# of items = " << number << endl;
        cout << "---------------------------\n";
    }
}

void hash::PrintItemsInIndex(int bucket)
{
    item* Ptr = HashTable[bucket];
    
    if(Ptr -> key == "empty")
        cout << "Bucket " << bucket << " is empty\n";
    else
    {
        cout << "Bucket " << bucket << " contains the following items\n";
        
        while(Ptr != NULL)
        {
            cout<< "-----------------------\n";
            cout << Ptr -> key << endl;
            cout << Ptr -> value << endl;
            cout << "----------------------\n";
            Ptr = Ptr -> next;
        }
    }
}

void hash::FindItem(string key)
{
    int bucket = Hash(key);
    bool foundKey = false;
    
    string value;
    item* Ptr = HashTable[bucket];
    
    while(Ptr != NULL)
    {
        if(Ptr -> key == key)
        {
            foundKey = true;
            value = Ptr -> value;
            break;
        }
        Ptr = Ptr -> next;
            
    }
    
    if(foundKey)
        cout << "Value for key " <<  key << " is " << value << endl;
    else
        cout << "No value found for key " << key << endl;
}

void hash::RemoveItem(string key)
{
    int bucket = Hash(key);
    
    item* DelPtr;
    item* P1;
    item* P2;
    
    // Case 0 Bucket is empty
    
    // Case 1 Only 1 item contained in bucket and that item has matching key
    
    // Case 2 Match is located in the bucket but there are more items in the bucket
    
    // Case 3 Bucket does contain items, but first item isn't a match
    
         // Case 3.1 Looked through all items in bucket and there's no match
         // Case 3.2 There does exist a match in the bucket
    
    // Case 0
    if(HashTable[bucket] -> key == "empty" && HashTable[bucket] -> value  == "empty")
        cout << key << " was not found in HashTable\n";
    // Case 1
    else if(HashTable[bucket] -> key == key && HashTable[bucket] -> next == NULL)
    {
        HashTable[bucket] -> key = "empty";
        HashTable[bucket] -> value = "empty";
        cout << key << " was removed from hash-table\n";
    }
    // Case 2
    else if(HashTable[bucket] -> key == key)
    {
        DelPtr = HashTable[bucket];
        HashTable[bucket] = HashTable[bucket] -> next;
        delete DelPtr;
        
        cout << key << " was removed from hash-table\n";
    }
    // Case 3
    else
    {
        P1 = HashTable[bucket] -> next;
        P2 = HashTable[bucket];
        
        while(P1 != NULL && P1 -> key != key)
        {
            P2 = P1;
            P1 = P1 -> next;
        }
        
        if(P1 == NULL)
            cout << key << " was not found in HashTable\n";
        else
        {
            DelPtr = P1;
            P1 = P1 -> next;
            P2 -> next = P1;
            
            delete DelPtr;
            cout << key << " was removed from hash-table\n";
        }
    }
    
}























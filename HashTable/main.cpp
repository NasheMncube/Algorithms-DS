//
//  main.cpp
//  
//
//  Created by nashe mncube on 14/04/2017.
//
//

#include "hash.h"
#include <cstdlib>
#include <iostream>
#include <string>

using std::cout; using std::endl; using std::string; using std::cin;

int main(int argc, char** argv )
{
    hash Hashy;
    string key = "";
    
    //Testing Output
    Hashy.AddItem("Paul", "Locha");
    Hashy.AddItem("Kim", "Iced Mocha");
    Hashy.AddItem("Emma", "Strawberry Smoothie");
    Hashy.AddItem("Annie", "Hot Chocolate");
    Hashy.AddItem("Sarah", "Passion Tea");
    Hashy.AddItem("Pepper", "Caramel Mocha");
    Hashy.AddItem("Mike", "Chai Tea");
    Hashy.AddItem("Steve", "Apple Cider");
    Hashy.AddItem("Bill", "Root Beer");
    Hashy.AddItem("Marie", "Skinny Latte");
    Hashy.AddItem("Susan", "Water");
    Hashy.AddItem("Joe", "Green Tea");
    Hashy.PrintTable();
    
    /* Testing search
    while(key != "exit")
    {
        cout << "Search for ";
        cin >> key;
        if(key != "exit")
            Hashy.FindValue(key);
    }*/
    
    // Testing remove
    while(key != "exit")
    {
        cout << "Remove ";
        cin >> key;
        if(key != "exit")
            Hashy.RemoveItem(key);
    }
    
    Hashy.PrintTable();
    
    return 0;
}

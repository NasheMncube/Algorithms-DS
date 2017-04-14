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

using std::cout; using std::endl; using std::string;

int main(int argc, char** argv )
{
    int index;
    hash hashObj;
    
    index = hashObj.Hash("disfgais");
    
    cout << "index =  " << index << endl;
    
    return 0;
}

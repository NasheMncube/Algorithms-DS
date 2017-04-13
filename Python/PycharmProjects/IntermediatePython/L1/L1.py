#In this file we deal with string concatenation and formatting

names = ['Jeff', 'Gary', 'Jill', 'Samantha']

#If we want to iterate over the list with a greeting "Hello there" + name we'd do

#for name in names:
    #print('Hello there, ' + name)
    #The above code is readable and understandable but not necessarily
    #preferred. Below is an alternative
    #print(' '.join(['Hello there', name]))
    #This code achieves the same function but is not as easily understandable

#Suppose you want to print the list of names as a string

#print(', '.join(names))

#This is not very readable but achieves the same function

#import os
#location_of_file = 'Users/nashe/SnowyFiles/'
#file_name = 'example.txt'

#print(location_of_file + '/' + file_name) #The tempting way to get to a directory

#with open(os.path.join(location_of_file, file_name)) as f:
#    print(f.read)
#The above will read code designated by filepath

who = 'Gary'
how_many = 12

#Gary bought 12 apples today

#print(who, 'bought',how_many,'apples today') #this is the wrong way to do this

print('{} bought {} apples today!'.format(who, how_many)) #Correct nicer way
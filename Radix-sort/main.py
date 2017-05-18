"""
Author: Nashe Mncube
This script implements the radix sort algorithm
Information on the algorithm and it's implementation can be found here
https://en.wikipedia.org/wiki/Radix_sort
<<<<<<< HEAD
=======
Following this tutorial: http://www.geekviewpoint.com/python/sorting/radixsort
>>>>>>> b609e303c39b3eecb3e46c0b38ab3824c9137edf


The algorithm has average case complexity of big_theta(nk) where k is the word
size. The best case complexity is big_omega(nk) and worst case O(nk).
=======
"""


def radixSort(aList):
    RADIX = 10
    maxLength = False
    tmp, placement = -1, 1

    while not maxLength:
        maxLength = True
        buckets = [list() for _ in range(RADIX)] #We initialise our buckets

        for i in aList:
            tmp = int((int(i)-97) / placement) #The i'th digit of out current value
            buckets[tmp % RADIX].append(i)
            if maxLength and tmp > 0:
                maxLength = False
        a = 0
        for b in range(RADIX): #This loop places values from buckets back in list
            buck = buckets[b]
            for i in buck:
                aList[a] = i
                a += 1
        placement *= RADIX #Looking at the next digit of out values in list
    return aList


sortedList = radixSort([9443,
7126,
2938,
5299,
6366,
3917,
1006,
3562,
5498,
7685,
84,
1411,
8499,
2595,
6,
3708,
3342,
1203,
6896,
6394,
3663,
6818,
3136,
1337,
5000])

print(sortedList)

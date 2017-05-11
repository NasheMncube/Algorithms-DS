"""
Author: Nashe Mncube
This script implements the radix sort algorithm
Information on the algorithm and it's implementation can be found here
https://en.wikipedia.org/wiki/Radix_sort
Following this tutorial: http://www.geekviewpoint.com/python/sorting/radixsort
"""


def radixSort(aList):
    RADIX = 10
    maxLength = False
    tmp, placement = -1, 1

    while not maxLength:
        maxLength = True
        buckets = [list() for _ in range(RADIX)]

        for i in aList:
            tmp = int((int(i)-97) / placement)
            buckets[tmp % RADIX].append(i)
            if maxLength and tmp > 0:
                maxLength = False
        a = 0
        for b in range(RADIX):
            buck = buckets[b]
            for i in buck:
                aList[a] = i
                a += 1
        placement *= RADIX
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

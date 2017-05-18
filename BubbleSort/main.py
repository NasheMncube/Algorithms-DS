"""
 Implementation of the bubble sort algorithm, because

 https://en.wikipedia.org/wiki/Bubble_sort

 This algorith has best case complexity of O(n) which is the case in an already
 sorted l, needing only one pass through.

 The average and worst case of this algorithm is O(n^2).

"""
import random
def bubbleSort(l, swaps):
    for n in range(1, len(l)):
        if l[n-1] > l[n]:
            buffer = l[n-1]
            l[n-1] = l[n]
            l[n] = buffer
            swaps = True
    if not swaps:
        return l
    else:
        return bubbleSort(l, False)

l = random.sample(range(10000), 10)
print(bubbleSort(l, False))

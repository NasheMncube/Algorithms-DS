"""
 This is a script which implements the mergesort algorithm.

 The algorithm is described in detail here
 https://en.wikipedia.org/wiki/Merge_sort

 It has average case complexity of big_theta(nlogn).

 This implementation uses a down to top approach
"""


# The first step to merge sort is dividing the list until we get elements of size
# 1

def merge(left, right):
    ret = []
    i, j = 0, 0
    while(i < len(left) and j < len(right)):
        if left[i] <= right[j]:
            ret.append(left[i])
            i+=1
        elif left[i] > right[j]:
            ret.append(right[j])
            j+=1
        if i >= len(left):
            for item in right[j:]:
                ret.append(item)
            break
        if j>= len(right):
            for item in left[i:]:
                ret.append(item)
            break
    return ret

def mergeSort(array, first):
    div = len(array) %2 == 0
    if len(array) == 1:
        array = array[0]
        return array
    if first == True: #If first recursion step, break down list to single list
        array = [[x] for x in array]
    if div == False: #Odd number of elements, take last element and merge to penultimate
        array[-2] = merge(array[-2], array[-1])
        array.pop()
    buff = []
    for i in range(0, len(array)-1, +2):
        buff.append(merge(array[i], array[i+1]))
    return mergeSort(buff, False)

ret = mergeSort([778, -3993, 9624, -1000, 23, 578, 10],  True)
print(ret)

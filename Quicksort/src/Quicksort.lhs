Author: Nashe Mncube

An implementation of the Quicksort algorithm.

Quicksort is the best known sorting algorithm for sorting lists. The algorithm first selects a pivot from a list,
let's call this p. It then uses that pivot to sort list by dividing the list into two heaps. We then take x from each list.
If x<p then we append x:p. If x>=p p:x; The lower half will contain all elements of the list strictly lower than p and
the upper half contains all elements strictly larger or equal to p.Calling the function recursively on the two halves
will sort the list completely.Below is an implementation of the algorithm.

> import System.Random (randomRIO)

> qSort :: (Ord a) => [a] -> [a]
> qSort [] = []
> qSort (x:xs) = qSort([lower | lower <- xs, lower<x]) ++ [x] ++ qSort([higher | higher <- xs, higher>=x])

It's that easy folks. As we can see from above the pivot chosen in this implementation is the head of any input list.
This is bad as the choice of pivot is crucial to how efficient the algorithm works. Good choice of pivot ensures
that when we sort through a list, our lower half and upper are are roughly equal in size. By choosing the first element
as pivot, there's a greater risk of ending up with a worst O(n*n) complexity. An example of quicksortwhich selects a
random pivot is shown below..

> qSort' :: [Int] -> [Int]
> qSort' [] = []
> qSort' xs = do
>     let x = pick xs
>     let lower  = [z | z <- xs, z < x]
>     let higher = [z | z <- xs, z >= x]
>     return lower ++ higher


> pick :: [a] ->  IO a
> pick xs = fmap (xs !!) $ randomRIO (0, length xs - 1)


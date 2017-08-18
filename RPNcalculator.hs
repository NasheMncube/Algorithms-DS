import Data.List

solveRPN :: (Num a, Read a) => String -> a
{-
solveRPN = head . foldl foldingFunction [] . words
  where foldingFunction stack item = ....
-}
solveRPN = head . foldl foldingFuncton [] . words
  where foldingFuncton (x:y:ys) "*" = (x*y):ys
        foldingFuncton (x:y:ys) "+" = (x+y):ys
        foldingFuncton (x:y:ys) "-" = (x-y):ys
        foldingFuncton xs numberString = read numberString:xs

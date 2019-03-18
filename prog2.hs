sumSquare :: Int -> Int -> Int
sumSquare x y = x^2+y^2

hasEqHeads :: [Int] -> [Int] -> Bool
hasEqHeads lis1 lis2 = if head lis1 == head lis2 then True else False

superMe :: [Char] -> [Char]
superMe word = "Super " ++ word

returnSpace :: String -> Int
returnSpace word = length $ filter (== ' ') word

calcList :: [Float] -> [Float]
calcList lis = map (\n -> 3*n^2 + 2/n + 1) lis

negList :: [Int] -> [Int]
negList list = filter (\n -> n < 0) list

onetoHundred :: [Int] -> [Int]
onetoHundred list = filter (\n -> n >= 0 && n <= 100) list

ageSubtraction :: [Int] -> [Int]
ageSubtraction list = filter (\n -> 2019-n >= 1980) list

pairList :: [Int] -> [Int]
pairList list = filter (\n -> mod n 2 == 0) list

charFound :: Char -> String -> Bool
charFound c word = filter (\n -> c == n) word /= ""

endsWithA :: [String] -> [String]
endsWithA list = filter (\n -> (last n) == 'a' || (last n) == 'A') list 
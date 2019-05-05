-- Para rodar o programa, no terminal:
-- cabal install random
-- ghci -package random

import System.Random 

-- = AUXILIARES = --

annoyingIO :: Int -> IO(Int)
annoyingIO n = return n

evenN :: Int -> Bool
evenN n = if (mod n 2 == 0) then True else False


-- = 01 = --

oddN :: Int -> Bool
oddN n = if (mod n 2 == 1) then True else False


-- = 02 = --

hasN :: Int -> [Int] -> Bool
hasN n l = if (l /= [] && n /= 0) then (hasN (n - 1) (tail(l))) else if (l == [] && n == 0) then True else False


-- = 03 = --

inc99 :: [Int] -> [Int]
inc99 [] = []
inc99 (x:xs) = 99 + x : inc99 xs

-- = 04 = --

incN :: Int -> [Int] -> [Int]
incN _ [] = []
incN n (x:xs) = n + x : incN n xs 


-- = 05 = --

comment :: [String] -> [String]
comment [] = []
comment (x:xs) = ("--" ++ x) : comment xs


-- = 06 = --

onlyEven :: [Int] -> [Int]
onlyEven list = onlyEven' (\n -> evenN n) list

onlyEven' :: (Int -> Bool) -> [Int] -> [Int]
onlyEven' _ [] = []
onlyEven' f (x:xs)
 | f x = x : (onlyEven' f xs)
 | otherwise = onlyEven' f xs


-- = 07 = --

countdown :: Int -> IO[Int]
countdown 0 = return []
countdown n = do
    x <- annoyingIO n
    xs <- countdown (n - 1)
    return (x:xs)


-- = 08 = --

nRandoms :: Int -> IO([Int])
nRandoms 0 = return []
nRandoms n = do
    x <- randomRIO (1,100)
    xs <- nRandoms(n-1)
    return (x:xs)

-- = 09 = --

twoPower :: Int -> Int
twoPower n = fromIntegral (2 ^ n)

potN0 :: Int -> IO([Int])
potN0 0 = return [1]
potN0 n = do
    x <- annoyingIO (twoPower n)
    xs <- potN0 (n - 1)
    return (x:xs)

-- = 10 = --

zipmult :: [Int] -> [Int] -> [Int]
zipmult [] [] = []
zipmult _ [] = [0]
zipmult [] _ = [0]
zipmult (x:xs) (y:ys) = (x * y) : zipmult xs ys

-- = 11 = --

potencias' :: Int -> Int -> IO([Int])
potencias' n m = if n == m then return [(twoPower (n - 1))] else do
    x <- annoyingIO (twoPower (n - 1))
    xs <- potencias' (n + 1) m
    return (x:xs)

potencias :: Int -> IO([Int])
potencias n = potencias' 1 n


-- = 12 = --

cedulas :: Int -> [Int] -> [Int]
cedulas n [] = []
cedulas n (x:xs) = (div n x) : cedulas (mod n x) xs

callcedulas :: Int -> [Int]
callcedulas n = cedulas n [100, 50, 20, 10, 5, 2, 1]
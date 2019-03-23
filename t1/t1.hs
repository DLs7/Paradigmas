-- Trabalho 1 de Paradígmas da Programação
-- Por: Augusto Gai Dal'Asta

import Data.Char (toLower, toUpper)

-- = 1 = --

isVowel :: Char -> Bool
isVowel c = if c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O' || c == 'u' || c == 'U' then True else False


-- == 2 == --

addComma :: [String] -> [String]
addComma string = map (\s -> s ++ ",") string


-- = 3 = --

-- com funções anônimas
htmlListItems1 :: [String] -> [String]
htmlListItems1 strings = map (\s -> "<LI>" ++ s ++ "</LI>") strings

-- auxiliar de htmlListItems2
addHtmlList :: String -> String
addHtmlList string = "<LI>" ++ string ++ "</LI>"

-- sem funções anônimas
htmlListItems2 :: [String] -> [String]
htmlListItems2 strings = map addHtmlList strings


-- = 4 = --

-- com funções anônimas
noVowel1 :: String -> String
noVowel1 string = filter (\c -> c /= 'a' && c /= 'A' && c /= 'e' && c /= 'E' && c /= 'i' && c /= 'I' && c /= 'o' && c /= 'O' && c /= 'u' && c /= 'U') string

-- auxiliar para noVowel2
prohibitVowels :: Char -> Bool
prohibitVowels c = if c /= 'a' && c /= 'A' && c /= 'e' && c /= 'E' && c /= 'i' && c /= 'I' && c /= 'o' && c /= 'O' && c /= 'u' && c /= 'U' then True else False

-- sem funções anônimas
noVowel2 :: String -> String
noVowel2 string = filter (prohibitVowels) string


-- = 5 = --

-- com funções anônimas
censorIt1 :: String -> String
censorIt1 string = map (\c -> if c /= ' ' then '-' else c) string

-- auxiliar para censorIt2
officeOfCensorship :: Char -> Char
officeOfCensorship c = if c/= ' ' then '-' else c

-- sem funções anônimas
censorIt2 :: String -> String
censorIt2 string = map (officeOfCensorship) string


-- = 6 = --

firstName :: String -> String
firstName string = takeWhile (\c -> c /= ' ') string


-- = 7 = --

isInt :: String -> Bool
isInt string = if length (filter (\c -> c /= '0' && c /= '1' && c /= '2' && c /= '3' && c /= '4' && c /= '5' && c /= '6' && c /= '7' && c /= '8' && c /= '9') string) == 0 then True else False


-- = 8 = --

-- funções auxiliares
reverseName :: String -> String
reverseName = foldl (flip(:)) []

cutLastName :: String -> String
cutLastName string = takeWhile (\c -> c /= ' ') string

-- função principal
-- mesmo princípio da função firstName, porém reverto a string para começar do fim até o último espaço
-- após cortar essa string invertida no ultimo espaço da original, inverto ela de novo para voltar ao estado original
lastName :: String -> String
lastName string = reverseName (cutLastName (reverseName (string)))


-- = 9 = --

-- funções auxiliares
charToString :: Char -> String
charToString c = [c]

-- filtro para que não passem alguns nicks bizarros como "adal'asta"
anomalyFilter :: String -> String 
anomalyFilter string = filter (\c -> c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f' || c == 'g' || c == 'h' || c == 'i' || c == 'j' || c == 'k' || c == 'l' || c == 'm' || c == 'n' || c == 'o' || c == 'p' || c == 'q' || c == 'r' || c == 's' || c == 't' || c == 'u' || c == 'v' || c == 'w' || c == 'x' || c == 'y' || c == 'z') string

-- função principal
nickNameGenerator :: String -> String
nickNameGenerator string = anomalyFilter (map (toLower) (charToString (head string) ++ lastName string))


-- = 10 = --

switchStuff :: Char -> Char
switchStuff c
    | c == 'A' = '4'
    | c == 'E' = '3'
    | c == 'I' = '2'
    | c == 'O' = '1'
    | c == 'U' = '0'
    | c == 'a' = '4'
    | c == 'e' = '3'
    | c == 'i' = '2'
    | c == 'o' = '1'
    | c == 'u' = '0'
    | otherwise = c

encodeName :: String -> String
encodeName string = map (switchStuff) string

-- = 11 = --


-- = 12 = --

dotAdder :: String -> String
dotAdder string
    | length string == 0 = string ++ ".........."
    | length string == 1 = string ++ "........."
    | length string == 2 = string ++ "........"
    | length string == 3 = string ++ "......."
    | length string == 4 = string ++ "......"
    | length string == 5 = string ++ "....."
    | length string == 6 = string ++ "...."
    | length string == 7 = string ++ "..."
    | length string == 8 = string ++ ".."
    | length string == 9 = string ++ "."
    | otherwise = string

giveMe10Chars :: [String] -> [String]
giveMe10Chars strings = map (dotAdder) strings
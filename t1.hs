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
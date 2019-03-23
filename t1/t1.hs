-- Trabalho 1 de Paradígmas da Programação
-- Por: Augusto Gai Dal'Asta

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
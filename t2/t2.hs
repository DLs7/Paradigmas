import Text.Printf
import Data.Fixed

type Point     = (Float,Float)
type Rect      = (Point,Float,Float)
type Circle    = (Point,Float)

-------------------------------------------------------------------------------
-- Paletas
-------------------------------------------------------------------------------

redPalette :: Int -> [(Int,Int,Int)]
redPalette n = [(70+i*10,0,0) | i <- [0..(n-1)] ]

greenPalette :: Int -> [(Int,Int,Int)]
greenPalette n = [(0,70+i*10,0) | i <- [0..(n-1)] ]

bluePalette :: Int -> [(Int,Int,Int)]
bluePalette n = [(0,0,70+i*10) | i <- [0..(n-1)] ]

rgbPalette :: Int -> [(Int,Int,Int)]
rgbPalette n = take n $ cycle [(255,0,0),(0,0,255),(0,255,0)]

hsvPalette :: Int -> [(Int,Int,Int)]
hsvPalette n = [convertHSVtoRGB ((fromIntegral i) * (fromIntegral circ)/(fromIntegral n), 1, 1) | i <- [0..n]]
           where circ = 360

convertHSVtoRGB :: (Float,Float,Float) -> (Int,Int,Int)
convertHSVtoRGB (h,s,v)
  | h >= 0 && h < 60 = (255,round (x*255),0)
  | h >= 60 && h < 120 = (round (x*255),255,0)
  | h >= 120 && h < 180 = (0,255,round (x*255))
  | h >= 180 && h < 240 = (0,round (x*255),255)
  | h >= 240 && h < 300 = (round (x*255),0,255)
  | h >= 300 && h <= 360 = (255,0,round (x*255))
  | otherwise = (0,0,0)
  where h' = h/60.0
        x = (1 - (abs ((mod' h' 2) - 1)))


-------------------------------------------------------------------------------
--Strings SVG
-------------------------------------------------------------------------------

-- Gera string representando retângulo SVG 
-- dadas coordenadas e dimensoes do retângulo e uma string com atributos de estilo
svgRect :: Rect -> String -> String 
svgRect ((x,y),w,h) style = 
  printf "<rect x='%.3f' y='%.3f' width='%.2f' height='%.2f' style='%s' />" x y w h style

svgCircle :: Circle -> String -> String
svgCircle ((x,y),r) style =
  printf "<circle cx='%.3f' cy='%.3f' r='%.2f' style='%s' />" x y r style

-- String inicial do SVG
svgBegin :: Float -> Float -> String
svgBegin w h = printf "<html>\n<body>\n\n<h1>Programming Paradigms</h1>\n\n<svg width='%.2f' height='%.2f' xmlns='http://www.w3.org/2000/svg'>\n" w h 

-- String final do SVG
svgEnd :: String
svgEnd = "</svg>\n\n</body>\n</html>"

-- Gera string com atributos de estilo para uma dada cor
-- Atributo mix-blend-mode permite misturar cores
svgStyle :: (Int,Int,Int) -> String
svgStyle (r,g,b) = printf "fill:rgb(%d,%d,%d); mix-blend-mode: screen;'/>" r g b

-- Gera strings SVG para uma dada lista de figuras e seus atributos de estilo
-- Recebe uma funcao geradora de strings SVG, uma lista de círculos/retângulos e strings de estilo
svgElements :: (a -> String -> String) -> [a] -> [String] -> String
svgElements func elements styles = concat $ zipWith func elements styles
  
-------------------------------------------------------------------------------
-- Caso 1
-------------------------------------------------------------------------------

genRectsInLine :: Int -> Int -> [Rect]
genRectsInLine n c = [((m*(w+gap),(fromIntegral c)*(h+(4*gap))),w,h) | m <- [0..fromIntegral (n-1)]]
  where (w,h) = (50,50)
        gap = 10

genLineOfRects :: Int -> Int -> [String]
genLineOfRects lin col = ["  " ++ svgRect (last (genRectsInLine l c)) (svgStyle (last (greenPalette (l+(2*c)))))  | l <- [1..lin], c <- [col]]

genColumnsOfRects :: Int -> Int -> [[String]]
genColumnsOfRects lin col =  [genLineOfRects l c | l <- [lin], c <- [0..(col-1)]]

printRects :: Int -> Int -> String
printRects l c = printf (unlines $ (concat (genColumnsOfRects l c)))

genCase1 :: IO ()
genCase1 = do
    writeFile "case1.svg" $ svgstr1
    where svgstr1 = svgBegin w h ++ printRects l c ++ svgEnd
          (w, h) = (1500, 500)
          l = 10
          c = 5


-------------------------------------------------------------------------------
-- Caso 2
-------------------------------------------------------------------------------

createCirc :: Float -> Int -> String
createCirc r ncirc = printf (unlines $ ["   " ++ svgCircle ((x,y),10) (svgStyle (last (take (z+1) (hsvPalette ncirc)))) | z <- [0..(ncirc-1)], x <- [(r + 30) + (r * (cos((fromIntegral z)*2*pi/(fromIntegral ncirc))))], y <- [(r + 30) + (r * (sin((fromIntegral z)*2*pi/(fromIntegral ncirc))))]])

genCase2 :: IO()
genCase2 = do
  writeFile "case2.svg" $ svgstr2
  where svgstr2 = svgBegin w h ++ createCirc r c ++ svgEnd
        (w, h) = (1500, 500)
        r = 50
        c = 12

-------------------------------------------------------------------------------
-- Caso 3
-------------------------------------------------------------------------------

circlePiramid :: Float -> Float -> [Circle]
circlePiramid x0 y0 = [if n /= 0.5 then ((x0 + n*r, y0),r) else ((x0 + n*r, y0 - r),r) | n <- [0, 0.5, 1]]
    where r = 50

circlePiramidMatrix :: Int -> Int -> [Circle]
circlePiramidMatrix lin col = concat [(concat [circlePiramid (sp*col) (sp*lin) | col <- [1..fromIntegral col], lin <- [1..fromIntegral lin]])]
    where sp = 160

genCase3 :: IO()
genCase3 = do
  writeFile "case3.svg" $ svgstr3
  where svgstr3 = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgCircle circles (map svgStyle palette)
        circles = circlePiramidMatrix l c
        palette = rgbPalette(l*c*3)
        (w, h) = (1500, 500)
        r = 50
        l = 2
        c = 3

-------------------------------------------------------------------------------
-- Caso 4
-------------------------------------------------------------------------------

sinRedCirc :: Float -> Int -> String
sinRedCirc r ncirc = printf (unlines $ ["   " ++ svgCircle ((x,y),small_r) (svgStyle (last (redPalette (z + 1)))) | z <- [0..ncirc+1], x <- [(x0 + (offset*(fromIntegral z)))], y <- [y0 + (r * (sin(2*pi*(fromIntegral z)/(fromIntegral ncirc))))]])
  where small_r = 20
        x0 = 50
        y0 = 90
        offset = 30


sinGreenCirc :: Float -> Int -> String
sinGreenCirc r ncirc = printf (unlines $ ["   " ++ svgCircle ((x,y),small_r) (svgStyle (last (greenPalette (z + 1)))) | z <- [0..ncirc+1], x <- [(x0 + (offset*(fromIntegral z)))], y <- [y0 + (r * (sin(2*pi*(fromIntegral z)/(fromIntegral ncirc))))]])
  where small_r = 20
        x0 = 50
        y0 = 180
        offset = 30

sinBlueCirc :: Float -> Int -> String
sinBlueCirc r ncirc = printf (unlines $ ["   " ++ svgCircle ((x,y),small_r) (svgStyle (last (bluePalette (z + 1)))) | z <- [0..ncirc+1], x <- [(x0 + (offset*(fromIntegral z)))], y <- [y0 + (r * (sin(2*pi*(fromIntegral z)/(fromIntegral ncirc))))]])
  where small_r = 20
        x0 = 50
        y0 = 270
        offset = 30

genCase4 :: IO()
genCase4 = do
  writeFile "case4.svg" $ svgstr4
  where svgstr4 = svgBegin w h ++ sinRedCirc r q ++ sinGreenCirc r q ++ sinBlueCirc r q ++ svgEnd
        (w, h) = (1500, 500)
        r = 50
        q = 12

main = do 
  putStrLn "I'm now generating all the .svg cases!"
  putStrLn "Their file names are 'case1.svg', 'case2.svg', 'case3.svg' and 'case4.svg'."
  genCase1
  genCase2
  genCase3
  genCase4
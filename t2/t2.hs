import Text.Printf

type Point     = (Float,Float)
type Rect      = (Point,Float,Float)
type Circle    = (Point,Float)

-------------------------------------------------------------------------------
-- Paletas
-------------------------------------------------------------------------------

rgbPalette :: Int -> [(Int,Int,Int)]
rgbPalette n = take n $ cycle [(255,0,0),(0,255,0),(0,0,255)]

rgbBigPalette :: Int -> (Int, Int, Int)
rgbBigPalette n = last (take n $ cycle [(255,130,0),(249,255,0),(119,255,0),(0,255,10),(0,255,140),(0,239,255),(0,109,255),(20,0,255),(150,0,255),(255,0,229),(255,0,99),(255,0,0)])

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
svgStyle (r,g,b) = printf "fill:rgb(%d,%d,%d); mix-blend-mode: screen;" r g b

-- Gera strings SVG para uma dada lista de figuras e seus atributos de estilo
-- Recebe uma funcao geradora de strings SVG, uma lista de círculos/retângulos e strings de estilo
svgElements :: (a -> String -> String) -> [a] -> [String] -> String
svgElements func elements styles = concat $ zipWith func elements styles
  
-------------------------------------------------------------------------------
-- Caso 1
-------------------------------------------------------------------------------

greenPalette :: Int -> [(Int,Int,Int)]
greenPalette n = [(0,70+i*10,0) | i <- [0..n] ]

genRectsInLine :: Int -> Int -> [Rect]
genRectsInLine n c = [((m*(w+gap),(fromIntegral c)*(h+(4*gap))),w,h) | m <- [0..fromIntegral (n-1)]]
  where (w,h) = (50,50)
        gap = 10

genLineOfRects :: Int -> Int -> [String]
genLineOfRects lin col = ["  " ++ svgRect (last (genRectsInLine l c)) (svgStyle (last (greenPalette (l+(2*c)))))  | l <- [1..lin], c <- [col]]

genColumnsOfRects :: Int -> Int -> [[String]]
genColumnsOfRects lin col =  [genLineOfRects l c | l <- [lin], c <- [0..(col-1)]]

genCase1 :: IO ()
genCase1 = do
    writeFile "case1.svg" $ svgstr1
    where svgstr1 = svgBegin w h ++ printf (unlines $ (concat (genColumnsOfRects 10 5))) ++ svgEnd
          (w, h) = (1500, 500)


-------------------------------------------------------------------------------
-- Caso 2
-------------------------------------------------------------------------------

createCirc :: Float -> Float -> String
createCirc r ncirc = printf (unlines $ ["   " ++ svgCircle ((x,y), 10) (svgStyle (rgbBigPalette (round z))) | z <- [1..ncirc], x <- [(r + 30) + (r * (cos(z*2*pi/ncirc)))], y <- [(r + 30) + (r * (sin(z*2*pi/ncirc)))]])

genCase2 :: IO()
genCase2 = do
  writeFile "case2.svg" $ svgstr2
  where svgstr2 = svgBegin w h ++ createCirc 50 12 ++ svgEnd
        (w, h) = (1500, 500)

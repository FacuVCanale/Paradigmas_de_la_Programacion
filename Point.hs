module Point ( Point, newP, difP)
   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP = Poi
difP :: Point -> Point -> Float  -- distancia absoluta
difP (Poi a b) (Poi x y) = sqrt (fromIntegral ((a - x)^2) + fromIntegral ((b - y)^2))
module Point (Point , newP, difP)
   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP a b = Poi a b
difP :: Point -> Point -> Float 
difP (Poi a b) (Poi x y) = sqrt (fromIntegral ((a - x)^2) + fromIntegral ((b - y)^2))


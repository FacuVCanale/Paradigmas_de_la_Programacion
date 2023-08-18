module Point (Point , newP, difP)
   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP a b = Poi a b

difP :: Point -> Point -> Float 
difP (Poi a b) (Poi x y) = sqrt (fromIntegral ((a - x)^2) + fromIntegral ((b - y)^2))

p1 = newP 3 4
p2 = newP 0 0

tP = [
   newP (-1) 1 == Poi (-1) 1,
   difP p1 p2 == 5,
   True
   ]

allTestsP = and tP
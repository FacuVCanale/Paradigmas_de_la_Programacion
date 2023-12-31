module Point (Point , newP, difP)
   where

data Point = Poi Int Int deriving (Eq)


instance Show Point where
  show (Poi x y) = show (x,y)


newP :: Int -> Int -> Point
newP a b = Poi a b

difP :: Point -> Point -> Float 
difP (Poi a b) (Poi x y) = sqrt (fromIntegral ((a - x)^2) + fromIntegral ((b - y)^2))

---------------------------------

testPoi = newP (-1) 1 == Poi (-1) 1
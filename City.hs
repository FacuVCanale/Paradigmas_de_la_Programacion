module City (City, newC, nameC, distanceC )
    where

import Point

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC name location
  | null name == True = error "A city name must be chosen."
  | otherwise = Cit name location

nameC :: City -> String
nameC (Cit name _) = name

getLocationC :: City -> Point
getLocationC (Cit _ location) = location

distanceC :: City -> City -> Float
distanceC city1 city2 = difP (getLocationC city1) (getLocationC city2)

------------------------------------
pA = newP 0 0
cA = newC "A" pA

pB = newP 3 4
cB = newC "B" pB


tC = [
    
    cA == Cit "A" pA,
    
    getLocationC cA == pA,

    True
        ]

allTestC = and tC
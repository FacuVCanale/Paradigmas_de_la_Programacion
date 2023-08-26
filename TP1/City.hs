module City (City, newC, nameC, distanceC )
    where


import Point

data City = Cit String Point deriving (Eq)

instance Show City where
  show (Cit name loc) = "City '" ++ name ++ "'\nCoordinates: " ++ show loc

newC :: String -> Point -> City
newC name loc
  | null name = error "A city name must be chosen."
  | otherwise = Cit name loc

nameC :: City -> String
nameC (Cit name _) = name

getlocationC :: City -> Point
getlocationC (Cit _ loc) = loc

distanceC :: City -> City -> Float
distanceC c1 c2 = difP (getlocationC c1) (getlocationC c2)

------------------------------------

pA = newP 0 0
cA = newC "A" pA

pB = newP 3 4
cB = newC "B" pB


tC = [

    cA == Cit "A" pA,

    getlocationC cA == pA,

    True
        ]

allTestC = and tC
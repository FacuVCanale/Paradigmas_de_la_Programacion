{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Eta reduce" #-}

module City (City, newC, nameC, distanceC )
where

import Point

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC name location = Cit name location

nameC :: City -> String
nameC (Cit name _) = name

distanceC :: City -> City -> Float
distanceC city1 city2 = difP (getLocationC city1) (getLocationC city2)

getLocationC :: City -> Point
getLocationC (Cit _ location) = location

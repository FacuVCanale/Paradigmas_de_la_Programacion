module TestCity 
    where

import City
import Point

import TestPoint

mercedes = newC "Mercedes" mercedesP

mercedes2 = newC "Mercedes" mercedesP

zarate = newC "Zárate" zarateP

victoria = newC "Victoria" victoriaP


distanceMV = distanceC mercedes victoria

distanceMZ = distanceC mercedes zarate

distanceZV = distanceC zarate victoria


getLocationC :: City -> Point
getLocationC (Cit name location) = location

getNameC :: City -> String
getNameC (Cit name location) = name

sameLocationC :: City -> City -> Bool
sameLocationC city1 city2 = getLocationC city1 == getLocationC city2

sameNameC :: City -> City -> Bool
sameNameC city1 city2 = getNameC city1 == getNameC city2

sameC :: City -> City -> Bool
sameC city1 city2 = sameNameC city1 city2 && sameLocationC city1 city2

finalTestC :: City -> City-> (Bool, Bool, Bool, Float, Bool)
finalTestC city1 city2 = (sameNameC city1 city2, sameLocationC city1 city2, sameC city1 city2, distanceC city1 city2, sameNameC city1 city2 && sameLocationC city1 city2 && sameC city1 city2 && distanceC city1 city2 == 0)

test1 :: (Bool, Bool, Bool, Float, Bool)
test1 = finalTestC mercedes zarate

test2 :: (Bool, Bool, Bool, Float, Bool)
test2 = finalTestC mercedes mercedes2

test3 :: (Bool, Bool, Bool, Float, Bool)
test3 = finalTestC victoria zarate
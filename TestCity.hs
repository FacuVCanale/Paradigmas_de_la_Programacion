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

sameNameC :: City -> City -> Bool
sameNameC city1 city2 = nameC city1 == nameC city2

sameC :: City -> City -> Bool
sameC city1 city2 = sameNameC city1 city2 

finalTestC :: City -> City-> (Bool, Bool, Float, Bool)
finalTestC city1 city2 = (sameNameC city1 city2, sameC city1 city2, distanceC city1 city2, sameNameC city1 city2 && sameC city1 city2 && distanceC city1 city2 == 0)

test1 :: (Bool, Bool, Float, Bool)
test1 = finalTestC mercedes zarate

test2 :: (Bool, Bool, Float, Bool)
test2 = finalTestC mercedes mercedes2

test3 :: (Bool, Bool, Float, Bool)
test3 = finalTestC victoria zarate
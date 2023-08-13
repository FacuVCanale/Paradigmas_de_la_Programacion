module TestLink 
    where

import Link
import City
import Quality
import Point

import TestCity
import TestQuality


------------------------------------------------------

linkMZ = newL mercedes zarate badQ

linkZM = newL zarate mercedes badQ

linkMV = newL victoria mercedes highQ


sameL :: Link -> Link -> Bool
sameL (Lin city1 city2 quality1) (Lin city3 city4 quality2) = quality1 == quality2 && (city1 == city3 && city2 == city4|| city1 == city4 && city2 == city3)

sameLtest1 = sameL linkZM linkMZ

sameLtest2 = sameL linkMV linkMZ

connectsLtest1 = [connectsL mercedes linkMZ, connectsL mercedes linkZM, connectsL zarate linkMZ, connectsL zarate linkZM]

connectsLtest2 = connectsL zarate linkMV

capacityLtest = (capacityL linkMZ, capacityL linkMV, capacityL linkZM == capacityL linkMZ)

delayLtest = (delayL linkMZ, delayL linkMV, delayL linkZM == delayL linkMZ)
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
sameL l1 l2 = l1 == l2

connectsLtest1 = [connectsL mercedes linkMZ, connectsL mercedes linkZM, connectsL zarate linkMZ, connectsL zarate linkZM]

connectsLtest2 = connectsL zarate linkMV

capacityLtest = (capacityL linkMZ, capacityL linkMV, capacityL linkZM == capacityL linkMZ)

delayLtest = (delayL linkMZ, delayL linkMV, delayL linkZM == delayL linkMZ)
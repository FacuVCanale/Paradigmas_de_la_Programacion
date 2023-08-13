module TestPoint 
    where

import Point

origin = newP 0 0

origin2 = newP 0 0

pointA = newP 2 1

pointB = newP (-1) (-8)

pointC = newP (-1) (-2)


mercedesP = newP (-34653175) (-59429675)

zarateP = newP (-34096012) (-59024116)

victoriaP = newP (-34446312) (-58530667)

equalP :: Point -> Point -> Bool
equalP p1 p2 = p1 == p2

sameNormP :: Point -> Point -> Bool
sameNormP p1 p2 = difP origin p1 == difP origin p2

distance = difP pointA pointB 

finalTestP :: Point -> Point -> (Bool, Bool, Float, Bool)
finalTestP p1 p2 = (equalP p1 p2, sameNormP p2 p1, difP p1 p2, equalP p1 p2 && sameNormP p1 p2 && difP p1 p2 == 0)

test1 :: (Bool, Bool, Float, Bool)
test1 = finalTestP pointA pointB

test2 :: (Bool, Bool, Float, Bool)
test2 = finalTestP pointA pointC

test3 :: (Bool, Bool, Float, Bool)
test3 = finalTestP origin2 origin

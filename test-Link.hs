import Link


--CAMBIAR ESTO

mercedesP = newP (-34653175) (-59429675)

mercedes = newC "Mercedes" mercedesP

mercedes2 = newC "Mercedes" mercedesP

zarateP = newP (-34096012) (-59024116)

zarate = newC "Zárate" zarateP

victoriaP = newP (-34446312) (-58530667)

victoria = newC "Victoria" victoriaP



badQ = Qua "Bad" 1 100.0

badQ2 = Qua "Bad" 1 100.0

mediumQ = Qua "Medium" 5 50.0

highQ = Qua "High" 10 10.0

mediumBadQ = Qua "Medium" 5 100.0

highMediumQ = Qua "High" 5 10.0

badMediumHighQ = Qua "Bad" 5 10.0

------------------------------------------------------

linkMZ = newL mercedes zarate badQ





sameL :: Link -> Link -> Bool
sameL l1 l2 = l1 == l2



distance = difP pointA pointB 

finalTestP :: Point -> Point -> (Bool, Bool, Float, Bool)
finalTestP p1 p2 = (equalP p1 p2, sameNormP p2 p1, difP p1 p2, equalP p1 p2 && sameNormP p1 p2 && difP p1 p2 == 0)

test1 :: (Bool, Bool, Float, Bool)
test1 = finalTestP pointA pointB

test2 :: (Bool, Bool, Float, Bool)
test2 = finalTestP pointA pointC

test3 :: (Bool, Bool, Float, Bool)
test3 = finalTestP origin2 origin



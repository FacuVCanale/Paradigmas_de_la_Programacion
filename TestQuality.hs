module TestQuality
    where
import Quality

-- delay = ms
-- quantity = tunnels
-- string = name (NIDEA BRO)

badQ = Qua "Bad" 1 100.0

badQ2 = Qua "Bad" 1 100.0

mediumQ = Qua "Medium" 5 50.0

highQ = Qua "High" 10 10.0

mediumBadQ = Qua "Medium" 5 100.0

highMediumQ = Qua "High" 5 10.0

badMediumHighQ = Qua "Bad" 5 10.0


sameQ :: Quality -> Quality -> Bool
sameQ q1 q2 = q1 == q2

sameNameQ :: Quality -> Quality -> Bool
sameNameQ (Qua name capacity delay) (Qua name2 capacity2 delay2) = name == name2

sameCapacityQ :: Quality -> Quality -> Bool
sameCapacityQ q1 q2 = capacityQ q1 == capacityQ q2

sameDelayQ :: Quality -> Quality -> Bool
sameDelayQ q1 q2 = delayQ q1 == delayQ q2

finalTestQ :: Quality -> Quality -> [Bool]
finalTestQ q1 q2 = [sameQ q1 q2, sameNameQ q1 q2, sameCapacityQ q1 q2, sameDelayQ q1 q2, sameDelayQ q1 q2 && sameQ q1 q2 && sameNameQ q1 q2 && sameCapacityQ q1 q2 && sameDelayQ q1 q2]

test1 :: [Bool]
test1 = finalTestQ badQ mediumQ

test2 :: [Bool]
test2 = finalTestQ badQ badMediumHighQ

test3 :: [Bool]
test3 = finalTestQ mediumBadQ badMediumHighQ

test4 :: [Bool]
test4 = finalTestQ highQ badMediumHighQ

test5 :: [Bool]
test5 = finalTestQ badQ badQ2
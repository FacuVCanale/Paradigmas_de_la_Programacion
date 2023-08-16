module TestQuality
    where
import Quality

-- delay = ms
-- newQntity = tunnels
-- string = name (NIDEA BRO)

badQ = newQ "Bad" 1 100.0

badQ2 = newQ "Bad" 1 100.0

mediumQ = newQ "Medium" 5 50.0

highQ = newQ "High" 10 10.0

mediumBadQ = newQ "Medium" 5 100.0

highMediumQ = newQ "High" 5 10.0

badMediumHighQ = newQ "Bad" 5 10.0


sameQ :: Quality -> Quality -> Bool
sameQ q1 q2 = q1 == q2

sameCapacityQ :: Quality -> Quality -> Bool
sameCapacityQ q1 q2 = capacityQ q1 == capacityQ q2

sameDelayQ :: Quality -> Quality -> Bool
sameDelayQ q1 q2 = delayQ q1 == delayQ q2

finalTestQ :: Quality -> Quality -> [Bool]
finalTestQ q1 q2 = [sameQ q1 q2, sameCapacityQ q1 q2, sameDelayQ q1 q2, sameDelayQ q1 q2 && sameQ q1 q2 && sameCapacityQ q1 q2 && sameDelayQ q1 q2]

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
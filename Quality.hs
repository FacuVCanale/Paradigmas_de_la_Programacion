module Quality (Quality, newQ, capacityQ, delayQ )
   where

data Quality = Qua String Int Float deriving (Eq, Show)

checkDataQ :: String -> Int -> Float -> Bool
checkDataQ str cap delay = not (null str) && cap > 0 && delay >= 0

newQ :: String -> Int -> Float -> Quality
newQ str cap delay
  | not (checkDataQ str cap delay) = error "It is not possible to create this quality."
  | otherwise = Qua str cap delay

capacityQ :: Quality -> Int
capacityQ (Qua _ cap _) = cap

delayQ :: Quality -> Float
delayQ (Qua _ _ delay) = delay

--------------------------------

qA = newQ "A" 10 1.0

qB = newQ "B" 15 0.5

tQ = [

   not (checkDataQ "" 1 1),

   checkDataQ "Mercedes" 10 1.0,

   qA == Qua "A" 10 1.0,

   True
      ]

allTestQ = and tQ

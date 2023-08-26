module Link (Link, newL, linksL, connectsL, capacityL, delayL )
   where

import Point
import City
import Quality

data Link = Lin City City Quality deriving (Eq)

instance Show Link where
  show (Lin c1 c2 q) = 
    nameC c1 ++ " -[C:" ++ show (capacityQ q) ++ ", D:" ++ show (delayQ q) ++ "]- " ++ nameC c2


errorRepeatedC = error "City provided is repeated."

areCitiesOrdered :: City -> City -> Bool
areCitiesOrdered c1 c2 = nameC c1 <= nameC c2

newL :: City -> City -> Quality -> Link
newL c1 c2 q
  | not (areCitiesOrdered c1 c2) = error "Links' cities must follow alphabetical order."
  | c1 == c2 = errorRepeatedC
  | distanceC c1 c2 == 0 || nameC c1 == nameC c2 = error "The cities provided have at least one common attribute."
  | otherwise = Lin c1 c2 q

connectsL :: City -> Link -> Bool
connectsL cA (Lin c1 c2 _) = cA == c1 || cA == c2

linksL :: City -> City -> Link -> Bool
linksL c1 c2 l
  | c1 == c2 = errorRepeatedC
  | otherwise = connectsL c1 l && connectsL c2 l

capacityL :: Link -> Int
capacityL (Lin _ _ q) = capacityQ q

delayL :: Link -> Float
delayL (Lin c1 c2 q) = delayQ q * distanceC c1 c2

---------------------------

pA = newP 0 0

pB = newP 3 4

cA = newC "A" pA

cB = newC "B" pB

qA = newQ "A" 10 1.0

lAB = newL cA cB qA

tL = [

   not (areCitiesOrdered cB cA),

   lAB == Lin cA cB qA,

   True
      ]

allTestsL = and tL
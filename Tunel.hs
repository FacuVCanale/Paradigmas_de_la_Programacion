module Tunel (Tunel, newT, connectsT, usesT, delayT )
   where

import ErrorCatching

import Point
import City
import Quality
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT ls = Tun ls


correctConnectionTwoLOneSided :: City -> City -> [Link] -> Bool
correctConnectionTwoLOneSided c1 c2 [l1, l2] = connectsL c1 l1 && not (connectsL c1 l2)

correctConnectionTwoL :: City -> City -> [Link] -> Bool
correctConnectionTwoL c1 c2 twoLs = correctConnectionTwoLOneSided c1 c2 twoLs && correctConnectionTwoLOneSided c2 c1 (reverse twoLs)

correctConnectionVariousLOneSided :: City -> City -> [Link] -> Bool
correctConnectionVariousLOneSided c1 c2 (l:ls) = connectsL c1 l && not (connectsL c1 (head ls)) && connectsL c2 (last ls) && not (connectsL c2 (last (init ls)))

correctConnectionVariousL :: City -> City -> [Link] -> Bool
correctConnectionVariousL c1 c2 ls = correctConnectionVariousLOneSided c1 c2 ls || correctConnectionVariousLOneSided c2 c1 ls

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT c1 c2 (Tun ls)
  | length ls == 1 = linksL c1 c2 (head ls)
  | length ls == 2 = correctConnectionTwoL c1 c2 ls || correctConnectionTwoL c2 c1 ls
  | otherwise = correctConnectionVariousL c1 c2 ls || correctConnectionVariousL c2 c1 ls

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun ls) = link `elem` ls

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun []) = 0
delayT (Tun (l:ls)) = delayL l + delayT (Tun ls)

--------------------------------

pA = newP 0 0

pB = newP 3 4

pC = newP 7 7

pD = newP 1 7

cA = newC "A" pA

cB = newC "B" pB

cC = newC "C" pC

cD = newC "D" pD


qA = newQ "A" 10 1.0

qB = newQ "B" 15 0.5

qC = newQ "C" 5 0.25

lAB = newL cA cB qA

lBC = newL cB cC qB

lCD = newL cC cD qA

tAC = newT [lAB,lBC]

tT = newT [lAB,lBC] == Tun [lAB,lBC]



tC = [

   not (correctConnectionTwoL cA cB [lAB,lBC]),
   correctConnectionTwoL cA cC [lAB, lBC],

   not (correctConnectionVariousL cA cC [lAB, lBC, lAB]),
   correctConnectionVariousL cA cD [lAB, lBC, lCD],

   testF (correctConnectionVariousL cA cC [lAB, lBC]),

   True]
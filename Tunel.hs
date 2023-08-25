module Tunel (Tunel, newT, connectsT, usesT, delayT )
   where

import ErrorCatching

import Point
import City
import Quality
import Link

data Tunel = Tun [Link] deriving (Eq)

instance Show Tunel where
  show (Tun links) = 
    show links 


newT :: [Link] -> Tunel
newT ls = Tun ls


isAFirstEnd :: City -> [Link] -> Bool
isAFirstEnd c (l:ls) = connectsL c l && not (connectsL c (head ls))

areEndCitiesTwoL :: City -> City -> [Link] -> Bool
areEndCitiesTwoL c1 c2 twoLs
   | connectsL c1 (head twoLs) =  isAFirstEnd c1 twoLs && isAFirstEnd c2 (reverse twoLs)
   | otherwise = isAFirstEnd c2 twoLs && isAFirstEnd c1 (reverse twoLs)

isALastEnd :: City -> [Link] -> Bool
isALastEnd c ls = connectsL c (last ls) && not (connectsL c (last (init ls)))

areEndCitiesManyL :: City -> City -> [Link] -> Bool
areEndCitiesManyL c1 c2 ls
   | connectsL c1 (head ls) = isAFirstEnd c1 ls && isALastEnd c2 ls
   | otherwise = isAFirstEnd c2 ls && isALastEnd c1 ls

connectsT :: City -> City -> Tunel -> Bool
connectsT c1 c2 (Tun ls)
  | length ls == 1 = linksL c1 c2 (head ls)
  | length ls == 2 = areEndCitiesTwoL c1 c2 ls
  | otherwise = areEndCitiesManyL c1 c2 ls


usesT :: Link -> Tunel -> Bool
usesT link (Tun ls) = link `elem` ls


delayT :: Tunel -> Float
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

   not (areEndCitiesTwoL cA cB [lAB,lBC]),
   areEndCitiesTwoL cA cC [lAB, lBC],

   not (areEndCitiesManyL cA cC [lAB, lBC, lAB]),
   areEndCitiesManyL cA cD [lAB, lBC, lCD],

   

   True]
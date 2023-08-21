module Tunel (Tunel, newT, connectsT, usesT, delayT )
   where

import ErrorCatching

import Point
import City
import Quality
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT links = Tun links


correctConnectionTwoLOneSided :: City -> City -> [Link] -> Bool
correctConnectionTwoLOneSided city1 city2 [l1, l2] = if connectsL city1 l1 && not (connectsL city1 l2) then True else False

correctConnectionTwoL :: City -> City -> [Link] -> Bool
correctConnectionTwoL city1 city2 twoLinks = if correctConnectionTwoLOneSided city1 city2 twoLinks == True && correctConnectionTwoLOneSided city2 city1 (reverse twoLinks) == True then True else False

correctConnectionVariousLOneSided :: City -> City -> [Link] -> Bool
correctConnectionVariousLOneSided city1 city2 (l:ls) = if connectsL city1 l && not (connectsL city1 (head ls)) && connectsL city2 (last ls) && not (connectsL city2 (last (init ls))) then True else False

correctConnectionVariousL :: City -> City -> [Link] -> Bool
correctConnectionVariousL city1 city2 links = if correctConnectionVariousLOneSided city1 city2 links == True || correctConnectionVariousLOneSided city2 city1 links == True then True else False

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun links)
  | length links == 1 = linksL city1 city2 (head links)
  | length links == 2 = if correctConnectionTwoL city1 city2 links == True || correctConnectionTwoL city2 city1 links == True then True else False
  | otherwise = if correctConnectionVariousL city1 city2 links == True || correctConnectionVariousL city2 city1 links == True then True else False 

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = link `elem` links

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
   
   correctConnectionTwoL cA cB [lAB,lBC] ==  False, 
   correctConnectionTwoL cA cC [lAB, lBC] == True,
   
   correctConnectionVariousL cA cC [lAB, lBC, lAB] == False,
   correctConnectionVariousL cA cD [lAB, lBC, lCD] == True,
    
   testF (correctConnectionVariousL cA cC [lAB, lBC]) == True,
   
   True]
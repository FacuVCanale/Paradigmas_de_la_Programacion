module Tunel (Tunel, newT, connectsT, usesT, delayT )
   where

import Point
import City
import Quality
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT links = Tun links

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun (l:ls)) | null ls = linksL city1 city2 l
                                   | length ls == 1 = if connectsL city1 l && connectsL city2 (last ls) then True else False
                                   | otherwise = if connectsL city1 l && not (connectsL city1 (head ls)) && connectsL city2 (last ls) && not (connectsL city2 (last (init ls))) then True else False

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = link `elem` links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun []) = 0
delayT (Tun (l:ls)) = delayL l + delayT (Tun ls)

--------------------------------

pA = newP 0 0

pB = newP 3 4

pC = newP 7 7

cA = newC "A" pA

cB = newC "B" pB

cC = newC "C" pC

qA = newQ "A" 10 1.0

qB = newQ "B" 15 0.5

qC = newQ "C" 5 0.25

lAB = newL cA cB qA 

lBC = newL cB cC qB

tAC = newT [lAB,lBC]

tT = newT [lAB,lBC] == Tun [lAB,lBC]

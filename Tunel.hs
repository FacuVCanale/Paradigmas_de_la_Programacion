module Tunel (Tunel, newT, connectsT, usesT, delayT )
   where


import Link
import City


data Tunel = Tun [Link] deriving (Eq, Show)

checkRepeatedLinks :: [Link] -> Bool
checkRepeatedLinks [] = False
checkRepeatedLinks (l:ls) = l `elem` ls || checkRepeatedLinks ls

newT :: [Link] -> Tunel
newT links | checkRepeatedLinks == True = error "There are repeated links."
           | otherwise = Tun links
-- CHEQUEAR QUE EFECTIVAMENTE SEA UN TUNEL

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun []) = False 
connectsT city1 city2 (Tun (l:ls)) = linksL city1 city2 l || connectsT city1 city2 (Tun ls)

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = link `elem` links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT tunel = doubleDelayT `div` 2

doubleDelayT :: Tunel -> Float
doubleDelayT (Tun []) = 0
doubleDelayT (Tun (l:ls)) = delayL l  + doubleDelayT (Tun ls)
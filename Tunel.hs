{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use newtype instead of data" #-}
{-# HLINT ignore "Eta reduce" #-}
{-# HLINT ignore "Redundant bracket" #-}


module Tunel (Tunel(Tun), newT, connectsT, usesT, delayT )
   where


import Link
import City


data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT links = Tun links

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun []) = False 
connectsT city1 city2 (Tun (l:ls)) = linksL city1 city2 l || connectsT city1 city2 (Tun ls)

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT (Lin city1 city2 quality) (Tun links) = (Lin city1 city2 quality) `elem` links || (Lin city2 city1 quality) `elem` links 

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun []) = 0
delayT (Tun (l:ls)) = delayL l + delayT (Tun ls)
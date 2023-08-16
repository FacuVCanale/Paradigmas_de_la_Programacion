{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Redundant if" #-}
module Tunel (Tunel, newT, connectsT, usesT, delayT )
   where


import Link
import City

-- cuando damos el ejemplo de como crear el tunel, hay que poner que se haga en orden alfababetico

data Tunel = Tun [Link] deriving (Eq, Show)

--checkRepeatedLinks :: [Link] -> Bool
--checkRepeatedLinks [] = False
--checkRepeatedLinks (l:ls) = l `elem` ls || checkRepeatedLinks ls

newT :: [Link] -> Tunel
-- Chequear que el orden de la lista de links sea coherente (A->B->C). SI no se puede gg vemos q hacemos
--newT links | checkRepeatedLinks links == True = error "There are repeated links."
--           | otherwise = Tun links
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
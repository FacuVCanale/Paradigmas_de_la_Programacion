module Tunel (Tunel, newT, connectsT, usesT, delayT )
   where


import Link
import City

-- cuando damos el ejemplo de como crear el tunel, hay que poner que se haga en orden alfababetico

data Tunel = Tun [Link] deriving (Eq, Show)

checkRepeatedLinks :: [Link] -> Bool
checkRepeatedLinks [] = False
checkRepeatedLinks (l:ls) = l `elem` ls || checkRepeatedLinks ls

newT :: [Link] -> Tunel 
-- Chequear que el orden de la lista de links sea coherente (A->B->C). SI no se puede gg vemos q hacemos
newT links | checkRepeatedLinks links == True = error "There are repeated links."
           | otherwise = Tun links

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun links) = connectsL city1 (head links) && connectsL city2 (last links) || connectsL city2 (head links) && connectsL city1 (last links) 

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = link `elem` links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun []) = 0
delayT (Tun (l:ls)) = delayL l + delayT (Tun ls)
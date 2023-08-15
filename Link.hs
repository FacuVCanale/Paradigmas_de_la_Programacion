{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Eta reduce" #-}

module Link (Link, newL, linksL, connectsL, capacityL, delayL )
   where

import City
import Quality

data Link = Lin City City Quality deriving (Eq, Show)

checkNameCityOrder :: City -> City -> Bool
checkNameCityOrder city1 city2 = if nameC city1 <= nameC city2 then True else False

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 quality | checkNameCityOrder city1 city2 == False = error "Links' cities must follow alphabetical order."
                         | otherwise = Lin city1 city2 quality

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL cityA (Lin city1 city2 _) = cityA == city1 || cityA == city2

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL cityA cityB link | cityA == cityB = error "Both cities are the same."
                        | otherwise = connectsL cityA link && connectsL cityB link

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin _ _ quality) = delayQ quality
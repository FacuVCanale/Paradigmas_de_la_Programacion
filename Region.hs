{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Eta reduce" #-}
{-# HLINT ignore "Redundant ==" #-}

module Region (Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where

import City
import Link
import Quality
import Tunel

data Region = Reg [City] [Link] [Tunel]


newR :: Region
newR cities links tunels = Reg cities links tunels

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (R) city | city `elem` cities = Reg cities links tunels
                                      | otherwise = Reg (city:cities) links tunels


-- Qua String Int Float deriving (Eq, Show) 

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) city1 city2 quality | (city1 `elem` cities) == False || (city2 `elem` cities) == False || city1 == city2 = error "That cities aren't in this region"
                                                    | otherwise = Reg cities (Lin city1 city2 quality : links) tunels
----- FALTA VER TUNELS AGREGAR LINK


--tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
--tunelR (Reg cities links tunels)

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg cities links []) city1 city2 = False
connectedR (Reg cities links (t:ts)) city1 city2 = city1 `elem` cities && city2 `elem` cities && city1 /= city2 && connectsT t city1 city2 || connectedR (Reg cities links ts) city1 city2

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg cities [] tunels) city1 city2 = False
linkedR (Reg cities (l:ls) tunels) city1 city2 = linksL city1 city2 l || linkedR (Reg cities ls tunels) city1 city2

-- delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora

--availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades

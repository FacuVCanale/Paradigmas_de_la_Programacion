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

checkCorrectFormatCity :: Region -> City -> City -> Bool
checkCorrectFormatCity (Reg cities links tunels) city1 city2  = not (city1 `notElem` cities || city2 `notElem` cities || city1 == city2)

newR :: Region
-- Chequear que todos los links tengas estas cities, y que todos los tunes tengan estos links
newR cities links tunels = Reg cities links tunels

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) city | city `elem` cities = Reg cities links tunels
                                      | otherwise = Reg (city:cities) links tunels
-- Qua String Int Float deriving (Eq, Show) 

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) city1 city2 quality | checkCorrectFormatCity (Reg cities links tunels) city1 city2 == False = error "Cities aren't in the region"
                                                    | otherwise = Reg cities (newL city1 city2 quality : links) tunels

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg cities links tunels) list_cities| lenght list_cities /= 2 ||  checkCorrectFormatCity (Reg cities links tunels) (head list_cities) (last list_cities) = error "Can only connect 2 cities."
                                -- | otherwise =  : tunels


connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg cities links (t:ts)) city1 city2 | checkCorrectFormatCity (Reg cities links tunels) city1 city2 == False = error "Cities provided are not valid."
                                                 | otherwise = connectsT t city1 city2 || connectedR (Reg cities links ts) city1 city2

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ [] _) city1 city2 = False
linkedR (Reg cities (l:ls) tunels) city1 city2 = linksL city1 city2 l || linkedR (Reg cities ls tunels) city1 city2

connectionR :: Region -> City -> City -> Tunel
connectionR (Reg cities links []) city1 city2 = error "There are no possible tunels in this region."
connectionR (Reg cities links (t:ts)) city1 city2 | checkCorrectFormatCity city1 city2 == False = error "Cities provided are not valid"
                                                  | connectsT city1 city2 t == True = t
                                                  | otherwise = connectionR (Reg cities links ts) 

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
-- Chequeo de ciudades en Region 
delayR region city1 city2 | checkCorrectFormatCity region city1 city2 == False = error "Cities provided are not valid."
                          | otherwise = delayT (connectionR region city1 city2)

getLinkR :: Region -> City -> City -> Link
getLinkR (Reg _ [] _) city1 city2 = error "There are no possible links in this region."
getLinkR (Reg cities (l:ls) tunels) city1 city2 | checkCorrectFormatCity city1 city2 == False = error "Cities provided are not valid."
                                                | linksL city1 city2 l == True = l
                                                | otherwise = getlinkR (Reg cities ls tunels) 

usesOfLinkR :: Region -> Link -> Int
usesOfLinkR (Reg cities links []) link = 0
usesOfLinkR (Reg cities links (t:ts)) link | usesT link t == True = 1 + usesOfLinkR (Reg cities links ts)
                                           | otherwise = usesOfLinkR (Reg cities links ts)

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
delayR region city1 city2 | checkCorrectFormatCity region city1 city2 == False = error "Cities provided are not valid."
                          | otherwise = capacityL link - usesOfLinkR region link 
                                                where link = getLinkR region city1 city2




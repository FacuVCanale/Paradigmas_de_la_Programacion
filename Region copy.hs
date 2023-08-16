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
-- Chequear que todos los links tengas estas cities, y que todos los tunes tengan estos links PERO NO ACÁ, EMPIEZA VACÍO
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) city | city `elem` cities = error "This city is already in this region."
                                      | otherwise = Reg (city:cities) links tunels

doesAnyConnectL :: City -> City -> [Link] -> Bool
doesAnyConnectL _ _ [] = False
doesAnyConnectL city1 city2 (l:ls) = connectsL city1 city2 l || doesAnyConnectL city1 city2 ls

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) city1 city2 quality | checkCorrectFormatCity (Reg cities links tunels) city1 city2 == False = error "Cities aren't in the region"
                                                    | doesAnyConnectL city1 city2 links == True = error "A link already exists."
                                                    | otherwise = Reg cities (newL city1 city2 quality : links) tunels







filterConnections :: (Link -> City -> Bool) -> City -> [City] -> [City]
filterConnections _ _ [] = []
filterConnections f x (l:ls)
    | f l x     = l : filterConnections f x ls
    | otherwise = filterConnections f x ls

findCity :: (City -> City -> Link -> Bool) -> City -> City -> [Link] -> City
findCity _ _ y [] = error "Couldn't find a tunnel that connects the given cities."
findCity f x y connections
    | y `elem` connections = y
    | otherwise = findCity f newCity y newConnections
    where
        newCity = head connections
        newConnections = filterConnections f newCity connections

checkPossibleT :: City -> City -> [Link] -> [City] -> Bool
checkPossibleT _ _ [] _ = False
checkPossibleT city1 city2 links cities = findCity linksL city1 city2 links `elem` filterConnections connectsL city2 cities




createT :: City -> City -> [Link] -> [City] -> Tunel
createT city1 city2 links cities | checkPossibleT city1 city2 links cities == False = error "You cannot create this tunnel"
                                 | otherwise = newT []








{- 

isThereAT :: City -> City -> [Tunel] -> Bool
isThereAT _ _ [] = False
isThereAT city1 city2 (t:ts) = connectsT city1 city2 t || isThereAT city1 city2 ts

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg cities links tunels) cs | length cs /= 2 || checkCorrectFormatCity (Reg cities links tunels) (head cs) (last cs) = error "Cities provided are not valid."
                                    | isThereAT (head cs) (last cs) tunels == True = error "This tunel already exists."
                                    | otherwise = (Reg cities links ( createT (head cs) (last cs) links : tunels))
-- el check possible hagamoslo adentro de createT

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg cities links (t:ts)) city1 city2 | checkCorrectFormatCity (Reg cities links tunels) city1 city2 == False = error "Cities provided are not valid."
                                                 | otherwise = connectsT t city1 city2 || connectedR (Reg cities links ts) city1 city2

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ [] _) city1 city2 = False
linkedR (Reg cities (l:ls) tunels) city1 city2 = linksL city1 city2 l || linkedR (Reg cities ls tunels) city1 city2

connectionR :: Region -> City -> City -> Tunel
connectionR (Reg cities links []) city1 city2 = error "There are no possible tunels in this region."
connectionR (Reg cities links (t:ts)) city1 city2 | checkCorrectFormatCity city1 city2 == False = error "Cities provided are not valid."
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



 -}
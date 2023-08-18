{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Eta reduce" #-}
{-# HLINT ignore "Redundant ==" #-}

module Region (Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR )
   where

import City
import Link
import Quality
import Tunel

data Region = Reg [City] [Link] [Tunel] deriving (Show)

checkFormatCity :: Region -> City -> City -> Bool
checkFormatCity (Reg cities links tunels) city1 city2  = city1 `elem` cities && city2 `elem` cities && city1 /= city2

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) city | city `elem` cities = error "This city is already in this region."
                                      | otherwise = Reg (city:cities) links tunels

doesAnyConnectL :: City -> City -> [Link] -> Bool
doesAnyConnectL _ _ [] = False
doesAnyConnectL city1 city2 (l:ls) = linksL city1 city2 l || doesAnyConnectL city1 city2 ls

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR region@(Reg cities links tunels) city1 city2 quality | checkFormatCity region city1 city2 == False = error "Cities aren't in the region"
                                                           | doesAnyConnectL city1 city2 links == True = error "A link already exists."
                                                           | otherwise = Reg cities (newL city1 city2 quality : links) tunels

addLinkForT :: City -> City -> [Link] -> [Link] -> [Link]
addLinkForT city1 city2 [] foundL = error "Necesary links does not exist."
addLinkForT city1 city2 (l:ls) foundL = if linksL city1 city2 l == True then l : foundL else addLinkForT city1 city2 ls foundL

createT :: Region -> [City] -> [Link] -> [Link]
createT region@(Reg _ links _) (c1 : ( c2 : cs)) foundL | null cs == True = addLinkForT c1 c2 links foundL
                                                        | otherwise = createT region (c2 : cs) (addLinkForT c1 c2 links foundL)

isThereAT :: City -> City -> [Tunel] -> Bool
isThereAT _ _ [] = False
isThereAT city1 city2 (t:ts) = connectsT city1 city2 t || isThereAT city1 city2 ts

{- 
availibleCapacityL :: Foldable t => t Link -> Bool
availibleCapacityL links 
   | null links == True = False
   | foldr (\link acc -> capacityL link > 0 && acc) True links = True
   | otherwise = False
 -}
{- 
availibleCapacityL :: Foldable t => t Link -> Region -> 
availibleCapacityL links region = all (\link -> capacityL link - usesOfLinkR region link) links 
 -}
{- tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR region@(Reg cities links tunels) cs | availibleCapacityL links || checkCorrectFormatCity (Reg cities links tunels) (head cs) (last cs) || length cs > length links + 1 = error "Cities provided are not valid."
                                    | isThereAT (head cs) (last cs) tunels == True = error "This tunel already exists."
                                    | otherwise = Reg cities links (newT (region cs []) : tunels) -}
availableCapacityLs :: Region -> [Link] -> Bool
--availableCapacityLs region= foldr (\ l -> (&&) ((capacityL l - usesOfLinkR region l) > 0)) True
--availableCapacityLs region= foldr (\ l -> (&&) ((>) (capacityL l - usesOfLinkR region l) 0)) True
--availableCapacityLs region= foldr (\ l -> (&&) ( flip (>) 0 (capacityL l - usesOfLinkR region l))) True
--availableCapacityLs region= foldr (\ l -> (&&) ( flip (>) 0 ((-) (capacityL l) (usesOfLinkR region l) ) ) ) True
availableCapacityLs region = foldr (\l -> (&&) ((> 0) ((-) (capacityL l) (usesOfLinkR region l)) )) True



tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR region@(Reg cities links tunels) cs | checkFormatCity region (head cs) (last cs) == False || length cs > length links + 1 = error "Cities provided are not valid."
                                           | isThereAT (head cs) (last cs) tunels == True = error "This tunel already exists."
                                           | availableCapacityLs region linksForT == False = error "Some link has no available capacity"
                                           | otherwise = Reg cities links (newT linksForT : tunels)
                                             where linksForT = createT (Reg cities links tunels) cs []

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg cities links (t:ts)) city1 city2 | checkFormatCity (Reg cities links (t:ts)) city1 city2 == False = error "Cities provided are not valid."
                                                 | null ts = if connectsT city1 city2 t == True then True else False
                                                 | otherwise = connectsT city1 city2 t || connectedR (Reg cities links ts) city1 city2

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ [] _) city1 city2 = False
linkedR (Reg cities (l:ls) tunels) city1 city2 = linksL city1 city2 l || linkedR (Reg cities ls tunels) city1 city2

connectionR :: Region -> City -> City -> Tunel
connectionR (Reg cities links []) city1 city2 = error "There are no possible tunels in this region."
connectionR (Reg cities links (t:ts)) city1 city2 | checkFormatCity (Reg cities links (t:ts)) city1 city2 == False = error "Cities provided are not valid."
                                                  | connectsT city1 city2 t == True = t
                                                  | otherwise = connectionR (Reg cities links ts) city1 city2

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR region city1 city2 | checkFormatCity region city1 city2 == False = error "Cities provided are not valid."
                          | otherwise = delayT (connectionR region city1 city2)

getLinkR :: Region -> City -> City -> Link
getLinkR (Reg _ [] _) city1 city2 = error "There are no possible links in this region."
getLinkR (Reg cities (l:ls) tunels) city1 city2 | checkFormatCity (Reg cities (l:ls) tunels) city1 city2 == False = error "Cities provided are not valid."
                                                | linksL city1 city2 l == True = l
                                                | otherwise = getLinkR (Reg cities ls tunels) city1 city2

usesOfLinkR :: Region -> Link -> Int
usesOfLinkR (Reg cities links []) link = 0
usesOfLinkR (Reg cities links (t:ts)) link | usesT link t == True = 1 + usesOfLinkR (Reg cities links ts) link
                                           | otherwise = usesOfLinkR (Reg cities links ts) link

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR region city1 city2 | checkFormatCity region city1 city2 == False = error "Cities provided are not valid."
                                         | otherwise = capacityL link - usesOfLinkR region link
                                                where link = getLinkR region city1 city2




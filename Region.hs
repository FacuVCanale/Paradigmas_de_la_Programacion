module Region (Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR)
   where

import Point
import City
import Link
import Quality
import Tunel

data Region = Reg [City] [Link] [Tunel] deriving (Eq, Show)

errorCities = error "Cities provided are not valid."

newR :: Region
newR = Reg [] [] []

checkValidCity :: City -> [City] -> Bool
checkValidCity _ [] = True
checkValidCity city (c:cs)
  | distanceC city c /= 0 && nameC city /= nameC c && checkValidCity city cs = True
  | otherwise = False

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) city 
  | city `elem` cities = error "This city is already in this region." -- Tenemos que chequear también que no tenga la misma coord o el mismo nombre, puede no ser la misma ciudad y que no deba ser válida.
  | checkValidCity city cities == False = errorCities
  | otherwise = Reg (city:cities) links tunels


doesAnyConnectL :: City -> City -> [Link] -> Bool
doesAnyConnectL _ _ [] = False
doesAnyConnectL city1 city2 (l:ls) = linksL city1 city2 l || doesAnyConnectL city1 city2 ls

areValidCities :: Region -> City -> City -> Bool
areValidCities (Reg cities links tunels) city1 city2  = city1 `elem` cities && city2 `elem` cities && city1 /= city2

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR region@(Reg cities links tunels) city1 city2 quality 
  | areValidCities region city1 city2 == False = errorCities
  | doesAnyConnectL city1 city2 links == True = error "A link already exists."
  | otherwise = Reg cities (newL city1 city2 quality : links) tunels

addLinkForT :: City -> City -> [Link] -> [Link] -> [Link]
addLinkForT city1 city2 [] foundL = error "Necesary links do not exist."
addLinkForT city1 city2 (l:ls) foundL 
  | linksL city1 city2 l == True = l : foundL
  | otherwise = addLinkForT city1 city2 ls foundL

linksForT :: Region -> [City] -> [Link] -> [Link]
linksForT region@(Reg _ links _) (c1 : ( c2 : cs)) foundL 
  | null cs == True = addLinkForT c1 c2 links foundL
  | otherwise = linksForT region (c2 : cs) (addLinkForT c1 c2 links foundL)

isThereAT :: City -> City -> [Tunel] -> Bool
isThereAT _ _ [] = False
isThereAT city1 city2 (t:ts) = connectsT city1 city2 t || isThereAT city1 city2 ts

availableCapacityLs :: Region -> [Link] -> Bool
--availableCapacityLs region= foldr (\ l -> (&&) ((capacityL l - usesOfLinkR region l) > 0)) True
--availableCapacityLs region= foldr (\ l -> (&&) ((>) (capacityL l - usesOfLinkR region l) 0)) True
--availableCapacityLs region= foldr (\ l -> (&&) ( flip (>) 0 (capacityL l - usesOfLinkR region l))) True
--availableCapacityLs region= foldr (\ l -> (&&) ( flip (>) 0 ((-) (capacityL l) (usesOfLinkR region l) ) ) ) True
availableCapacityLs region = foldr (\l -> (&&) ((> 0) ((-) (capacityL l) (usesOfLinkR region l)) )) True
--  all (\l -> (capacityL l - usesOfLinkR region l) > 0) links

checkAlphabeticalOrderCities :: City -> City -> Bool
checkAlphabeticalOrderCities city1 city2 = if nameC city1 <= nameC city2 then True else False

checkRepeatedElem :: Eq a => [a] -> Bool
checkRepeatedElem [] = False
checkRepeatedElem (e:es) = e `elem` es || checkRepeatedElem es

checkForTunelR :: Region -> [City] -> Bool
checkForTunelR region@(Reg cities links tunels) cs
  | areValidCities region (head cs) (last cs) == False || length cs > length links + 1 || checkRepeatedElem cs == True = errorCities
  | isThereAT (head cs) (last cs) tunels == True = error "This tunel already exists."
  | checkAlphabeticalOrderCities (head cs) (last cs) == False = error "Connected cities must follow alphabetical order (Tip: Use reverse to invert the list)"
  | availableCapacityLs region (linksForT region cs []) == False = error "Some link has no available capacity"
  | otherwise = True


tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR region@(Reg cities links tunels) cs 
  | checkForTunelR region cs == True = Reg cities links (newT (linksForT region cs []): tunels)
  | otherwise = error "Tunel cannot be created."

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR region@(Reg cities links (t:ts)) city1 city2 
  | areValidCities region city1 city2 == False = errorCities
  | null ts = if connectsT city1 city2 t == True then True else False
  | otherwise = connectsT city1 city2 t || connectedR (Reg cities links ts) city1 city2

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ [] _) city1 city2 = False
linkedR (Reg cities (l:ls) tunels) city1 city2 = linksL city1 city2 l || linkedR (Reg cities ls tunels) city1 city2

connectionR :: Region -> City -> City -> Tunel
connectionR (Reg cities links []) city1 city2 = error "There are no possible tunels in this region."
connectionR (Reg cities links (t:ts)) city1 city2
  | connectsT city1 city2 t == True = t
  | otherwise = connectionR (Reg cities links ts) city1 city2

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR region city1 city2 
  | areValidCities region city1 city2 == False = errorCities
  | otherwise = delayT (connectionR region city1 city2)

getLinkR :: Region -> City -> City -> Link
getLinkR (Reg _ [] _) city1 city2 = error "There are no possible links in this region."
getLinkR region@(Reg cities (l:ls) tunels) city1 city2 
  | areValidCities region city1 city2 == False = errorCities
  | linksL city1 city2 l == True = l
  | otherwise = getLinkR (Reg cities ls tunels) city1 city2

usesOfLinkR :: Region -> Link -> Int
usesOfLinkR (Reg cities links []) link = 0
usesOfLinkR (Reg cities links (t:ts)) link 
  | usesT link t == True = 1 + usesOfLinkR (Reg cities links ts) link
  | otherwise = usesOfLinkR (Reg cities links ts) link

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR region city1 city2 
  | areValidCities region city1 city2 == False = errorCities
  | otherwise = capacityL link - usesOfLinkR region link
      where link = getLinkR region city1 city2


---------------------------------


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

tAC = newT [lBC, lAB]

rA00 = newR

rA01 = foundR rA00 cA 

rA02 = foundR rA01 cB

rA03 = foundR rA02 cC

rA04 = linkR rA03 cA cB qA

rA05 = linkR rA04 cB cC qB

rA = tunelR rA05 [cA,cB,cC] 


tR = [
  
    newR == Reg [] [] [],
  
    rA01 == Reg [cA] [] [],
  
    rA == Reg [cC, cB, cA] [lBC, lAB] [tAC],
  
    connectionR rA cA cC == tAC,

    getLinkR rA cA cB == lAB,

    usesOfLinkR rA lAB == 1,
  
    True
        ]

allTestsR = and tR

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

isValidNewC :: City -> [City] -> Bool
isValidNewC _ [] = True
isValidNewC c1 (c2:cs) = distanceC c1 c2 /= 0 && nameC c1 /= nameC c2 && isValidNewC c1 cs

foundR :: Region -> City -> Region
foundR (Reg cs ls ts) c
  | c `elem` cs = error "This city is already in this region."
  | not (isValidNewC c cs) = errorCities
  | otherwise = Reg (c:cs) ls ts


doesAnyConnectL :: City -> City -> [Link] -> Bool
doesAnyConnectL _ _ [] = False
doesAnyConnectL c1 c2 (l:ls) = linksL c1 c2 l || doesAnyConnectL c1 c2 ls

areValidCities :: Region -> City -> City -> Bool
areValidCities (Reg cs ls ts) c1 c2  = c1 `elem` cs && c2 `elem` cs && c1 /= c2

linkR :: Region -> City -> City -> Quality -> Region
linkR r@(Reg cs ls ts) c1 c2 q
  | not (areValidCities r c1 c2) = errorCities
  | doesAnyConnectL c1 c2 ls = error "A link already exists."
  | otherwise = Reg cs (newL c1 c2 q : ls) ts


addLinkForT :: City -> City -> [Link] -> [Link] -> [Link]
addLinkForT c1 c2 [] foundL = error "Necesary links do not exist."
addLinkForT c1 c2 (l:ls) foundL
  | linksL c1 c2 l = l : foundL
  | otherwise = addLinkForT c1 c2 ls foundL

linksForT :: Region -> [City] -> [Link] -> [Link]
linksForT r@(Reg _ ls _) (c1 : ( c2 : cs)) foundL
  | null cs = addLinkForT c1 c2 ls foundL
  | otherwise = linksForT r (c2 : cs) (addLinkForT c1 c2 ls foundL)

isThereAT :: City -> City -> [Tunel] -> Bool
isThereAT _ _ [] = False
isThereAT c1 c2 (t:ts) = connectsT c1 c2 t || isThereAT c1 c2 ts

usesOfLinkR :: Region -> Link -> Int
usesOfLinkR (Reg _ _ []) _ = 0
usesOfLinkR (Reg cs ls (t:ts)) l
  | usesT l t = 1 + usesOfLinkR (Reg cs ls ts) l
  | otherwise = usesOfLinkR (Reg cs ls ts) l

availableCapacityL :: Region -> Link -> Int
availableCapacityL r l = capacityL l - usesOfLinkR r l

hasCapacityL :: Region -> Link -> Bool
hasCapacityL r l = availableCapacityL r l > 0

haveCapacityLs :: Region -> [Link] -> Bool
haveCapacityLs r = foldr ((&&) . hasCapacityL r) True

areCitiesOrdered :: City -> City -> Bool
areCitiesOrdered c1 c2 = nameC c1 <= nameC c2

isRepeatedElem :: Eq a => [a] -> Bool
isRepeatedElem [] = False
isRepeatedElem (e:es) = e `elem` es || isRepeatedElem es

checkForTunelR :: Region -> [City] -> Bool
checkForTunelR r@(Reg cs ls ts) cs2
  | not (areValidCities r (head cs2) (last cs2)) = errorCities
  | length cs2 > length ls + 1 = errorCities
  | isRepeatedElem cs2 = errorCities
  | isThereAT (head cs2) (last cs2) ts = error "This tunel already exists."
  | not (areCitiesOrdered (head cs2) (last cs2)) = error "Connected cities must follow alphabetical order (Tip: Use reverse to invert the list)"
  | not (haveCapacityLs r ls2) = error "Some link has no available capacity"
  | otherwise = True
      where ls2 = linksForT r cs2 []

tunelR :: Region -> [ City ] -> Region
tunelR r@(Reg cs ls ts) cs2
  | checkForTunelR r cs2 = Reg cs ls (newT ls2: ts)
  | otherwise = error "Tunel cannot be created."
      where ls2 = linksForT r cs2 []


connectedR :: Region -> City -> City -> Bool
connectedR r@(Reg cs ls (t:ts)) c1 c2
  | not (areValidCities r c1 c2) = errorCities
  | null ts = connectsT c1 c2 t
  | otherwise = connectsT c1 c2 t || connectedR (Reg cs ls ts) c1 c2


linkedR :: Region -> City -> City -> Bool
linkedR (Reg _ [] _) _ _ = False
linkedR (Reg cs (l:ls) ts) c1 c2 = linksL c1 c2 l || linkedR (Reg cs ls ts) c1 c2


connectionR :: Region -> City -> City -> Tunel
connectionR (Reg _ _ []) _ _ = error "There are no possible tunels in this region."
connectionR (Reg cs ls (t:ts)) c1 c2
  | connectsT c1 c2 t = t
  | otherwise = connectionR (Reg cs ls ts) c1 c2

delayR :: Region -> City -> City -> Float
delayR r c1 c2
  | not (areValidCities r c1 c2) = errorCities
  | otherwise = delayT (connectionR r c1 c2)


getLinkR :: Region -> City -> City -> Link
getLinkR (Reg _ [] _) _ _ = error "There are no possible links in this region."
getLinkR r@(Reg cs (l:ls) ts) c1 c2
  | not (areValidCities r c1 c2) = errorCities
  | linksL c1 c2 l = l
  | otherwise = getLinkR (Reg cs ls ts) c1 c2

availableCapacityForR :: Region -> City -> City -> Int
availableCapacityForR r c1 c2
  | not (areValidCities r c1 c2) = errorCities
  | otherwise = availableCapacityL r l
      where l = getLinkR r c1 c2


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

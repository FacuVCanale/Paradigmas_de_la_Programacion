module Link (Link, newL, linksL, connectsL, capacityL, delayL )
   where

import Point
import City
import Quality

data Link = Lin City City Quality deriving (Eq, Show)

checkNameCityOrder :: City -> City -> Bool
checkNameCityOrder city1 city2 = if nameC city1 <= nameC city2  then True else False

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 quality 
  | checkNameCityOrder city1 city2 == False = error "Links' cities must follow alphabetical order."
  | city1 == city2 = error "City provided is repeated."
  | distanceC city1 city2 == 0 || nameC city1 == nameC city2 = error "The cities provided have at least one common attribute."
  | otherwise = Lin city1 city2 quality

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL cityA (Lin city1 city2 _) = cityA == city1 || cityA == city2

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL cityA cityB link 
  | cityA == cityB = error "City provided is repeated."
  | otherwise = connectsL cityA link && connectsL cityB link

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin city1 city2 quality) = delayQ quality * distanceC city1 city2

---------------------------
pA = newP 0 0

pB = newP 3 4

cA = newC "A" pA

cB = newC "B" pB

qA = newQ "A" 10 1.0

lAB = newL cA cB qA 

tL = [
   
   checkNameCityOrder cB cA == False,
   
   lAB == Lin cA cB qA,
   
   True
      ]

allTestsL = and tL
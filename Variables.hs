module Variables 
where

import Tunel
import Quality
import City
import Point
import Region
import Link

pA = newP 0 0

pB = newP 3 4

pC = newP 7 7

---------
cA = newC "A" pA

cB = newC "B" pB

cC = newC "C" pC

-----------
qA = newQ "A" 10 1.0

qB = newQ "B" 15 0.5

qC = newQ "C" 5 0.25
-----------

lAB = newL cA cB qA 

lBC = newL cB cC qB

-------

tAC = newT [lAB,lBC]

tBC = newT [lBC]

-----

rA00 = newR

rA01 = foundR rA00 cA 

rA02 = foundR rA01 cB

rA03 = foundR rA02 cC

rA04 = linkR rA03 cA cB qC 

rA05 = linkR rA04 cB cC qC

rA = tunelR rA05 [cA,cB,cC] 

-- 
rB00 = newR

rB01 = foundR rA00 cA 

rB02 = foundR rA01 cC

rB03 = foundR rA02 cB

rB04 = linkR rA03 cA cB qC 

rB05 = linkR rA04 cB cC qC

rB = tunelR rA05 [cC,cB,cA] 

t = [rA == rB]
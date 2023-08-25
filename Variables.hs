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

pD = newP 9 1

pE = newP 1 2

---------

cA = newC "A" pA

cB = newC "B" pB

cC = newC "C" pC

cD = newC "D" pD

cE = newC "E" pE

-----------

qA = newQ "A" 10 1.0

qB = newQ "B" 15 0.5

qC = newQ "C" 5 0.25

qN = newQ "N" 1 0.2

-----------

lAB = newL cA cB qA 

lBC = newL cB cC qB

lCD = newL cC cD qN

lDE = newL cD cE qN

-------

tAC = newT [lAB,lBC]

tBC = newT [lBC]

tAD = newT [lAB,lBC,lCD]

tCE = newT [lCD, lDE]

-----

rA00 = newR

rA01 = foundR rA00 cA 

rA02 = foundR rA01 cB

rA03 = foundR rA02 cC

rA04 = linkR rA03 cA cB qA

rA05 = linkR rA04 cB cC qB

rA = tunelR rA05 [cA,cB,cC] 

rA06 = tunelR rA [cA, cB]

rA07 = tunelR rA06 [cB, cC]



rB00 = newR

rB01 = foundR rB00 cA 

rB02 = foundR rB01 cB

rB03 = foundR rB02 cC

rB04 = foundR rB03 cD

rB05 = foundR rB04 cE

rB06 = linkR rB05 cA cB qN

rB07 = linkR rB06 cB cC qN

rB08 = linkR rB07 cC cD qN

rB09 = tunelR rB08 [cA,cB,cC,cD]

rB = tunelR rB09 [cA, cB]

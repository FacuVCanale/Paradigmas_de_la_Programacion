import Point
import City
import Quality
import Link
import Tunel
import Region
import ErrorCatching
import Variables

-- HAY QUE PONER POR QUÉ FALLA CADA UNA


testPoint = [

  difP pA pB == 5,

  True
    ]



testCity = [

  nameC cA == "A",

  distanceC cA cB == difP pA pB,

  testF (newC "" pC),

  True
    ]


testQuality = [

  qA /= qB,

  capacityQ qB == 15,

  delayQ qA == 1.0,

  testF (newQ "" 3 0.1),
  testF (newQ "G" (-3) 0.1),
  testF (newQ "" 3 (-0.1)),

  True
    ]


testLinks = [

  connectsL cA lAB,
  not (connectsL cC lAB),

  linksL cB cC lBC,
  not (linksL cA cB lBC),

  capacityL lBC == 15,

  delayL lBC == 0.5 * 5,

  testF (newL cB cA qA),
  testF (newL cB cB qA),

  testF (linksL cA cA lAB),



  True
    ]


testTunel = [

  connectsT cA cC tAC,
  not (connectsT cB cC tAC),
  not (connectsT cA cB tAC),

  not (usesT lAB tBC),
  usesT lAB tAC,

  delayT tAC == delayL lAB + delayL lBC,

  True
    ]


testRegion = [

  connectedR rA cA cC,

  linkedR rA cA cB,

  delayR rA cA cC == delayL lAB + delayL lBC,

  availableCapacityForR rA cA cB == capacityL lAB - 1,

  testF (foundR rA01 cA),

  testF (linkR rA02 cA cC qA),

  testF (linkR rA02 cA cA qA),

  testF (linkR rA04 cA cB qC),

  testF (tunelR rA04 [cA, cB, cC]),

  testF (tunelR rA04 [cA, cB, cA]),

  testF (tunelR rA05 [cC, cB, cA]),

  testF (tunelR rB09 [cA, cB, cC, cD] ),

  testF (delayR rA05 cA cC),

  testF (availableCapacityForR rA02 cA cB),

  True
    ]



allTestsPoint = and testPoint
allTestsCity = and testCity
allTestsQuality = and testQuality
allTestsLink = and testLinks
allTestsTunel = and testTunel
allTestsRegion = and testRegion

allTests = and [allTestsPoint, allTestsCity, allTestsQuality, allTestsLink, allTestsTunel, allTestsRegion]
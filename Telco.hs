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

  testF (newC "" pC), -- Throws an error because of empty name

  True
    ]


testQuality = [

  qA /= qB,

  capacityQ qB == 15,

  delayQ qA == 1.0,

  testF (newQ "" 3 0.1), -- Throws error because of empty name
  testF (newQ "G" (-3) 0.1), -- Throws error because of negative capacity (invalid) and for empty name.
  testF (newQ "" 3 (-0.1)),  -- Throws error negative delay (invalid) and for empty name.

  True
    ]


testLinks = [

  connectsL cA lAB,
  not (connectsL cC lAB),

  linksL cB cC lBC,
  not (linksL cA cB lBC),

  capacityL lBC == 15,

  delayL lBC == 0.5 * 5,

  testF (newL cB cA qA), -- Throws error because cities are not in the required alphabetical order. 
  testF (newL cB cB qA), -- Throws an error because the same city cB is provided twice, which is not allowed.

  testF (linksL cA cA lAB), -- Throws an error because the linksL function requires two distinct cities, but cA is provided twice here.



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

  testF (foundR rA01 cA), -- Throws error because cA already exists in rA01.

  testF (linkR rA02 cA cC qA), -- Throws error because cC does not exist in rA02.

  testF (linkR rA02 cA cA qA), -- Throws error because cA is repeated.

  testF (linkR rA04 cA cB qC), -- Throws error because link cA-cB already exists.

  testF (tunelR rA04 [cA, cB, cC]), -- Throws error because link cB-cC does not exist.

  testF (tunelR rA04 [cA, cB, cA]), -- Throws error because cA is repeated.

  testF (tunelR rA05 [cC, cB, cA]), -- Throws error because cities not in alphabetical order.

  testF (tunelR rB09 [cA, cB, cC, cD] ), -- Throws error because there's already a tunnel connecting those cities.

  testF (delayR rA05 cA cC), -- Throws error because there are not tunnels connecting the cities given.

  testF (availableCapacityForR rA02 cA cB), -- There are no possible links in this region.

  True
    ]



allTestsPoint = and testPoint
allTestsCity = and testCity
allTestsQuality = and testQuality
allTestsLink = and testLinks
allTestsTunel = and testTunel
allTestsRegion = and testRegion

allTests = and [allTestsPoint, allTestsCity, allTestsQuality, allTestsLink, allTestsTunel, allTestsRegion]
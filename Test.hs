import Point
import City
import Quality
import Link
import Tunel
import Region
import ErrorCatching
import Variables 


testPoint = [
  
  difP pA pB == 5,
   
  True
    ]



testCity = [

  nameC cA == "A",

  distanceC cA cB == difP pA pB,

  testF (newC "" pC) == True,
    
  True 
    ]


testQuality = [
  
  qA /= qB,

  capacityQ qB == 15,

  delayQ qA == 1.0,

  testF (newQ "" 3 0.1) == True,
  testF (newQ "G" (-3) 0.1) == True,
  testF (newQ "" 3 (-0.1)) == True,

  True
    ]


testLinks = [

  connectsL cA lAB == True,
  connectsL cC lAB == False,

  linksL cB cC lBC == True,
  linksL cA cB lBC == False,
   
  capacityL lBC == 15,
   
  delayL lBC == 0.5 * 5,

  testF (newL cB cA qA) == True, 
  testF (newL cB cB qA) == True,

  testF (linksL cA cA lAB) == True,
  


  True
    ]


testTunel = [

  connectsT cA cC tAC == True,
  connectsT cB cC tAC == False,
  connectsT cA cB tAC == False,

  usesT lAB tBC == False,
  usesT lAB tAC == True,

  delayT tAC == delayL lAB + delayL lBC,

  True
    ]


testRegion = [

  connectedR rA cA cC == True,

  linkedR rA cA cB == True,

  delayR rA cA cC == delayL lAB + delayL lBC,

  availableCapacityForR rA cA cB == capacityL lAB - 1,
  
  testF (foundR rA01 cA) == True,

  testF (linkR rA02 cA cC qA) == True,

  testF (linkR rA02 cA cA qA) == True,

  testF (linkR rA04 cA cB qC) == True,

  testF (tunelR rA04 [cA, cB, cC]) == True,

  testF (tunelR rA04 [cA, cB, cA]) == True,

  testF (tunelR rA05 [cC, cB, cA]) == True,

--HACER TESTF DE NO AVAILABLE CAPACITY FOR LINK
  testF (tunelR rB09 [cA,cB,cC,cD] ) == True,

  testF (delayR rA05 cA cC) == True,

  testF (availableCapacityForR rA02 cA cB) == True,

  True
    ]



allTestsPoint = and testPoint
allTestsCity = and testCity
allTestsQuality = and testQuality
allTestsLink = and testLinks
allTestsTunel = and testTunel
allTestsRegion = and testRegion

allTests = and [allTestsPoint, allTestsCity, allTestsQuality, allTestsLink, allTestsTunel, allTestsRegion]
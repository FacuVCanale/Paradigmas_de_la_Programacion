import Point
import City
import Quality
import Link
import Tunel
import Region
import Variables 


testPoint = [
   difP pA pB == 5,
   
   True
   ]

allTestsP = and testPoint



testCity = [
    nameC cA == "A",
    distanceC cA cB == difP pA pB,
    
    True 
    ]

allTestC = and testCity


testQuality = [
    qA /= qB,
    capacityQ qB == 15,
    delayQ qA == 1.0,

    True
    ]

allTestQ = and testQuality


testLinks = [
   connectsL cA lAB == True,
   connectsL cC lAB == False,
   linksL cB cC lBC == True,
   linksL cA cB lBC == False,
   capacityL lBC == 15,
   delayL lBC == 0.5 * 5,

   True
   ]

allTestLink = and testLinks



testTunel = [
    connectsT cA cC tAC == True,
    connectsT cB cC tAC == False,
    connectsT cA cB tAC == False,

    usesT lAB tBC == False,
    usesT lAB tAC == True,

    delayT tAC == delayL lAB + delayL lBC,

    True
  ]

allTestsTunel = and testTunel

-- linkR, connectedR, linkedR, delayR, availableCapacityForR

testRegion = [


    
]
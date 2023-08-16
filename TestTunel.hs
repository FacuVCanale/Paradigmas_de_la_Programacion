{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Redundant ==" #-}

module TestTunel
    where
import Tunel

import TestLink
import TestCity


sameT :: Tunel -> Tunel -> Bool
sameT t1 t2 = t1 == t2


--Tun [Link] deriving (Eq, Show)

tun1 = newT [linkMV, linkMZ, linkZM]

tun1Copy = newT [linkMV, linkMZ, linkZM]

tun1Inversed = newT [linkZM, linkMZ, linkMV]

tun2 = newT [linkMZ, linkZM]

tun3 = newT [linkMV, linkMZ]

tun4 = newT []

sameTTest1 = sameT tun1 tun1Copy

sameTTest2 = sameT tun1 tun1Inversed

sameTTest3= sameT tun1 tun3

sameTTest4= sameT tun1 tun2


connectsTTest1 = connectsT mercedes victoria tun1
connectsTTest2 = connectsT mercedes victoria tun2
connectsTTest3 = connectsT mercedes zarate tun2
connectsTTest4 = connectsT zarate victoria tun3
connectsTTest5 = connectsT mercedes zarate tun4


usesTTest1 = usesT linkMV tun1
usesTTest2 = usesT linkMZ tun3
usesTTest3 = usesT linkZM tun3
usesTTest4 = usesT linkMV tun2
usedTTest5 = usesT linkMV tun4


delayTTest1 = delayT tun1
delayTTest2 = delayT tun2
delayTTest3 = delayT tun3
delayTTest4 = delayT tun4

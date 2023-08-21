module Quality (Quality, newQ, capacityQ, delayQ )
   where


data Quality = Qua String Int Float deriving (Eq, Show) 

checkDataQ :: String -> Int -> Float -> Bool
checkDataQ name capacity delay = if (not (null name)) && capacity > 0 && delay >= 0 then True else False

newQ :: String -> Int -> Float -> Quality
newQ name capacity delay 
  | checkDataQ name capacity delay == False = error "It is not possible to create this quality."
  | otherwise = Qua name capacity delay

capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua _ capacity _) = capacity

delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua _ _ delay) = delay


--------------------------------
qA = newQ "A" 10 1.0

qB = newQ "B" 15 0.5

tQ = [
   
   checkDataQ "" 1 1 == False,
   
   checkDataQ "Mercedes" 10 1.0 == True,
   
   qA == Qua "A" 10 1.0,
   
   True
      ]

allTestQ = and tQ

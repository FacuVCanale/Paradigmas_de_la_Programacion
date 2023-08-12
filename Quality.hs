{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Eta reduce" #-}


module Quality ( Quality (Qua), newQ, capacityQ, delayQ )
   where

data Quality = Qua String Int Float deriving (Eq, Show) 

newQ :: String -> Int -> Float -> Quality
newQ name capacity delay = Qua name capacity delay

capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua name capacity delay) = capacity

delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua name capacity delay) = delay


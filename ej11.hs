partir :: [a] -> [([a], [a])]
partir xs = [splitAt x xs | x <- [0..(length xs)]]

erre :: (a -> b -> b) -> b -> [a] -> b
erre = foldr


partes :: [a] -> [[a]]
-- partes [2] = foldr f vi [2]
-- partes [2] = f 2 (foldr f vi [])
-- partes [2] = f 2 vi
-- partes [2] = [[2], []]                       SE QUE f :: a -> Algo -> [[a]]

-- partes [] = foldr f vi []
-- partes [] = f vi []
-- partes [] = [[]]       --> vi = [[]]

-- f 1 [[2], []] = [[1, 2] [1], [2], []]             devuelve lo que ya tenía, más lo que tenía con el uno


--partes = foldr (\each acc -> [each : x | x <- acc] ++ acc) [[]]
partes = foldr (\each acc -> (++) (g each acc) acc) [[]]
    where g each acc = [each : x | x <- acc]

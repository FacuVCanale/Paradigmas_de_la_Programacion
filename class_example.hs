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



data Stick = Vacio | Stack Stick Int deriving (Eq)

instance Show Stick
        where show s = "| " ++ printS s

printS :: Stick -> [Char]
printS Vacio = ""
printS (Stack s d) = printS s ++ " " ++ show d



push :: Stick -> Int -> Stick
push Vacio n = Stack Vacio n
push s@(Stack stick elem) d | elem > d = Stack s d
                            | otherwise = error "Segui participando"

pop :: Stick -> Stick
pop (Stack s _) = s

top :: Stick -> Int
top (Stack _ n) = n


data Hanoi = Hanoi Stick Stick Stick deriving (Eq)

hanoiIC :: Hanoi -> Hanoi
hanoiIC (Hanoi i c d) = Hanoi (pop i) (push c (top i)) d

initWith :: [Int] -> [Int] -> [Int] -> Hanoi
initWith i c d = Hanoi (stickWith i) (stickWith c) (stickWith d)

stickWith :: [Int] -> Stick
stickWith = foldr (flip push) Vacio

hanoi = initWith [] [1,3] [2]

instance Show Hanoi
        where show (Hanoi i c d) = " I: " ++ show i ++ "\n C: " ++ show c ++ "\n D: " ++ show d

t = [ push Vacio 3 == Stack Vacio 3,
      pop (push Vacio 3) == Vacio,
      top (push Vacio 3) == 3,
      stickWith [2, 3] == Stack (Stack Vacio 3) 2,
      True ]

allTest = and t



datss :: Int -> Bool -> Bool
datss n b | n == 0 && not b = True


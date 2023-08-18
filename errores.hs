import Control.Exception
import System.IO.Unsafe

fallo :: (a -> IO b) -> a -> IO Bool
fallo function action = do
    result <- tryJust isException (function action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()

testF :: IO Bool -> Bool
testF action = unsafePerformIO action

result :: Int -> Int
result x | x > 5 = 4
         | otherwise = error "hey"
package linea;

import java.util.Arrays;

public abstract class TypeOfGame {


    char name;

    public static TypeOfGame getTypeOfGame(char typeOfGame) {


        return Arrays.stream(new TypeOfGame[]{
                        new HorizontalAndVerticalGame(),
                        new DiagonalGame(),
                        new CompleteGame()
                })
                .filter(game -> game.name == typeOfGame)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid type of game"));


    }
        abstract boolean validateWin(Linea board);



}

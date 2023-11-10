package linea;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class TypeOfGame {

    char name;

    public static final ArrayList<TypeOfGame> typesOfGame = new ArrayList<>(Arrays.asList(
            new HorizontalAndVerticalGame(),
            new DiagonalGame(),
            new AllDirectionsGame()
    ));

    public static TypeOfGame getTypeOfGame(char typeOfGame) {
        return typesOfGame.stream()
                .filter(game -> game.name == typeOfGame)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid type of game"));
    }
        abstract boolean validateWin(Linea board, int column);



}

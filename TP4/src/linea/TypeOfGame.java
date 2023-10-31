package linea;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class TypeOfGame {

    char name;

    // VALIDATE WIN PODRÍA SER UN ATRIBUTO DE LA CLASE, Y PODRÍAMOS CREAR TRES INSTANCIAS.
    // NO SE DIFERENCIAN ENTRE SI, SÓLO NOMBRE Y VALIDACIÓN.

    public static final ArrayList<TypeOfGame> typesOfGame = new ArrayList<>(Arrays.asList(
            new HorizontalAndVerticalGame(),
            new DiagonalGame(),
            new CompleteGame()
    ));

    public static TypeOfGame getTypeOfGame(char typeOfGame) {
        return typesOfGame.stream()
                .filter(game -> game.name == typeOfGame)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid type of game"));
    }
        abstract boolean validateWin(Linea board);



}

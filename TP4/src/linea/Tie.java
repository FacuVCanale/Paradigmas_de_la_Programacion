package linea;

public class Tie extends GameState {

    private final String gameIsOverMessage = "Game is over!";

    private final String gameIsATieMessage = "It's a tie!";

    public void playBlueAt(Linea game, int column) {
        throw new RuntimeException(gameIsOverMessage + " " + gameIsATieMessage);
    }

    public void playRedAt(Linea game, int column) {
        throw new RuntimeException(gameIsOverMessage + " " + gameIsATieMessage);
    }

    public GameState changeTurn() {
        throw new RuntimeException(gameIsOverMessage + " " + gameIsATieMessage);
    }

    public GameState win() {
        throw new RuntimeException(gameIsOverMessage + " " + gameIsATieMessage);
    }

    public GameState tie() {
        throw new RuntimeException(gameIsOverMessage + " " + gameIsATieMessage);
    }

    public String show() {
        return gameIsATieMessage;
    }
}

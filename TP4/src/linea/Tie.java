package linea;

public class Tie extends GameState {

    public static final String gameIsOverMessage = "Game is over! It's a tie!";


    public void playBlueAt(Linea game, int column) {
        throw new RuntimeException(gameIsOverMessage);
    }

    public void playRedAt(Linea game, int column) {
        throw new RuntimeException(gameIsOverMessage);
    }

    public GameState changeTurn() {
        throw new RuntimeException(gameIsOverMessage);
    }

    public GameState win() {
        throw new RuntimeException(gameIsOverMessage);
    }

    public GameState tie() {
        throw new RuntimeException(gameIsOverMessage);
    }

    public String show() {
        return "It's a tie!";
    }
}

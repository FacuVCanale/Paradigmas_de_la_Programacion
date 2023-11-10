package linea;

public class Tie extends GameState {

    public static final String ItsATieMessage = "It's a tie!";


    public void playBlueAt(Linea game, int column) {
        throw new RuntimeException(gameIsOverMessage + " " + ItsATieMessage);
    }

    public void playRedAt(Linea game, int column) {
        throw new RuntimeException(gameIsOverMessage + " " + ItsATieMessage);
    }

    public GameState changeTurn() {
        throw new RuntimeException(gameIsOverMessage + " " + ItsATieMessage);
    }

    public GameState win() {
        throw new RuntimeException(gameIsOverMessage + " " + ItsATieMessage);
    }

    public GameState tie() {
        throw new RuntimeException(gameIsOverMessage + " " + ItsATieMessage);
    }

    public String show() {
        return ItsATieMessage;
    }
}

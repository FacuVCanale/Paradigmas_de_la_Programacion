package linea;

public class Finish extends GameState {

    private static final String gameIsFinishedMessage = "Game is finished!";
    private final String message;
    public Finish(String message) {
        this.message = message;
    }

    public void playBlueAt(Linea game, int column) {
        throw new RuntimeException(gameIsFinishedMessage);
    }

    public void playRedAt(Linea game, int column) {
        throw new RuntimeException(gameIsFinishedMessage);
    }

    public GameState changeTurn() {
        throw new RuntimeException(gameIsFinishedMessage);
    }

    public GameState win() {
        throw new RuntimeException(gameIsFinishedMessage);
    }

    public GameState tie() {
        throw new RuntimeException(gameIsFinishedMessage);
    }

    public String show() {
        return message;
    }
}

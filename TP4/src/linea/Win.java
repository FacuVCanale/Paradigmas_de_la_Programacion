package linea;

public class Win extends GameState {
    private final String message;
    public Win(String message) {
        this.message = message;
    }

    public void playBlueAt(Linea game, int column) {
        throw new RuntimeException(GameState.gameIsOverMessage);
    }

    public void playRedAt(Linea game, int column) {
        throw new RuntimeException(GameState.gameIsOverMessage + " " + message);
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
        return message;
    }
}

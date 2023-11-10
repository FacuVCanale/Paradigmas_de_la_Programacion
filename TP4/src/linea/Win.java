package linea;

public class Win extends GameState {
    private final String message;
    public Win(String message) {
        this.message = message;
    }

    public void playBlueAt(Linea game, int column) {
        throw new RuntimeException(gameIsOverMessage + " " + message);
    }

    public void playRedAt(Linea game, int column) {
        throw new RuntimeException(GameState.gameIsOverMessage + " " + message);
    }

    public GameState changeTurn() {
        throw new RuntimeException(GameState.gameIsOverMessage + " " + message);
    }

    public GameState win() {
        throw new RuntimeException(GameState.gameIsOverMessage + " " + message);
    }

    public GameState tie() {
        throw new RuntimeException(GameState.gameIsOverMessage + " " + message);
    }

    public String show() {
        return message;
    }
}

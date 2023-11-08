package linea;

public class Finish extends GameState {
    private String message;
    public Finish(String message) {
        this.message = message;
    }

    public void playBlueAt(Linea game, int column) {
        throw new RuntimeException("Game is finished!");
    }

    public void playRedAt(Linea game, int column) {
        throw new RuntimeException("Game is finished!");
    }

    public GameState changeTurn() {
        throw new RuntimeException("Game is finished!");
    }

    public GameState win() {
        throw new RuntimeException("Game is finished!");
    }

    public GameState tie() {
        throw new RuntimeException("Game is finished!");
    }

    public String show() {
        return message;
    }
}

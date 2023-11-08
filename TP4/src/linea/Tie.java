package linea;

public class Tie extends GameState {
    public void playBlueAt(Linea game, int column) {
        throw new RuntimeException("Game is over! It's a tie!");
    }

    public void playRedAt(Linea game, int column) {
        throw new RuntimeException("Game is over! It's a tie!");
    }

    public GameState changeTurn() {
        throw new RuntimeException("Game is over! It's a tie!");
    }

    public GameState win() {
        throw new RuntimeException("Game is over! It's a tie!");
    }

    public GameState tie() {
        throw new RuntimeException("Game is over! It's a tie!");
    }

    public String show() {
        return "It's a tie!";
    }
}

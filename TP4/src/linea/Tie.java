package linea;

public class Tie extends GameState {
    public GameState playBlueAt(Linea game, int column) {
        throw new RuntimeException("Game is over! It's a tie!");
    }

    public GameState playRedAt(Linea game, int column) {
        throw new RuntimeException("Game is over! It's a tie!");
    }
}

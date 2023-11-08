package linea;

public class PlayingRed extends GameState {
    public GameState playBlueAt(Linea game, int column) {
        throw new RuntimeException("Not blue player's turn!");
    }

    public GameState playRedAt(Linea game, int column) {
        game.placePiece(column, GameState.RedPlayer);
        return new PlayingBlue();
    }
}

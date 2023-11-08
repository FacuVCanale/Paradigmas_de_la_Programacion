package linea;

public class PlayingBlue extends GameState {
    public GameState playBlueAt(Linea game, int column) {
        game.placePiece(column, GameState.BluePlayer);
        return new PlayingRed();
    }

    public GameState playRedAt(Linea game, int column) {
        throw new RuntimeException("Not blue player's turn!");
    }
}

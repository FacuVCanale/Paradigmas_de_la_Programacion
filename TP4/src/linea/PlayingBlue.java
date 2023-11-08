package linea;

public class PlayingBlue extends GameState {
    public void playBlueAt(Linea game, int column) {
        game.placePiece(column, GameState.BluePlayer);
    }

    public void playRedAt(Linea game, int column) {
        throw new RuntimeException("Not red player's turn!");
    }

    public GameState changeTurn() {
        return new PlayingRed();
    }

    public GameState win() {
        return new Finish("Blue has won!");
    }

    public GameState tie() {
        return new Tie();
    }

    public String show() {
        return "Blue's turn";
    }
}

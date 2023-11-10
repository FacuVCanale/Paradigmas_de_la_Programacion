package linea;

public class PlayingRed extends GameState {
    public void playBlueAt(Linea game, int column) {
        throw new RuntimeException("Not blue player's turn!");
    }

    public void playRedAt(Linea game, int column) {
        game.placePieceAt(column, 'X');
    }

    public GameState changeTurn() {
        return new PlayingBlue();
    }

    public GameState win() {
        return new Finish("Red has won!");
    }

    public GameState tie() {
        return new Tie();
    }

    public String show() {
        return "Red's turn";
    }
}

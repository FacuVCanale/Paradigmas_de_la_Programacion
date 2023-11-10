package linea;

public class PlayingBlue extends GameState {

    public static final String notRedPlayerTurnMessage = "Not red player's turn!";

    public void playBlueAt(Linea game, int column) {
        game.placePieceAt(column, '0');
    }

    public void playRedAt(Linea game, int column) {
        throw new RuntimeException(notRedPlayerTurnMessage);
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

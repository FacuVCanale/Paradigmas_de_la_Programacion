package linea;

public class RedTurnHandler extends TurnHandler {
    public TurnHandler playBlueAt(Linea game, int column) {
        throw new RuntimeException("Not blue player's turn!");
    }

    public TurnHandler playRedAt(Linea game, int column) {
        game.placePiece(column, TurnHandler.RedPlayer);
        return new BlueTurnHandler();
    }
}

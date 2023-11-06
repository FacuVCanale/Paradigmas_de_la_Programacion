package linea;

public class BlueTurnHandler extends TurnHandler {
    public TurnHandler playBlueAt(Linea game, int column) {
        game.placePiece(column, TurnHandler.BluePlayer);
        return new RedTurnHandler();
    }

    public TurnHandler playRedAt(Linea game, int column) {
        throw new RuntimeException("Not blue player's turn!");
    }
}

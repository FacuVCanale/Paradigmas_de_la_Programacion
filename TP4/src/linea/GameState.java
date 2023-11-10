package linea;

public abstract class GameState {
    public static char BluePlayer = '0';
    public static char RedPlayer = 'X';

    public static final String gameIsOverMessage = "Game is over!";

    public abstract String show();

    public abstract void playBlueAt(Linea game, int column);

    public abstract void playRedAt(Linea game, int column);

    public abstract GameState win();

    public abstract GameState tie();

    public abstract GameState changeTurn();
}

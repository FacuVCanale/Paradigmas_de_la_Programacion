package linea;

public abstract class TurnHandler {
    public static char BluePlayer = '0';
    public static char RedPlayer = 'X';
    private boolean gameFinished = false;

    public abstract TurnHandler playBlueAt(Linea game, int column);

    public abstract TurnHandler playRedAt(Linea game, int column);
}

package linea;

public abstract class GameState {
    public static char BluePlayer = '0';
    public static char RedPlayer = 'X';

    public abstract GameState playBlueAt(Linea game, int column);

    public abstract GameState playRedAt(Linea game, int column);
}

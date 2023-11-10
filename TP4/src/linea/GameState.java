package linea;

public abstract class GameState {

    public abstract String show();

    public abstract void playBlueAt(Linea game, int column);

    public abstract void playRedAt(Linea game, int column);

    public abstract GameState win();

    public abstract GameState tie();

    public abstract GameState changeTurn();
}

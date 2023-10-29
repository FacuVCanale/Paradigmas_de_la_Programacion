package linea;

public class BluePlayer implements Player{

    private char color;

    public BluePlayer() {
        this.color = 'B';

    }

    @Override
    public char getPlayerColor() {
        return this.color;
    }
}

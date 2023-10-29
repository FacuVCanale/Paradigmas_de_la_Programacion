package linea;

public class BluePlayer implements Player{

    private char color;

    public BluePlayer() {
        this.color = 'B';

    }

    @Override
    public void playAt(Board board, int column) {
        board.playBlueAt(column);
    }

    @Override
    public char getPlayerColor() {
        return this.color;
    }
}

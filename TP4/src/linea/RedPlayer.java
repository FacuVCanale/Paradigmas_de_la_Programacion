package linea;

public class RedPlayer implements Player{

    private final char color;

    public RedPlayer() {
        this.color = 'R';

    }


    @Override
    public void playAt(Board board, int column) {


        // Colocar una ficha roja en la posición obtenida
        board.playRedAt(column);


    }

    public char getPlayerColor() {
        return this.color;
    }


}

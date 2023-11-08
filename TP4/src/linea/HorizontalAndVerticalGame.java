package linea;

public class HorizontalAndVerticalGame extends TypeOfGame {
    public HorizontalAndVerticalGame() {
        this.name = 'A';
    }

    public boolean validateWin(Linea board, int column) {
        return board.checkHorizontalWin(column) || board.checkVerticalWin(column);
    }



}
package linea;

public class HorizontalAndVerticalGame extends TypeOfGame {
    public HorizontalAndVerticalGame() {
        this.name = 'A';
    }

    public boolean validateWin(Linea board) {
        return board.checkHorizontalWin() || board.checkVerticalWin();
    }



}
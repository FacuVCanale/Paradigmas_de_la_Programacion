package linea;

public class AllDirectionsGame extends TypeOfGame {

    public AllDirectionsGame(){
        this.name = 'C';
    }

    boolean validateWin(Linea board, int column) {
        return  board.checkDiagonalWin(column) || board.checkHorizontalWin(column) || board.checkVerticalWin(column);

    }

}

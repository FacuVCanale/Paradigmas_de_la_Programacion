package linea;

public class CompleteGame extends TypeOfGame {

    public CompleteGame(){
        this.name = 'C';
    }

    boolean validateWin(Linea board, int column) {
        return  board.checkDiagonalWin(column) || board.checkHorizontalWin(column) || board.checkVerticalWin(column);

    }

}

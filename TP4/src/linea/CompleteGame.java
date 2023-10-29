package linea;

public class CompleteGame extends TypeOfGame {
    @Override
    boolean validateWin(Board board) {

        return checkHorizontalWin(board) || checkVerticalWin(board) || checkDiagonalWin(board);

    }

}

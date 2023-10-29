package linea;

public class DiagonalGame extends TypeOfGame {
    @Override
    boolean validateWin(Board board) {
        return checkDiagonalWin(board);
    }




}

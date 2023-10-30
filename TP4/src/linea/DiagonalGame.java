package linea;

public class DiagonalGame extends TypeOfGame {


    public DiagonalGame(){
        this.name = 'B';
    }

    @Override
    boolean validateWin(Linea board) {
        return checkDiagonalWin(board);
    }



    protected static boolean checkDiagonalWin(Linea board) {

        // Check forward diagonal
        for (int row = 0; row < board.getBoardHeight() - 3; row++) {
            for (int col = 0; col < board.getBoardWidth() - 3; col++) {
                if (checkForwardDiagonal(board, row, col)) {
                    return true;
                }
            }
        }

        // Check backward diagonal
        for (int row = 0; row < board.getBoardHeight() - 3; row++) {
            for (int col = 3; col < board.getBoardWidth(); col++) {
                if (checkBackwardDiagonal(board, row, col)) {
                    return true;
                }
            }
        }

        return false;

    }

    private static boolean checkForwardDiagonal(Linea board, int startRow, int startCol) {

        char symbol = board.getBox(startRow, startCol);

        return symbol != ' ' &&
                symbol == board.getBox(startRow + 1, startCol + 1) &&
                symbol == board.getBox(startRow + 2, startCol + 2) &&
                symbol == board.getBox(startRow + 3, startCol + 3);

    }

    private static boolean checkBackwardDiagonal(Linea board, int startRow, int startCol) {

        char symbol = board.getBox(startRow, startCol);

        return symbol != ' ' &&
                symbol == board.getBox(startRow + 1, startCol - 1) &&
                symbol == board.getBox(startRow + 2, startCol - 2) &&
                symbol == board.getBox(startRow + 3, startCol - 3);

    }


}

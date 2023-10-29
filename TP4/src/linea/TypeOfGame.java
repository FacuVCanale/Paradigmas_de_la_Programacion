package linea;

public abstract class TypeOfGame {
    protected char name;

    public static TypeOfGame getTypeOfGame(char typeOfGame) {

        return switch (typeOfGame) {
            case 'A' -> new HorizontalAndVerticalGame();
            case 'B' -> new DiagonalGame();
            case 'C' -> new CompleteGame();
            default -> null;
        };
    }

    abstract boolean validateWin(Board board);


    protected static boolean checkVerticalWin(Board board) {

        for (int col = 0; col < board.getBoardWidth(); col++) {
            for (int row = 0; row < board.getBoardHeight() - 3; row++) {

                char symbol = board.getBox(row, col);

                if (symbol != ' ' &&
                        symbol == board.getBox(row + 1, col) &&
                        symbol == board.getBox(row + 2, col) &&
                        symbol == board.getBox(row + 3, col)) {

                    return true;
                }
            }
        }

        return false;
    }

    protected static boolean checkHorizontalWin(Board board) {

        for (int row = 0; row < board.getBoardHeight(); row++) {
            for (int col = 0; col < board.getBoardWidth() - 3; col++) {

                char symbol = board.getBox(row, col);

                if (symbol != ' ' &&
                        symbol == board.getBox(row, col + 1) &&
                        symbol == board.getBox(row, col + 2) &&
                        symbol == board.getBox(row, col + 3)) {

                    return true;
                }
            }
        }

        return false;
    }

    protected static boolean checkDiagonalWin(Board board) {

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

    private static boolean checkForwardDiagonal(Board board, int startRow, int startCol) {

        char symbol = board.getBox(startRow, startCol);

        return symbol != ' ' &&
                symbol == board.getBox(startRow + 1, startCol + 1) &&
                symbol == board.getBox(startRow + 2, startCol + 2) &&
                symbol == board.getBox(startRow + 3, startCol + 3);

    }

    private static boolean checkBackwardDiagonal(Board board, int startRow, int startCol) {

        char symbol = board.getBox(startRow, startCol);

        return symbol != ' ' &&
                symbol == board.getBox(startRow + 1, startCol - 1) &&
                symbol == board.getBox(startRow + 2, startCol - 2) &&
                symbol == board.getBox(startRow + 3, startCol - 3);

    }

}

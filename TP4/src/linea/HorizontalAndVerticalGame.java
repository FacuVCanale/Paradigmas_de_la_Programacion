package linea;

public class HorizontalAndVerticalGame extends TypeOfGame {
    public HorizontalAndVerticalGame() {
        this.name = 'A';
    }

    public boolean validateWin(Linea board) {
        return checkHorizontalWin(board) || checkVerticalWin(board);
    }

    protected static boolean checkVerticalWin(Linea board) {

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

    protected static boolean checkHorizontalWin(Linea board) {

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


}
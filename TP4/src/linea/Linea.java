package linea;

public class Linea {

    private static final char UNMARKED = ' ';

    public static final String columnIsFullError = "Column is full";
    private static final char PLAYER_BLUE = 'B';
    private static final char PLAYER_RED = 'R';

    private char[][] board;
    private int BOARD_HEIGHT;
    private int BOARD_WIDTH;

    public Linea(int rows, int cols, char typeOfGame) {

        this.BOARD_HEIGHT = rows;
        this.BOARD_WIDTH = cols;

        this.board = new char[rows][cols];


        for (int i = 0; i < this.BOARD_HEIGHT; i++) {
            for (int j = 0; j < this.BOARD_WIDTH; j++) {
                this.board[i][j] = UNMARKED;
            }
        }

    }

    public String showBoard() {

        StringBuilder board = new StringBuilder();

        for (int i = 0; i < this.BOARD_HEIGHT; i++) {
            board.append("|");
            for (int j = 0; j < this.BOARD_WIDTH; j++) {
                board.append(this.board[i][j]);
            }
            board.append("|\n");
        }

        return board.toString();

    }


    public boolean finished() {
        return fourInARow();
    }

    public void playBlueAt(int column) {

        int row = checkRowForColumn(column);

        this.board[row][column] = PLAYER_BLUE;


    }


    public void playRedAt(int column) {
        int row = checkRowForColumn(column);

        this.board[row][column] = PLAYER_RED;

    }

    private int checkRowForColumn(int column) {

        for (int row = this.BOARD_HEIGHT - 1; row >= 0; row--) {
            if (this.board[row][column] == UNMARKED) {
                return row;
            }
        }

        throw new RuntimeException(columnIsFullError);
    }



    public boolean fourInARow() {
        return fourInAHorizontalRow() || fourInAVerticalRow() || fourInADiagonalRow();
    }

    private boolean fourInADiagonalRow() {

        for (int i = 0; i < this.BOARD_HEIGHT - 3; i++) {
            for (int j = 0; j < this.BOARD_WIDTH - 3; j++) {
                if (this.board[i][j] != UNMARKED && this.board[i][j] == this.board[i + 1][j + 1] && this.board[i][j] == this.board[i + 2][j + 2] && this.board[i][j] == this.board[i + 3][j + 3]) {
                    return true;
                }
            }
        }

        for (int i = 0; i < this.BOARD_HEIGHT - 3; i++) {
            for (int j = 3; j < this.BOARD_WIDTH; j++) {
                if (this.board[i][j] != UNMARKED && this.board[i][j] == this.board[i + 1][j - 1] && this.board[i][j] == this.board[i + 2][j - 2] && this.board[i][j] == this.board[i + 3][j - 3]) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean fourInAVerticalRow() {
        for (int i = 0; i < this.BOARD_HEIGHT - 3; i++) {
            for (int j = 0; j < this.BOARD_WIDTH; j++) {
                if (this.board[i][j] != UNMARKED && this.board[i][j] == this.board[i + 1][j] && this.board[i][j] == this.board[i + 2][j] && this.board[i][j] == this.board[i + 3][j]) {
                    return true;
                }
            }
        }
        return false;
    }



    private boolean fourInAHorizontalRow() {
        for (int i = 0; i < this.BOARD_HEIGHT; i++) {
            for (int j = 0; j < this.BOARD_WIDTH - 3; j++) {
                if (this.board[i][j] != UNMARKED && this.board[i][j] == this.board[i][j + 1] && this.board[i][j] == this.board[i][j + 2] && this.board[i][j] == this.board[i][j + 3]) {
                    return true;
                }
            }
        }
        return false;
    }


}
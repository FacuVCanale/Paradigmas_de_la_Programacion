package linea;

import java.util.stream.IntStream;

public class Linea implements Board {

    private static final char UNMARKED = ' ';

    public static final String columnIsFullError = "Column is full";

    private final BluePlayer BluePlayer;
    private final RedPlayer RedPlayer;
    private final TypeOfGame typeOfGame;

    private char[][] board;
    private int BOARD_HEIGHT;
    private int BOARD_WIDTH;

    public Linea(int rows, int cols, char typeOfGame) {

        this.BOARD_HEIGHT = rows;
        this.BOARD_WIDTH = cols;
        this.typeOfGame = TypeOfGame.getTypeOfGame(typeOfGame);


        this.BluePlayer = new BluePlayer();

        this.RedPlayer = new RedPlayer();


        this.board = new char[rows][cols];


        IntStream.range(0, BOARD_HEIGHT)
                .forEach(i ->
                        IntStream.range(0, BOARD_WIDTH)
                                .forEach(j ->
                                        board[i][j] = UNMARKED));


    }


    @Override
    public void playBlueAt(int column) {

        placePiece(column, BluePlayer.getPlayerColor());


    }

    @Override
    public void playRedAt(int column) {
       placePiece(column, RedPlayer.getPlayerColor());

    }


    public void placePiece(int column, char player) {

        int row = checkRowForColumn(column);

        this.board[row][column] = player;

    }

    private int checkRowForColumn(int column) {

        return IntStream.rangeClosed(0, this.BOARD_HEIGHT - 1)
                .filter(row -> this.board[row][column] == UNMARKED)
                .reduce((first, second) -> second)
                .orElseThrow(() -> new RuntimeException(columnIsFullError));
    }


    @Override
    public String showBoard() {

        StringBuilder board = new StringBuilder();

        IntStream.range(0, BOARD_HEIGHT)
                .forEach(i -> {
                    board.append("|");
                    IntStream.range(0, BOARD_WIDTH)
                            .forEach(j -> board.append(this.board[i][j]));
                    board.append("|\n");
                });

        return board.toString();



    }

    @Override
    public boolean isFull() {
        return IntStream.range(0, BOARD_WIDTH)
                .noneMatch(col -> board[0][col] == UNMARKED);


    }

    @Override
    public boolean finished() {

        return isFull() || typeOfGame.validateWin(this);

    }

    @Override
    public int getBoardHeight() {
        return this.BOARD_HEIGHT;
    }

    @Override
    public int getBoardWidth() {
        return this.BOARD_WIDTH;
    }

    @Override
    public char getBox(int row, int col) {
        return this.board[row][col];
    }

}
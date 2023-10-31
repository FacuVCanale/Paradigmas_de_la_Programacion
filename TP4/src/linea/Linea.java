package linea;

import java.util.stream.IntStream;

public class Linea  {

    private static final char UNMARKED = ' ';

    public static final String columnIsFullError = "Column is full!";


    private final char BluePlayer = '0';

    private final char RedPlayer = 'X';

    private final TypeOfGame typeOfGame;

    private char currentPlayer = RedPlayer;

    private char[][] board;
    //PARA MI, LA FORMA DE SOLUCIONAR LOS PROBLEMAS DE BOARD SEA CREANDO CLASES ROW Y COL,
    // O DIRECTAMENTE BOARD, COSA DE QUE SE MANEJE A SÍ MISMO Y SEPA MEJOR SUS COSAS.

    
    private int BOARD_HEIGHT;
    private int BOARD_WIDTH;

    public Linea(int rows, int cols, char typeOfGame) {

        this.BOARD_HEIGHT = rows;
        this.BOARD_WIDTH = cols;
        this.typeOfGame = TypeOfGame.getTypeOfGame(typeOfGame);


        this.board = new char[rows][cols];


        IntStream.range(0, BOARD_HEIGHT)
                .forEach(i ->
                        IntStream.range(0, BOARD_WIDTH)
                                .forEach(j ->
                                        board[i][j] = UNMARKED));
    }



    public Linea playBlueAt(int column) {

        if(currentPlayer != BluePlayer) {
            throw new RuntimeException("Not blue player's turn!");
        }

        placePiece(column, BluePlayer);

        currentPlayer = RedPlayer;

        return this;
    }


    public Linea playRedAt(int column) {

        if(currentPlayer != RedPlayer) {
            throw new RuntimeException("Not red player's turn!");
        }

       placePiece(column, RedPlayer);

        currentPlayer = BluePlayer;

        return this;
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

    public String showBoard() {

        StringBuilder decoratedBoard = new StringBuilder();

        IntStream.range(0, BOARD_HEIGHT)
                .forEach(i -> {
                    decoratedBoard.append("|");
                    IntStream.range(0, BOARD_WIDTH)
                            .forEach(j -> decoratedBoard.append(this.board[i][j]));
                    decoratedBoard.append("|\n");
                });

        IntStream.range(0, BOARD_WIDTH + 2)
                .forEach(i -> decoratedBoard.append("-"));

        decoratedBoard.append("\n");

        return decoratedBoard.toString();

    }


    public boolean isFull() {
        return IntStream.range(0, BOARD_WIDTH)
                .noneMatch(col -> board[0][col] == UNMARKED);
    }


    public boolean finished() {
        return isFull() || typeOfGame.validateWin(this);
    }


    public int getBoardHeight() {
        return this.BOARD_HEIGHT;
    }


    public int getBoardWidth() {
        return this.BOARD_WIDTH;
    }


    public char getBox(int row, int col) {
        return this.board[row][col];
    }

    public char getCurrentPlayer() {
        return this.currentPlayer;
    }

}
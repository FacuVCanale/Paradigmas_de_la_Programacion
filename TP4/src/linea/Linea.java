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

        placePiece(column, BluePlayer);

        currentPlayer = RedPlayer;

        return this;
    }


    public Linea playRedAt(int column) {

       placePiece(column, RedPlayer);

        currentPlayer = BluePlayer;

        return this;
    }


    public void placePiece(int column, char player) {

        if(currentPlayer != player) {
            throw new RuntimeException("Not blue player's turn!");
        }

        int row = checkRowForColumn(column);

        this.board[row][column] = player;

        currentPlayer = RedPlayer;

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


    // hacer metodo oregutnar en el que nos evitamos ahcer una matriz preseteada con valores anecdoticos  de la matriz que noi tiene fuchas.
    // el metodo preguntar podria hacer que pregunte si hay una ficha roja o azul, si esta vacio, etc
    // el tablero es una lista de columnas, y columnas es una lista de fichas. columnas no son un objeto en si
    // Preguntar devuelve nada, X o O

    // Aplicar Metodo Eli (recortar tablero 4x2 + 1 X 4x2 + 1)


    // juego es una lista de n listas vacias. El test nos va a empujar a hacer algo asi.
    // Tenemos un modelo de hacer el juego, y a la hora de verlo, el show lo proyecta distinto.
    //

    // preguntar devuelve nada afuera de los bordes. esto facilita muchisimo preguntar las diagonales,
    // y de hecho solo hay que chequearlo desde la ultima ficha puesta, y no todas



    //Crear clase quw maneje el juego, si termino, etc.


   // private Linea changeTurn() {
      //  currentPlayer = currentPlayer.
    //}

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
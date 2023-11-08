package linea;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Linea  {

    private static final char UNMARKED = ' ';

    public static final String columnIsFullError = "Column is full!";

    private final TypeOfGame typeOfGame;

    private GameState gameState = new PlayingRed();

    private ArrayList<ArrayList<Character>> board = new ArrayList<>();

    private int BOARD_HEIGHT;
    private int BOARD_WIDTH;

    public Linea(int rows, int cols, char typeOfGame) {

        this.BOARD_HEIGHT = rows;
        this.BOARD_WIDTH = cols;
        this.typeOfGame = TypeOfGame.getTypeOfGame(typeOfGame);

        IntStream.range(0, BOARD_WIDTH)
                .forEach(i -> board.add(new ArrayList<>()));
    }

    public Linea playBlueAt(int column) {
        gameState = gameState.playBlueAt(this, column);
        return this;
    }

    public Linea playRedAt(int column) {
        gameState = gameState.playRedAt(this, column);
        return this;
    }

    public void placePiece(int column, char checker) {
        //sacar
        if (finished()) {
            throw new RuntimeException("Game is finished!");
        }

        if (column < 0 || column >= this.BOARD_WIDTH) {
            throw new RuntimeException("Column out of bounds!");
        }

        if (this.board.get(column).size() == this.BOARD_HEIGHT) {
            throw new RuntimeException(columnIsFullError);
        }

        this.board.get(column).add(checker);
    }

    public String showBoard() {

        StringBuilder decoratedBoard = new StringBuilder();

        IntStream.range(0, BOARD_HEIGHT)
                .forEach(i -> {
                    decoratedBoard.append("|");
                    IntStream.range(0, BOARD_WIDTH)
                            .forEach(j -> decoratedBoard.append(getBox(i, j)));
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
                .noneMatch(col -> getBox(0, col) == UNMARKED);
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

    public GameState getTurnHandler() {
        return this.gameState;
    }

    public char getBox(int row, int col) {
        try {
            return this.board.get(col).get(BOARD_HEIGHT - 1 - row);
        } catch (IndexOutOfBoundsException e) {
            return UNMARKED;
        }
    }

    protected boolean checkVerticalWin() {

        for (int col = 0; col < BOARD_WIDTH; col++) {
            for (int row = 0; row < BOARD_HEIGHT - 3; row++) {

                char symbol = this.getBox(row, col);

                if (symbol != ' ' &&
                        symbol == this.getBox(row + 1, col) &&
                        symbol == this.getBox(row + 2, col) &&
                        symbol == this.getBox(row + 3, col)) {

                    return true;
                }
            }
        }

        return false;
    }

    protected boolean checkHorizontalWin() {

        for (int row = 0; row < BOARD_HEIGHT; row++) {
            for (int col = 0; col < BOARD_WIDTH  - 3; col++) {

                char symbol = this.getBox(row, col);

                if (symbol != ' ' &&
                        symbol == this.getBox(row, col + 1) &&
                        symbol == this.getBox(row, col + 2) &&
                        symbol == this.getBox(row, col + 3)) {

                    return true;
                }
            }
        }

        return false;
    }

    protected boolean checkDiagonalWin() {

        for (int row = 0; row < BOARD_HEIGHT - 3; row++) {
            for (int col = 0; col < BOARD_WIDTH - 3; col++) {
                if (checkDiagonal(row, col, 1)) {
                    return true;
                }
            }
        }

        for (int row = 0; row < BOARD_HEIGHT - 3; row++) {
            for (int col = 3; col < BOARD_WIDTH; col++) {
                if (checkDiagonal(row, col, -1)) {
                    return true;
                }
            }
        }

        return false;

    }

    private boolean checkDiagonal(int startRow, int startCol, int step) {

        char symbol = this.getBox(startRow, startCol);

        return symbol != ' '
                && symbol == this.getBox(startRow + 1, startCol + step)
                && symbol == this.getBox(startRow + 2, startCol + 2*step)
                && symbol == this.getBox(startRow + 3, startCol + 3*step);

    }


}
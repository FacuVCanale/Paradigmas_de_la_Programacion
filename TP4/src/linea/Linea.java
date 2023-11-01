package linea;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Linea  {

    private static final char UNMARKED = ' ';

    public static final String columnIsFullError = "Column is full!";


    private final char BluePlayer = '0';

    private final char RedPlayer = 'X';

    private final TypeOfGame typeOfGame;

    private char currentPlayer = RedPlayer;

    private ArrayList<ArrayList<Character>> board = new ArrayList<>();
    //PARA MI, LA FORMA DE SOLUCIONAR LOS PROBLEMAS DE BOARD SEA CREANDO CLASES ROW Y COL,
    // O DIRECTAMENTE BOARD, COSA DE QUE SE MANEJE A SÍ MISMO Y SEPA MEJOR SUS COSAS.

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
        //sacar
        if (finished()) {
            throw new RuntimeException("Game is finished!");
        }
//sacasr if de finished creando clase nueva que maneje el jeugo y los otros de abajo no se puede sacar
        if(currentPlayer != player) {
            throw new RuntimeException("Not this player's turn!");
        }

        if (column < 0 || column >= this.BOARD_WIDTH) {
            throw new RuntimeException("Column out of bounds!");
        }

        if (this.board.get(column).size() == this.BOARD_HEIGHT) {
            throw new RuntimeException(columnIsFullError);
        }



        this.board.get(column).add(player);

        currentPlayer = RedPlayer;

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

    public char getCurrentPlayer() {
        return this.currentPlayer;
    }

    public char getBox(int row, int col) {
        try {
            return this.board.get(col).get(BOARD_HEIGHT - 1 - row);
        } catch (IndexOutOfBoundsException e) {
            return UNMARKED;
        }
    }

}
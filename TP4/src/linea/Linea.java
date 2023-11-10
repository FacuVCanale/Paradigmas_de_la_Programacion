package linea;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Linea {

    private static final char UNMARKED_SLOT = ' ';


    public static final String columnOutOfBoundsError = "Column out of bounds!";

    public static final String columnIsFullError = "Column is full!";

    private final TypeOfGame typeOfGame;

    private GameState gameState = new PlayingRed();

    private ArrayList<ArrayList<Character>> board = new ArrayList<>();

    private final int BOARD_HEIGHT;
    private final int BOARD_WIDTH;

    public Linea(int rows, int cols, char typeOfGame) {

        this.BOARD_HEIGHT = rows;
        this.BOARD_WIDTH = cols;
        this.typeOfGame = TypeOfGame.getTypeOfGame(typeOfGame);

        IntStream.range(0, BOARD_WIDTH)
                .forEach(i -> board.add(new ArrayList<>()));
    }

    public Linea playBlueAt(int column) {
        gameState.playBlueAt(this, column - 1);
        return this;
    }

    public Linea playRedAt(int column) {
        gameState.playRedAt(this, column - 1);
        return this;
    }

    public void placePieceAt(int column, char checker) {

        if (column < 0 || column >= this.BOARD_WIDTH) {
            throw new RuntimeException(columnOutOfBoundsError);
        }

        if (this.board.get(column).size() == this.BOARD_HEIGHT) {
            throw new RuntimeException(columnIsFullError);
        }

        this.board.get(column).add(checker);
        updateGameStateAfterMove(column);
    }

    private Linea updateGameStateAfterMove(int column) {
        if (typeOfGame.validateWin(this, column)) {
            gameState = gameState.win();
        } else if (areAllColumnsFilled()) {
            gameState = gameState.tie();
        } else {
            gameState = gameState.changeTurn();
        }
        return this;
    }

    public String show() {

        StringBuilder decoratedBoard = new StringBuilder();

        IntStream.range(0, BOARD_HEIGHT)
                .forEach(i -> {
                    decoratedBoard.append("|");
                    IntStream.range(0, BOARD_WIDTH)
                            .forEach(j -> decoratedBoard.append(getSymbolAtPosition(i, j)));
                    decoratedBoard.append("|\n");
                });

        IntStream.range(0, BOARD_WIDTH + 2)
                .forEach(i -> decoratedBoard.append("-"));

        decoratedBoard.append("\n");

        decoratedBoard.append(gameState.show() + "\n");

        return decoratedBoard.toString();

    }

    private boolean areAllColumnsFilled() {
        return IntStream.range(0, BOARD_WIDTH)
                .noneMatch(col -> getSymbolAtPosition(0, col) == UNMARKED_SLOT);
    }


    public boolean isGameFinished() {
        return gameState instanceof Tie || gameState instanceof Win;
    }

    private char getSymbolAtPosition(int row, int col) {

        int rowIndex = BOARD_HEIGHT - 1 - row;

        if (col >= 0 && col < BOARD_WIDTH) {

            ArrayList<Character> column = board.get(col);

            if (rowIndex >= 0 && rowIndex < column.size()) {

                return column.get(rowIndex);

            }

        }

        return UNMARKED_SLOT;

    }

    protected boolean checkVerticalWin(int xAxis) {

        int yAxis = BOARD_HEIGHT - this.board.get(xAxis).size();

        char symbol = this.getSymbolAtPosition(yAxis, xAxis);

        return IntStream.range(1, 4).mapToObj(row -> this.getSymbolAtPosition(row + yAxis, xAxis)).allMatch(s -> s == symbol);
    }

    protected boolean checkHorizontalWin(int xAxis) {

        int yAxis = BOARD_HEIGHT - this.board.get(xAxis).size();

        return checkWinningLineFromCoord(xAxis,yAxis,1,0);

    }

    protected boolean checkDiagonalWin(int x) {

        int y = BOARD_HEIGHT - this.board.get(x).size();

        return  checkWinningLineFromCoord(x,y,-1,1) || checkWinningLineFromCoord(x,y,-1,-1); // anda mal diagonal inversa
    }

    private boolean checkWinningLineFromCoord(int xAxis, int yAxis, int stepX, int stepY){

        char symbol = this.getSymbolAtPosition(yAxis, xAxis);

        return IntStream.range(0,4)
                .mapToObj(index -> IntStream.range(0,4)
                                            .mapToObj(delta -> this.getSymbolAtPosition(yAxis + (delta - index)*stepY,xAxis + (delta - index)*stepX))
                                            .allMatch(s -> s == symbol ))
                .anyMatch(s -> s);
    }


}
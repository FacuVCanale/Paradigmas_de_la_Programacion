package linea;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Linea {

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
        gameState.playBlueAt(this, column);
        return this;
    }

    public Linea playRedAt(int column) {
        gameState.playRedAt(this, column);
        return this;
    }

    public void placePiece(int column, char checker) {

        if (column < 0 || column >= this.BOARD_WIDTH) {
            throw new RuntimeException("Column out of bounds!");
        }

        if (this.board.get(column).size() == this.BOARD_HEIGHT) {
            throw new RuntimeException(columnIsFullError);
        }

        this.board.get(column).add(checker);
        checkGameFinished(column);
    }

    private Linea checkGameFinished(int column) {
        if (typeOfGame.validateWin(this, column)) {
            gameState = gameState.win();
        } else if (isFull()) {
            gameState = gameState.tie();
        } else {
            gameState = gameState.changeTurn();
        }
        return this;
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

        decoratedBoard.append(gameState.show() + "\n");

        return decoratedBoard.toString();

    }

    public boolean isFull() {
        return IntStream.range(0, BOARD_WIDTH)
                .noneMatch(col -> getBox(0, col) == UNMARKED);
    }


    public boolean finished() {
        return gameState instanceof Tie || gameState instanceof Finish;
    }

    public char getBox(int row, int col) {
        try {
            return this.board.get(col).get(BOARD_HEIGHT - 1 - row);
        } catch (IndexOutOfBoundsException e) {
            return UNMARKED;
        }
    }

    protected boolean checkVerticalWin(int x) {

        int y = BOARD_HEIGHT - this.board.get(x).size();

        char symbol = this.getBox(y, x);

        return IntStream.range(1, 4).mapToObj(row -> this.getBox(row + y, x)).allMatch(s -> s == symbol);
    }

    protected boolean checkHorizontalWin(int x) {

        int y = BOARD_HEIGHT - this.board.get(x).size();

        char symbol = this.getBox(y, x);

        return IntStream.range(1, 4).mapToObj(col -> this.getBox(y, col + x)).allMatch(s -> s == symbol) ||
                IntStream.range(1, 4).mapToObj(col -> this.getBox(y, x - col)).allMatch(s -> s == symbol);
    }

    protected boolean checkDiagonalWin(int x) {

        int y = BOARD_HEIGHT - this.board.get(x).size();

        char symbol = this.getBox(y, x);

        return IntStream.range(1, 4).mapToObj(step -> this.getBox(y + step, x + step)).allMatch(s -> s == symbol) ||
                IntStream.range(1, 4).mapToObj(step -> this.getBox(y + step, x - step)).allMatch(s -> s == symbol) ||
                IntStream.range(1, 4).mapToObj(step -> this.getBox(y - step, x + step)).allMatch(s -> s == symbol) ||
                IntStream.range(1, 4).mapToObj(step -> this.getBox(y - step, x - step)).allMatch(s -> s == symbol);

    }
}
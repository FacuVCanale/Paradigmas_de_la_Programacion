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

    protected boolean checkVerticalWin(int xAxis) {

        int yAxis = BOARD_HEIGHT - this.board.get(xAxis).size();

        char symbol = this.getBox(yAxis, xAxis);

        return IntStream.range(1, 4).mapToObj(row -> this.getBox(row + yAxis, xAxis)).allMatch(s -> s == symbol);
    }

    protected boolean checkHorizontalWin(int xAxis) {

        int yAxis = BOARD_HEIGHT - this.board.get(xAxis).size();

        return checkWithStepFromCoordinate(xAxis,yAxis,1,0);

        /*return IntStream.range(1, 4).mapToObj(col -> this.getBox(yAxis, col + xAxis)).allMatch(s -> s == symbol) ||
                IntStream.range(1, 4).mapToObj(col -> this.getBox(yAxis, xAxis - col)).allMatch(s -> s == symbol);*/
    }

    protected boolean checkDiagonalWin(int x) {

        int y = BOARD_HEIGHT - this.board.get(x).size();

        return checkWithStepFromCoordinate(x,y,1,1) || checkWithStepFromCoordinate(x,y,-1,-1);

        /*return IntStream.range(1, 4).mapToObj(step -> this.getBox(y + step, x + step)).allMatch(s -> s == symbol) ||
                IntStream.range(1, 4).mapToObj(step -> this.getBox(y + step, x - step)).allMatch(s -> s == symbol) ||
                IntStream.range(1, 4).mapToObj(step -> this.getBox(y - step, x + step)).allMatch(s -> s == symbol) ||
                IntStream.range(1, 4).mapToObj(step -> this.getBox(y - step, x - step)).allMatch(s -> s == symbol);
*/
    }

    private boolean checkWithStepFromCoordinate(int xAxis, int yAxis, int stepX, int stepY){

        char symbol = this.getBox(yAxis, xAxis);

        return IntStream.range(0,4) //  diagonal: 1,1; horizontal; 1,0; diagonal inverso: -1,-1
                .mapToObj(index -> IntStream.range(1,4)
                                            .mapToObj(delta -> this.getBox(yAxis + (delta - index)*stepY,xAxis + (delta - index)*stepX))
                                            .allMatch(s -> s == symbol ))
                .anyMatch(s -> s == true);

    }

}
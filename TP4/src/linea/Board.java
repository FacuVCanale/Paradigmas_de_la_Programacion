package linea;

public interface Board {

    void playBlueAt(int column);

    void playRedAt(int column);

    boolean isFull();

    boolean finished();

    String showBoard();

    int getBoardHeight();

    int getBoardWidth();

    char getBox(int row, int col);
}
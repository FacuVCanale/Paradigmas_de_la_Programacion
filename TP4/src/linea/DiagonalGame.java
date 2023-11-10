package linea;

public class DiagonalGame extends TypeOfGame {


    public DiagonalGame(){
        this.name = 'B';
    }

    boolean validateWin(Linea board, int column) {
        return board.checkDiagonalWin(column);
    }





}

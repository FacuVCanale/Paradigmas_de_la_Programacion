package linea;

public class DiagonalGame extends TypeOfGame {


    public DiagonalGame(){
        this.name = 'B';
    }

    @Override
    boolean validateWin(Linea board) {
        return board.checkDiagonalWin();
    }





}

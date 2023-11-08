package linea;

public class CompleteGame extends TypeOfGame {

    public CompleteGame(){
        this.name = 'C';
    }

    boolean validateWin(Linea board) {
        //NO SE SI ES MEJOR NEW O USAR EL STATIC.
        return  board.checkDiagonalWin() || board.checkHorizontalWin() || board.checkVerticalWin();

    }

}

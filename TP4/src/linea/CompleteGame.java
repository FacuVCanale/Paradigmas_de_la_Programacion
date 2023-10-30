package linea;

public class CompleteGame extends TypeOfGame {

    public CompleteGame(){
        this.name = 'C';
    }

    @Override
    boolean validateWin(Linea board) {

        return HorizontalAndVerticalGame.checkHorizontalWin(board) || HorizontalAndVerticalGame.checkVerticalWin(board) || DiagonalGame.checkDiagonalWin(board);

    }

}

package linea;

public class CompleteGame extends TypeOfGame {

    public CompleteGame(){
        this.name = 'C';
    }

    boolean validateWin(Linea board) {
        //NO SE SI ES MEJOR NEW O USAR EL STATIC.
        return new HorizontalAndVerticalGame().validateWin(board) || new DiagonalGame().validateWin(board);

    }

}

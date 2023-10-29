package linea;

public class HorizontalAndVerticalGame extends TypeOfGame {

    public void HorizontalAndVertical() {
        this.name = 'A';
    }


    @Override
    public boolean validateWin(Board board) {

        return checkHorizontalWin(board) || checkVerticalWin(board);

    }




}
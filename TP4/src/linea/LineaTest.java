package linea;

import org.junit.Test;

import static org.junit.Assert.*;

public class LineaTest {

    @Test
    public void testLineaShowsGameCorrectly() {
        Linea game = new Linea(4, 4, 'C');



        game.playRedAt(0);

        System.out.println( game.showBoard() );

        game.playBlueAt(0);

        System.out.println( game.showBoard() );


        //TODO: assert that the board is correct

    }

    @Test
    public void testPlayBlueAt() {
        Linea linea = new Linea(6, 7, 'H');

        linea.playBlueAt(0);

        assertEquals('B', linea.getBox(5, 0));
    }


    @Test
    public void testLineaGameFinalizesCorrect() {
        Linea game = new Linea(4, 4, 'C');

        game.playRedAt(0);
        game.playBlueAt(1);

        game.playRedAt(0);
        game.playBlueAt(1);

        game.playRedAt(0);
        game.playBlueAt(1);

        game.playRedAt(0);


        assertTrue(game.finished());

    }

    @Test
    public void testDiagonalWinPlayingHorizontalAndVerticalGame() {

        Linea game = new Linea(4, 4, 'A');

        // Play pieces to make a diagonal line
        game.playRedAt(0);
        game.playBlueAt(1);

        game.playBlueAt(2);
        game.playBlueAt(2);
        game.playRedAt(1);

        game.playRedAt(2);
        game.playBlueAt(3);
        game.playBlueAt(3);
        game.playBlueAt(3);
        game.playRedAt(3);

        System.out.println( game.showBoard() );


        assertFalse(game.finished());

    }


    @Test
    public void testDiagonalWinPlayingCompleteGame() {

        Linea game = new Linea(4, 4, 'C');

        // Play pieces to make a diagonal line
        game.playRedAt(0);
        game.playBlueAt(1);

        game.playBlueAt(2);
        game.playBlueAt(2);
        game.playRedAt(1);

        game.playRedAt(2);
        game.playBlueAt(3);
        game.playBlueAt(3);
        game.playBlueAt(3);
        game.playRedAt(3);

        System.out.println( game.showBoard() );


        assertTrue(game.finished());

    }



    @Test
    public void testLineaCantDropACheckOnAFullColumn() {

        Linea game = new Linea(4, 4, 'C');



        game.playRedAt(0);

        game.playBlueAt(0);

        game.playRedAt(0);

        game.playBlueAt(0);



        assertEquals(Linea.columnIsFullError, assertThrows(RuntimeException.class, ()-> game.playRedAt(0)).getMessage());

    }


    @Test
    public void testBoardIsFull() {

        Linea game = new Linea(1, 1, 'C');

        game.playRedAt(0);

        assertTrue(game.isFull());
        assertTrue(game.finished());

    }






    }
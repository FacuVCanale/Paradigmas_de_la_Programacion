package linea;

import org.junit.Test;

import static org.junit.Assert.*;

public class LineaTest {

    @Test
    public void testInitialBoardEmpty() {
        Linea linea = new Linea(3, 4, 'A');

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 4; c++) {
                assertEquals(' ', linea.getBox(r, c));
            }
        }
    }

    @Test
    public void testInitialPlayerRed() {
        Linea linea = new Linea(3, 4, 'A');

        assertEquals('X', linea.getCurrentPlayer());
    }
    
    @Test
    public void testPlayBlueOutOfTurn() {

        Linea game = new Linea(4, 4, 'A');

        try {
            game.playBlueAt(0);
            fail("Expected exception");
        } catch (RuntimeException e) {
            assertEquals("Not blue player's turn!", e.getMessage());
        }

    }

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
    public void testDiagonalNoWinPlayingHorizontalAndVerticalGame() {

        Linea game = new Linea(4, 4, 'A');


//Implement
        assertFalse(game.finished());

    }


    @Test
    public void testCheckGameEnds() {

        Linea game = new Linea(4, 4, 'A');



        game.playRedAt(0);
        game.playBlueAt(1);

        game.playRedAt(0);
        game.playBlueAt(1);


        assertFalse(game.finished());


        game.playRedAt(2);
        game.playBlueAt(3);

        game.playRedAt(2);
        game.playBlueAt(3);



        assertTrue(game.finished());



    }

    @Test
    public void testDiagonalDoesNotWinHorizontalGame() {

        Linea game = new Linea(4, 4, 'A');


        game.playRedAt(0);


        game.playBlueAt(1);
        game.playRedAt(1);

        game.playBlueAt(1);
        game.playRedAt(3);
        game.playBlueAt(1);

        game.playRedAt(2);
        game.playBlueAt(2);
        game.playRedAt(2);


        game.playBlueAt(3);
        game.playRedAt(0);
        game.playBlueAt(3);

        game.playRedAt(3);




        System.out.println( game.showBoard() );

        assertFalse(game.finished());

    }


    @Test
    public void testDiagonalWinsCompleteGame() {
        Linea game = new Linea(4, 4, 'C');


        game.playRedAt(0);


        game.playBlueAt(1);
        game.playRedAt(1);

        game.playBlueAt(1);
        game.playRedAt(3);
        game.playBlueAt(1);

        game.playRedAt(2);
        game.playBlueAt(2);
        game.playRedAt(2);


        game.playBlueAt(3);
        game.playRedAt(0);
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
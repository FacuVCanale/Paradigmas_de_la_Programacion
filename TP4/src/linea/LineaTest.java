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
    public void testLineaCantDropACheckOnAFullColumn() {

        Linea game = new Linea(4, 4, 'C');



        game.playRedAt(0);

        game.playBlueAt(0);

        game.playRedAt(0);

        game.playBlueAt(0);



        assertEquals(Linea.columnIsFullError, assertThrows(RuntimeException.class, ()-> game.playRedAt(0)).getMessage());

    }




    }
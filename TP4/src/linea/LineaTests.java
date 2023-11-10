package linea;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class LineaTests {
    @Test void testLineaIsNotFinished() {
        assertFalse(basicGameModeA().isGameFinished());
    }

    @Test void testLineaCanShowBoard() {
        assertEquals("""
                        |    |
                        |    |
                        |    |
                        |    |
                        ------
                        Red's turn                      
                        """,
                basicGameModeA().show());
    }

    @Test void testLineaCanAdjustRowSize() {
        assertEquals("""
                        |    |
                        |    |
                        ------
                        Red's turn
                        """,
                new Linea(2,4, 'A').show());
    }

    @Test void testLineaCanAdjustColumnSize() {
        assertEquals("""
                        |  |
                        |  |
                        |  |
                        |  |
                        ----
                        Red's turn
                        """,
                new Linea(4,2, 'A').show());
    }

    @Test void testLineaCanPlayRedAt() {
        assertEquals("""
                        |    |
                        |    |
                        |    |
                        |X   |
                        ------
                        Blue's turn
                        """,
                basicGameModeA().playRedAt(1).show());
    }

    @Test void testLineaCanPlayRedAtDifferentColumn() {
        assertEquals("""
                        |    |
                        |    |
                        |    |
                        | X  |
                        ------
                        Blue's turn
                        """,
                basicGameModeA().playRedAt(2).show());
    }

    @Test void testLineaGameDoesNotFinishAfterANonWinningPlay() {
        assertFalse(basicGameModeA().playRedAt(2).isGameFinished());
    }

    @Test void testLineaGameRedCanNotPlayTwiceInARow() {
        assertThrowsLike("Not red player's turn!",
                            () -> basicGameModeA().playRedAt(1).playRedAt(1));
    }

    @Test void testLineaCanPlayBlueAfterRed() {
        assertEquals("""
                        |    |
                        |    |
                        |0   |
                        |X   |
                        ------
                        Red's turn
                        """,
                basicGameModeA().playRedAt(1)
                    .playBlueAt(1).show());
    }

    @Test void testLineaBlueCanNotPlayFirst() {
        assertThrowsLike("Not blue player's turn!",
                            () -> basicGameModeA().playBlueAt(1));
    }

    @Test void testLineaGameBlueCanNotPlayTwiceInARow() {
        assertThrowsLike("Not blue player's turn!",
                            () -> basicGameModeA()
                                    .playRedAt(1)
                                    .playBlueAt(1)
                                    .playBlueAt(1));

    }

    @Test void testLineaGameRedCanPlayAfterBlue() {
        assertEquals("""
                        |    |
                        |X   |
                        |0   |
                        |X   |
                        ------
                        Blue's turn
                        """,
                basicGameModeA()
                    .playRedAt(1)
                    .playBlueAt(1)
                    .playRedAt(1).show());
    }





    @Test void testPlayerCanNotPlaceOutsideColumns() {
        assertThrowsLike("Column out of bounds!",
                () -> basicGameModeA()
                        .playRedAt(5));
    }

    @Test void testPlayerCanNotPlaceIfColumnIsFull() {
        assertThrowsLike("Column is full!",
                            () -> new Linea(1, 4, 'A')
                                    .playRedAt(1).playBlueAt(1));
    }

    @Test void testLineaFinishesAfterFullBoard() {
        assertTrue(new Linea(1, 4, 'A')
                        .playRedAt(1)
                        .playBlueAt(2)
                        .playRedAt(3)
                        .playBlueAt(4)
                        .isGameFinished());
    }

    @Test void testLineaCanNotPlayAfterWin() {
        assertThrowsLike(Win.gameIsOverMessage,
                () -> new Linea(1, 4, 'A')
                        .playRedAt(1)
                        .playBlueAt(2)
                        .playRedAt(3)
                        .playBlueAt(4)
                        .playRedAt(1));
    }

    @Test void testLineaCanNotPlayAfterTie() {
        assertThrowsLike(Tie.gameIsOverMessage,
                            () -> new Linea(1, 4, 'A')
                                    .playRedAt(1)
                                    .playBlueAt(2)
                                    .playRedAt(3)
                                    .playBlueAt(4)
                                    .playRedAt(1));
    }

    @Test void testLineaGameCanFinishAfterAHorizontalWin() {
        Linea game = gameWithHorizontalRedWin('A');
        assertEquals("""
                        |    |
                        |    |
                        |000 |
                        |XXXX|
                        ------
                        Red has won!
                        """,
                game.show());
        assertTrue(game.isGameFinished());
    }

    @Test void testLineaGameCanFinishInAnyRow() {
        Linea game = basicGameModeA();
        game.playRedAt(1)
                .playBlueAt(1)
                .playRedAt(2)
                .playBlueAt(2)
                .playRedAt(3)
                .playBlueAt(3)
                .playRedAt(1)
                .playBlueAt(4)
                .playRedAt(2)
                .playBlueAt(1)
                .playRedAt(3)
                .playBlueAt(2)
                .playRedAt(4)
                .playBlueAt(3)
                .playRedAt(4);
        assertEquals("""
                        |000 |
                        |XXXX|
                        |000X|
                        |XXX0|
                        ------
                        Red has won!
                        """,
                game.show());
        assertTrue(game.isGameFinished());
    }

    @Test void testLineaGameCanFinishAfterAVerticalWin() {
        Linea game = gameWithVerticalRedWin('A');
        assertEquals("""
                        |X   |
                        |X0  |
                        |X0  |
                        |X0  |
                        ------
                        Red has won!
                        """,
                game.show());
        assertTrue(game.isGameFinished());
    }

    @Test void TestLineaPlayerCanWinInAnyColumn() {
        Linea game = basicGameModeA();
        game.playRedAt(3)
                .playBlueAt(2)
                .playRedAt(3)
                .playBlueAt(2)
                .playRedAt(3)
                .playBlueAt(2)
                .playRedAt(3);
        assertEquals("""
                        |  X |
                        | 0X |
                        | 0X |
                        | 0X |
                        ------
                        Red has won!
                        """,
                game.show());
        assertTrue(game.isGameFinished());
    }


    @Test void testGameEndsWhenFillingGapInThreeCheckerHorizontalLine() {
        Linea game = basicGameModeA();
        game.playRedAt(1)
                .playBlueAt(1)
                .playRedAt(2)
                .playBlueAt(2)
                .playRedAt(3)
                .playBlueAt(4)
                .playRedAt(1)
                .playBlueAt(4)
                .playRedAt(1)
                .playBlueAt(3);

        assertEquals("""
                        |X   |
                        |X   |
                        |0000|
                        |XXX0|
                        ------
                        Blue has won!
                        """,
                game.show());
        assertTrue(game.isGameFinished());
    }

    @Test
    public void testGameEndsWhenFillingGapInThreeCheckerBackwardDiagonalLine() {

        Linea game =  new Linea(4, 4, 'B');

        game.playRedAt(1)
                .playBlueAt(1)
                .playRedAt(1)
                .playBlueAt(2)
                .playRedAt(1)
                .playBlueAt(3)
                .playRedAt(4)
                .playBlueAt(2)
                .playRedAt(3)
                .playBlueAt(4)
                .playRedAt(4)
                .playBlueAt(4)
                .playRedAt(2);



        assertEquals("""
                        |X  0|
                        |XX X|
                        |00X0|
                        |X00X|
                        ------
                        Red has won!
                        """,
                game.show());
        assertTrue(game.isGameFinished());

    }



    @Test
    public void testGameEndsWhenFillingGapInThreeCheckerDiagonalLine() {

        Linea game =  new Linea(4, 4, 'B');

        game.playRedAt(1)
                .playBlueAt(1)
                .playRedAt(2)
                .playBlueAt(3)
                .playRedAt(2)
                .playBlueAt(3)
                .playRedAt(4)
                .playBlueAt(4)
                .playRedAt(4)
                .playBlueAt(1)
                .playRedAt(4)
                .playBlueAt(2)
                .playRedAt(3);


        assertEquals("""
                        |   X|
                        |00XX|
                        |0X00|
                        |XX0X|
                        ------
                        Red has won!
                        """,
                game.show());
        assertTrue(game.isGameFinished());

    }


    @Test void testLineaGameCanFinishInModeBAfterADiagonalWin() {
        Linea game = gameWithDiagonalRedWin('B');
        assertEquals("""
                        |   X|
                        |  X0|
                        |XX00|
                        |X00X|
                        ------
                        Red has won!
                        """,
                game.show());
        assertTrue(game.isGameFinished());
    }

    @Test void testLineaGameCanFinishInModeBAfterInvertedDiagonalWin() {
        Linea game = new Linea(4, 4, 'B');
        game.playRedAt(4)
            .playBlueAt(3)
            .playRedAt(1)
            .playBlueAt(2)
            .playRedAt(1)
            .playBlueAt(2)
            .playRedAt(3)
            .playBlueAt(4)
            .playRedAt(2)
            .playBlueAt(1)
            .playRedAt(1);
        assertEquals("""
                        |X   |
                        |0X  |
                        |X0X0|
                        |X00X|
                        ------
                        Red has won!
                        """,
                game.show());
        assertTrue(game.isGameFinished());
    }

    @Test void testLineaGameDoesNotFinishInModeAWithADiagonalWin() {
        assertFalse(gameWithDiagonalRedWin('A').isGameFinished());
    }

    @Test void testLineaCanNotWinInModeBWithAHorizontalWin() {
        assertFalse(gameWithHorizontalRedWin('B').isGameFinished());
    }

    @Test void testLineaCanNotWinInModeBWithAVerticalWin() {
        assertFalse(gameWithVerticalRedWin('B').isGameFinished());
    }

    @Test void testLineaPlayerCanWinHorizontallyInModeC() {
        assertTrue(gameWithHorizontalRedWin('C').isGameFinished());
    }

    @Test void testLineaPlayerCanWinVerticallyInModeC() {
        assertTrue(gameWithVerticalRedWin('C').isGameFinished());
    }

    @Test void testLineaPlayerCanWinDiagonallyInModeC() {
        assertTrue(gameWithDiagonalRedWin('C').isGameFinished());
    }



    private Linea basicGameModeA() {
        return new Linea(4, 4, 'A');
    }

    private void assertThrowsLike(String message, Executable executable) {
        assertEquals(message,
                assertThrows(RuntimeException.class, executable).getMessage());
    }

    private Linea gameWithHorizontalRedWin(char mode) {
        return new Linea(4, 4, mode)
                .playRedAt(1)
                .playBlueAt(1)
                .playRedAt(2)
                .playBlueAt(2)
                .playRedAt(3)
                .playBlueAt(3)
                .playRedAt(4);
    }

    private Linea gameWithVerticalRedWin(char mode) {
        return new Linea(4, 4, mode)
                .playRedAt(1)
                .playBlueAt(2)
                .playRedAt(1)
                .playBlueAt(2)
                .playRedAt(1)
                .playBlueAt(2)
                .playRedAt(1);
    }

    private Linea gameWithDiagonalRedWin(char mode) {
        return new Linea(4, 4, mode)
                .playRedAt(1)
                .playBlueAt(2)
                .playRedAt(2)
                .playBlueAt(3)
                .playRedAt(4)
                .playBlueAt(3)
                .playRedAt(3)
                .playBlueAt(4)
                .playRedAt(1)
                .playBlueAt(4)
                .playRedAt(4);
    }


}

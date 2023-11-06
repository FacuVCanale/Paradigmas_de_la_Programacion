package linea;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class LineaTests {
    @Test void testLineaIsNotFinished() {
        assertFalse(basicGameModeA().finished());
    }

    @Test void testLineaCanShowBoard() {
        assertEquals("""
                        |    |
                        |    |
                        |    |
                        |    |
                        ------
                        """,
                basicGameModeA().showBoard());
    }

    @Test void testLineaCanAdjustRowSize() {
        assertEquals("""
                        |    |
                        |    |
                        ------
                        """,
                new Linea(2,4, 'A').showBoard());
    }

    @Test void testLineaCanAdjustColumnSize() {
        assertEquals("""
                        |  |
                        |  |
                        |  |
                        |  |
                        ----
                        """,
                new Linea(4,2, 'A').showBoard());
    }

    @Test void testLineaCanPlayRedAt() {
        assertEquals("""
                        |    |
                        |    |
                        |    |
                        |X   |
                        ------
                        """,
                basicGameModeA().playRedAt(0).showBoard());
    }

    @Test void testLineaCanPlayRedAtDifferentColumn() {
        assertEquals("""
                        |    |
                        |    |
                        |    |
                        | X  |
                        ------
                        """,
                basicGameModeA().playRedAt(1).showBoard());
    }

    @Test void testLineaGameDoesNotFinishAfterANonWinningPlay() {
        assertFalse(basicGameModeA().playRedAt(0).finished());
    }

    @Test void testLineaGameRedCanNotPlayTwiceInARow() {
        assertThrowsLike("Not red player's turn!",
                            () -> basicGameModeA().playRedAt(0).playRedAt(0));
    }

    @Test void testLineaCanPlayBlueAfterRed() {
        assertEquals("""
                        |    |
                        |    |
                        |0   |
                        |X   |
                        ------
                        """,
                basicGameModeA().playRedAt(0)
                    .playBlueAt(0).showBoard());
    }

    @Test void testLineaBlueCanNotPlayFirst() {
        assertThrowsLike("Not blue player's turn!",
                            () -> basicGameModeA().playBlueAt(0));
    }

    @Test void testLineaGameBlueCanNotPlayTwiceInARow() {
        assertThrowsLike("Not blue player's turn!",
                            () -> basicGameModeA()
                                    .playRedAt(0)
                                    .playBlueAt(0)
                                    .playBlueAt(0));

    }

    @Test void testLineaGameRedCanPlayAfterBlue() {
        assertEquals("""
                        |    |
                        |X   |
                        |0   |
                        |X   |
                        ------
                        """,
                basicGameModeA()
                    .playRedAt(0)
                    .playBlueAt(0)
                    .playRedAt(0).showBoard());
    }





    @Test void testPlayerCanNotPlaceOutsideColumns() {
        assertThrowsLike("Column out of bounds!",
                () -> basicGameModeA()
                        .playRedAt(4));
    }

    @Test void testPlayerCanNotPlaceIfColumnIsFull() {
        assertThrowsLike("Column is full!",
                            () -> new Linea(1, 4, 'A')
                                    .playRedAt(0).playBlueAt(0));
    }

    @Test void testLineaFinishesAfterFullBoard() {
        assertTrue(new Linea(1, 4, 'A')
                        .playRedAt(0)
                        .playBlueAt(1)
                        .playRedAt(2)
                        .playBlueAt(3)
                        .finished());
    }

    @Test void testLineaCanNotPlayAfterFinished() {
        assertThrowsLike("Game is finished!",
                            () -> new Linea(1, 4, 'A')
                                    .playRedAt(0)
                                    .playBlueAt(1)
                                    .playRedAt(2)
                                    .playBlueAt(3)
                                    .playRedAt(0));
    }

    @Test void testLineaGameCanFinishAfterAHorizontalWin() {
        Linea game = gameWithHorizontalRedWin('A');
        assertEquals("""
                        |    |
                        |    |
                        |000 |
                        |XXXX|
                        ------
                        """,
                game.showBoard());
        assertTrue(game.finished());
    }

    @Test void testLineaGameCanFinishInAnyRow() {
        Linea game = basicGameModeA();
        game.playRedAt(0)
                .playBlueAt(0)
                .playRedAt(1)
                .playBlueAt(1)
                .playRedAt(2)
                .playBlueAt(2)
                .playRedAt(0)
                .playBlueAt(3)
                .playRedAt(1)
                .playBlueAt(0)
                .playRedAt(2)
                .playBlueAt(1)
                .playRedAt(3)
                .playBlueAt(2)
                .playRedAt(3);
        assertEquals("""
                        |000 |
                        |XXXX|
                        |000X|
                        |XXX0|
                        ------
                        """,
                game.showBoard());
        assertTrue(game.finished());
    }

    @Test void testLineaGameCanFinishAfterAVerticalWin() {
        Linea game = gameWithVerticalRedWin('A');
        assertEquals("""
                        |X   |
                        |X0  |
                        |X0  |
                        |X0  |
                        ------
                        """,
                game.showBoard());
        assertTrue(game.finished());
    }

    @Test void TestLineaPlayerCanWinInAnyColumn() {
        Linea game = basicGameModeA();
        game.playRedAt(2)
                .playBlueAt(1)
                .playRedAt(2)
                .playBlueAt(1)
                .playRedAt(2)
                .playBlueAt(1)
                .playRedAt(2);
        assertEquals("""
                        |  X |
                        | 0X |
                        | 0X |
                        | 0X |
                        ------
                        """,
                game.showBoard());
        assertTrue(game.finished());
    }

    @Test void testLineaGameCanFinishInModeBAfterADiagonalWin() {
        Linea game = gameWithDiagonalRedWin('B');
        assertEquals("""
                        |   X|
                        |  X0|
                        |XX00|
                        |X00X|
                        ------
                        """,
                game.showBoard());
        assertTrue(game.finished());
    }

    @Test void testLineaGameCanFinishInModeBAfterInvertedDiagonalWin() {
        Linea game = new Linea(4, 4, 'B');
        game.playRedAt(3)
            .playBlueAt(2)
            .playRedAt(0)
            .playBlueAt(1)
            .playRedAt(0)
            .playBlueAt(1)
            .playRedAt(2)
            .playBlueAt(3)
            .playRedAt(1)
            .playBlueAt(0)
            .playRedAt(0);
        assertEquals("""
                        |X   |
                        |0X  |
                        |X0X0|
                        |X00X|
                        ------
                        """,
                game.showBoard());
        assertTrue(game.finished());
    }

    @Test void testLineaGameDoesNotFinishInModeAWithADiagonalWin() {
        assertFalse(gameWithDiagonalRedWin('A').finished());
    }

    @Test void testLineaCanNotWinInModeBWithAHorizontalWin() {
        assertFalse(gameWithHorizontalRedWin('B').finished());
    }

    @Test void testLineaCanNotWinInModeBWithAVerticalWin() {
        assertFalse(gameWithVerticalRedWin('B').finished());
    }

    @Test void testLineaPlayerCanWinHorizontallyInModeC() {
        assertTrue(gameWithHorizontalRedWin('C').finished());
    }

    @Test void testLineaPlayerCanWinVerticallyInModeC() {
        assertTrue(gameWithVerticalRedWin('C').finished());
    }

    @Test void testLineaPlayerCanWinDiagonallyInModeC() {
        assertTrue(gameWithDiagonalRedWin('C').finished());
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
                .playRedAt(0)
                .playBlueAt(0)
                .playRedAt(1)
                .playBlueAt(1)
                .playRedAt(2)
                .playBlueAt(2)
                .playRedAt(3);
    }

    private Linea gameWithVerticalRedWin(char mode) {
        return new Linea(4, 4, mode)
                .playRedAt(0)
                .playBlueAt(1)
                .playRedAt(0)
                .playBlueAt(1)
                .playRedAt(0)
                .playBlueAt(1)
                .playRedAt(0);
    }

    private Linea gameWithDiagonalRedWin(char mode) {
        return new Linea(4, 4, mode)
                .playRedAt(0)
                .playBlueAt(1)
                .playRedAt(1)
                .playBlueAt(2)
                .playRedAt(3)
                .playBlueAt(2)
                .playRedAt(2)
                .playBlueAt(3)
                .playRedAt(0)
                .playBlueAt(3)
                .playRedAt(3);
    }


}

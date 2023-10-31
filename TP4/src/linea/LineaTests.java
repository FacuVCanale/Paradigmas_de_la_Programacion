package linea;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class LineaTests {
    @Test void testLineaIsNotFinished() {
        assertFalse(new Linea(4,4, 'A').finished());
    }

    @Test void testLineaCanShowBoard() {
        assertEquals("""
                        |    |
                        |    |
                        |    |
                        |    |
                        ------
                        """,
                new Linea(4,4, 'A').showBoard());
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
                new Linea(4,4, 'A')
                        .playRedAt(0).showBoard());
    }

    @Test void testLineaCanPlayRedAtDifferentColumn() {
        assertEquals("""
                        |    |
                        |    |
                        |    |
                        | X  |
                        ------
                        """,
                new Linea(4,4, 'A')
                        .playRedAt(1).showBoard());
    }

    @Test void testLineaGameDoesNotFinishAfterANonWinningPlay() {
        assertFalse(new Linea(4,4, 'A').playRedAt(0).finished());
    }

    @Test void testLineaGameRedCanNotPlayTwiceInARow() {
        assertThrowsLike("Not red player's turn!",
                            () -> new Linea(4, 4, 'A')
                                    .playRedAt(0)
                                    .playRedAt(0));
    }

    @Test void testLineaCanPlayBlueAfterRed() {
        assertEquals("""
                        |    |
                        |    |
                        |0   |
                        |X   |
                        ------
                        """,
                new Linea(4,4, 'A')
                    .playRedAt(0)
                    .playBlueAt(0).showBoard());
    }

    @Test void testLineaBlueCanNotPlayFirst() {
        assertThrowsLike("Not blue player's turn!",
                            () -> new Linea(4, 4, 'A')
                                    .playBlueAt(0));
    }

    @Test void testLineaGameBlueCanNotPlayTwiceInARow() {
        assertThrowsLike("Not blue player's turn!",
                            () -> new Linea(4, 4, 'A')
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
                new Linea(4, 4, 'A')
                    .playRedAt(0)
                    .playBlueAt(0)
                    .playRedAt(0).showBoard());
    }

    @Test void testPlayerCanNotPlaceOutsideColumns() {
        assertThrowsLike("Column out of bounds!",
                () -> new Linea(4, 4, 'A')
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
        Linea game = gameWithHorizontalRedWin(4,4, 'A');
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
        Linea game = new Linea(4, 4, 'A');
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
        Linea game = gameWithVerticalRedWin(4,4, 'A');
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
        Linea game = new Linea(4, 4, 'A');
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
        Linea game = gameWithDiagonalRedWin(4,4, 'B');
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
        assertFalse(gameWithDiagonalRedWin(4,4, 'A').finished());
    }

    @Test void testLineaCanNotWinInModeBWithAHorizontalWin() {
        assertFalse(gameWithHorizontalRedWin(4,4, 'B').finished());
    }

    @Test void testLineaCanNotWinInModeBWithAVerticalWin() {
        assertFalse(gameWithVerticalRedWin(4,4, 'B').finished());
    }

    @Test void testLineaPlayerCanWinHorizontallyInModeC() {
        assertTrue(gameWithHorizontalRedWin(4,4, 'C').finished());
    }

    @Test void testLineaPlayerCanWinVerticallyInModeC() {
        assertTrue(gameWithVerticalRedWin(4,4, 'C').finished());
    }

    @Test void testLineaPlayerCanWinDiagonallyInModeC() {
        assertTrue(gameWithDiagonalRedWin(4,4, 'C').finished());
    }

    private void assertThrowsLike(String message, Executable executable) {
        assertEquals(message,
                assertThrows(RuntimeException.class, executable).getMessage());
    }

    private Linea gameWithHorizontalRedWin(int rows, int cols, char mode) {
        return new Linea(rows, cols, mode)
                .playRedAt(0)
                .playBlueAt(0)
                .playRedAt(1)
                .playBlueAt(1)
                .playRedAt(2)
                .playBlueAt(2)
                .playRedAt(3);
    }

    private Linea gameWithVerticalRedWin(int rows, int cols, char mode) {
        return new Linea(rows, cols, mode)
                .playRedAt(0)
                .playBlueAt(1)
                .playRedAt(0)
                .playBlueAt(1)
                .playRedAt(0)
                .playBlueAt(1)
                .playRedAt(0);
    }

    private Linea gameWithDiagonalRedWin(int rows, int cols, char mode) {
        return new Linea(rows, cols, mode)
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

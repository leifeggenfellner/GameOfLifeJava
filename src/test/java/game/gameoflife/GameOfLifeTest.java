package game.gameoflife;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * This is a test class for a Java implementation of the Game of Life,
 * containing tests for grid
 * creation, cell state, ticking, emptying the grid, and a glider pattern.
 */
public class GameOfLifeTest {

    /**
     * This is a unit test in Java that checks if a GameOfLife object can create a
     * grid of a specified
     * size.
     */
    @Test
    public void testGridCreation() {
        GameOfLife game = new GameOfLife(10, 10);
        assertNotNull(game.getGrid());
    }

    /**
     * This is a unit test in Java for the GameOfLife class that checks if a cell's
     * state can be set
     * and retrieved correctly.
     */
    @Test
    public void testCellState() {
        GameOfLife game = new GameOfLife(10, 10);
        game.setCellState(0, 0, 1);
        assertTrue(game.getCellState(0, 0));
    }

    /**
     * This is a unit test for the "tick" function in a Game of Life implementation,
     * which tests if the
     * function correctly updates the state of cells in the game grid according to
     * the rules of the
     * game.
     */
    @Test
    public void testTick() {
        GameOfLife game = new GameOfLife(10, 10);
        game.emptyGrid();
        game.setCellState(1, 1, 1);
        game.setCellState(2, 2, 1);
        game.setCellState(3, 3, 1);
        game.tick();
        assertFalse(game.getCellState(1, 1));
        assertTrue(game.getCellState(2, 2));
        assertFalse(game.getCellState(3, 3));
    }

    /**
     * This function tests if an empty grid generates an empty next generation in
     * the Game of Life.
     */
    @Test
    public void testEmptyGrid() {
        GameOfLife game = new GameOfLife(10, 10);
        game.emptyGrid();
        game.tick();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertFalse(game.getCellState(i, j));
            }
        }
    }

    /**
     * This is a JUnit test for the GameOfLife class that tests the glider pattern
     * by setting the
     * initial state of the grid and checking if the next generation is correct.
     */
    @Test
    public void testGliderPattern() {
        GameOfLife game = new GameOfLife(10, 10);
        game.emptyGrid();
        game.setCellState(2, 1, 1);
        game.setCellState(3, 2, 1);
        game.setCellState(1, 3, 1);
        game.setCellState(2, 3, 1);
        game.setCellState(3, 3, 1);
        game.tick();
        assertFalse(game.getCellState(2, 1));
        assertTrue(game.getCellState(1, 2));
        assertTrue(game.getCellState(3, 2));
        assertTrue(game.getCellState(2, 3));
        assertTrue(game.getCellState(3, 3));
    }
}

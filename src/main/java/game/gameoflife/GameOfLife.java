package game.gameoflife;

import java.util.Random;

/**
 * The GameOfLife class is a Java implementation of Conway's Game of Life, which
 * generates a grid of
 * cells that evolve based on certain rules.
 */
public class GameOfLife {
    private final int rows;
    private final int columns;
    private int[][] grid;
    private final Random random = new Random();

    // This is a constructor for the GameOfLife class that takes in the number of
    // rows and columns for
    // the grid and initializes the instance variables `rows` and `columns` with
    // these values. It also
    // creates a new 2D integer array `grid` with the specified number of rows and
    // columns.
    public GameOfLife(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new int[rows][columns];
    }

    // This is a constructor for the GameOfLife class that takes in a 2D integer
    // array `grid` and
    // initializes the instance variables `grid`, `rows`, and `columns` with the
    // values of `grid`, the
    // number of rows in `grid`, and the number of columns in `grid`, respectively.
    // This constructor
    // allows for the creation of a GameOfLife object with a pre-existing grid
    // configuration.
    public GameOfLife(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.columns = grid[0].length;
    }

    /**
     * The function initializes a 2D grid with random 0s and 1s, where the
     * probability of a cell being
     * 1 is 30%.
     */
    public void init() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = random.nextInt(10) < 7 ? 0 : 1;
            }
        }
    }

    /**
     * This function generates the next generation of a grid based on the rules of
     * Conway's Game of
     * Life.
     */
    public void tick() {
        int[][] next = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int neighbors = countAliveNeighbors(i, j);

                if (neighbors == 3) {
                    next[i][j] = 1;
                } else if (neighbors < 2 || neighbors > 3) {
                    next[i][j] = 0;
                } else {
                    next[i][j] = grid[i][j];
                }
            }
        }
        grid = next;
    }

    /**
     * This function counts the number of alive neighbors surrounding a cell in a 2D
     * grid.
     * 
     * @param i The row index of the cell for which we want to count the number of
     *          alive neighbors.
     * @param j The parameter "j" represents the column index of a cell in a 2D
     *          grid.
     * @return The method is returning an integer value which represents the number
     *         of alive neighbors
     *         of a cell in a 2D grid.
     */
    private int countAliveNeighbors(int i, int j) {
        int sum = 0;
        int iStart = i == 0 ? 0 : -1;
        int iEndInclusive = i == grid.length - 1 ? 0 : 1;
        int jStart = j == 0 ? 0 : -1;
        int jEndInclusive = j == grid[0].length - 1 ? 0 : 1;

        for (int k = iStart; k <= iEndInclusive; k++) {
            for (int l = jStart; l <= jEndInclusive; l++) {
                sum += grid[i + k][j + l];
            }
        }

        sum -= grid[i][j];

        return sum;
    }

    /**
     * The function returns a 2D integer array called "grid".
     * 
     * @return A 2D integer array named "grid" is being returned.
     */
    public int[][] getGrid() {
        return grid;
    }

    /**
     * The function sets the state of a cell in a grid, throwing an exception if the
     * row, column, or
     * state is invalid.
     * 
     * @param row    The row index of the cell in the grid that we want to set the
     *               state of.
     * @param column The column index of the cell whose state is being set.
     * @param state  The state parameter represents the new state that we want to
     *               set for a particular
     *               cell in the grid. It can only be either 0 or 1, where 0
     *               represents a dead cell and 1 represents
     *               a live cell.
     */
    public void setCellState(int row, int column, int state) {
        if (row < 0 || column < 0 || row >= rows || column >= columns) {
            throw new IllegalArgumentException("Invalid row or column index");
        }

        if (state < 0 || state > 1) {
            throw new IllegalArgumentException("Invalid cell state");
        }

        grid[row][column] = state;
    }

    /**
     * This function returns the state of a cell in a grid given its row and column
     * indices.
     * 
     * @param row    The row index of the cell in the grid.
     * @param column The column index of the cell whose state is being retrieved
     *               from the grid.
     * @return The method is returning the state of the cell at the specified row
     *         and column in the
     *         grid.
     */
    public boolean getCellState(int row, int column) {
        if (row < 0 || column < 0 || row >= rows || column >= columns) {
            throw new IllegalArgumentException("Invalid row or column index");
        }

        return grid[row][column] != 0;
    }
}

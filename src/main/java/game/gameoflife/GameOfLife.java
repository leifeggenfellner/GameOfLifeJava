package game.gameoflife;

import java.util.Random;

public class GameOfLife {
    private final int rows;
    private final int columns;
    private int[][] grid;
    private final Random random = new Random();

    public GameOfLife(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new int[rows][columns];
    }

    public GameOfLife(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.columns = grid[0].length;
    }

    public void init() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = random.nextInt(10) < 7 ? 0 : 1;
            }
        }
    }

    public void generateNextGeneration() {
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

    public int[][] getGrid() {
        return grid;
    }
}


package game.gameoflife;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The GameOfLifeCanvas class is responsible for drawing a grid of cells on a
 * canvas using a given
 * GraphicsContext object.
 */
public class GameOfLifeCanvas implements CanvasInterface {

    private int cellSize;
    private final GraphicsContext graphicsContext;

    private final int width;
    private final int height;

    // This is a constructor for the GameOfLifeCanvas class that takes a
    // GraphicsContext object as a
    // parameter. It initializes the graphicsContext field with the passed-in
    // object, and sets the
    // width and height fields to the width and height of the canvas associated with
    // the
    // GraphicsContext object.
    public GameOfLifeCanvas(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        this.width = (int) graphicsContext.getCanvas().getWidth();
        this.height = (int) graphicsContext.getCanvas().getHeight();
    }

    /**
     * This function draws a grid on a canvas based on a 2D array of integers.
     * 
     * @param grid A 2D integer array representing the grid to be drawn. Each
     *             element in the array
     *             represents a cell in the grid, with a value of 0 indicating an
     *             empty cell and a non-zero value
     *             indicating a filled cell.
     */
    public void draw(int[][] grid) {
        cellSize = height / grid.length;
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, width, height);

        graphicsContext.setFill(Color.BLACK);
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] != 0) {
                    graphicsContext.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }

        graphicsContext.setStroke(Color.gray(0.5, 0.5));
        graphicsContext.setLineWidth(1.0);
        for (int y = 0; y <= grid.length; y++) {
            graphicsContext.strokeLine(0, y * cellSize, grid[0].length * cellSize, y * cellSize);
        }
        for (int x = 0; x <= grid[0].length; x++) {
            graphicsContext.strokeLine(x * cellSize, 0, x * cellSize, grid.length * cellSize);
        }
    }
}
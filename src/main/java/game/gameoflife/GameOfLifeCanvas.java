package game.gameoflife;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class GameOfLifeCanvas implements CanvasInterface {

    private int cellSize;
    private final GraphicsContext graphicsContext;

    private final int width;
    private final int height;

    public GameOfLifeCanvas(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        this.width = (int) graphicsContext.getCanvas().getWidth();
        this.height = (int) graphicsContext.getCanvas().getHeight();
    }

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
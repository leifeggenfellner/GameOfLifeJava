package game.gameoflife;

// This code is defining a Java interface called `CanvasInterface`. It has one method called `draw`
// that takes a 2D integer array (`int[][]`) as a parameter and returns nothing (`void`). This
// interface can be implemented by any class that wants to provide a way to draw a grid of cells for
// the Game of Life simulation.
public interface CanvasInterface {
    void draw(int[][] grid);
}

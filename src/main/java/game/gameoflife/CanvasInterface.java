package game.gameoflife;

// This is a Java interface called `CanvasInterface` that defines a method called `draw`. The `draw`
// method takes a 2D integer array as input and does not return anything. The `@param` tag in the
// method's Javadoc documentation explains that the input parameter `grid` is a two-dimensional integer
// array that represents a grid or matrix. The purpose of this interface is to provide a common
// contract for classes that implement it to draw a grid or matrix on some kind of canvas or display.
public interface CanvasInterface {
    /**
     * The function "draw" takes a 2D integer array as input and does not return
     * anything.
     * 
     * @param grid The parameter "grid" is a two-dimensional integer array that
     *             represents a grid or a
     *             matrix. It contains rows and columns of integers that can be used
     *             to represent a game board, a
     *             map, or any other type of grid-based data structure. The function
     *             "draw" takes this grid as
     *             input and
     */
    void draw(int[][] grid);
}

package game.gameoflife;

// This is a Java interface called `FileHandlerInterface` that defines two methods: `saveFile` and
// `loadFile`. The `saveFile` method takes a two-dimensional integer array as input and is responsible
// for saving it to a file. The `loadFile` method returns a two-dimensional integer array that has been
// loaded from a file. Any class that implements this interface must provide implementations for both
// methods.
public interface FileHandlerInterface {
    /**
     * This function saves a two-dimensional integer array to a file.
     * 
     * @param grid The parameter "grid" is a two-dimensional integer array that
     *             represents a grid or
     *             matrix of values. It could be a representation of a game board, a
     *             map, or any other type of
     *             grid-based data structure. The function "saveFile" takes this
     *             grid as input and is responsible
     *             for saving it
     */
    void saveFile(int[][] grid);

    /**
     * The function "loadFile" returns a two-dimensional integer array in Java.
     * 
     * @return A two-dimensional integer array is being returned.
     */
    int[][] loadFile();
}

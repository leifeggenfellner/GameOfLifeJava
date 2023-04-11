package game.gameoflife;

import java.util.concurrent.CompletableFuture;

// This is a Java interface called `FileHandlerInterface` that defines two methods: `saveFile` and
// `loadFile`. The `saveFile` method takes a two-dimensional integer array as input and is responsible
// for saving it to a file. The `loadFile` method returns a two-dimensional integer array that has been
// loaded from a file. Any class that implements this interface must provide implementations for both
// methods.
public interface FileHandlerInterface {

    /**
     * The function "saveFile" takes a 2D integer array as input and does not have
     * any implementation.
     * 
     * @param grid The parameter "grid" is a two-dimensional integer array that
     *             represents a grid or
     *             matrix. It is likely that this method is intended to save the
     *             contents of this grid to a file.
     * 
     */
    static void saveFile(int[][] grid) {
    }

    /**
     * The function "loadFile" returns a CompletableFuture object that will
     * eventually contain a
     * two-dimensional integer array.
     * 
     * @return A `CompletableFuture` object that will eventually contain a
     *         two-dimensional integer
     *         array (`int[][]`). However, the method is currently returning `null`,
     *         so it is not actually
     *         returning anything useful at the moment.
     */
    static CompletableFuture<int[][]> loadFile() {
        return null;
    }
}

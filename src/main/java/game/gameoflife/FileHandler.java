package game.gameoflife;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/**
 * The FileHandler class provides methods for saving and loading a 2D integer
 * array to and from a text
 * file.
 */
public class FileHandler implements FileHandlerInterface {

    /**
     * This function saves a 2D integer array as a text file using a file chooser
     * dialog and displays
     * an alert message with the file path.
     * 
     * @param grid a 2D integer array representing a grid or matrix.
     */
    public static void saveFile(int[][] grid) {
        try {
            String gridString = convertGridToString(grid);

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File file = fileChooser.showSaveDialog(null);

            assert file != null;
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(gridString.getBytes());
            fileOutputStream.close();

            Alert alertType = new Alert(Alert.AlertType.INFORMATION);
            alertType.setTitle("Game Saved");
            alertType.setHeaderText("Game Saved");
            alertType.setContentText("Game saved to " + file.getAbsolutePath());
            alertType.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This Java function converts a 2D integer array into a string with
     * comma-separated values and new
     * line characters.
     * 
     * @param grid The parameter "grid" is a 2D integer array representing a grid of
     *             values.
     * @return The method is returning a string representation of a 2D integer array
     *         (grid) where each
     *         row is separated by a newline character and each element in a row is
     *         separated by a comma.
     */
    private static String convertGridToString(int[][] grid) {
        StringBuilder builder = new StringBuilder();

        for (int[] ints : grid) {
            for (int j = 0; j < grid.length; j++) {
                builder.append(ints[j]);
                if (j < grid.length - 1) {
                    builder.append(",");
                }
            }

            builder.append("\n");
        }

        return builder.toString();
    }

    /**
     * This function converts a 2D ArrayList of integers into a 2D array of
     * integers.
     * 
     * @param arrayListGrid An ArrayList of ArrayLists of Integers, representing a
     *                      2D grid. Each inner
     *                      ArrayList represents a row in the grid, and each Integer
     *                      represents a cell value in that row.
     * @return The method is returning a 2D integer array that is converted from an
     *         ArrayList of
     *         ArrayLists of integers.
     */
    private static int[][] convertArrayListToArray(ArrayList<ArrayList<Integer>> arrayListGrid) {
        int[][] grid;

        int rows = arrayListGrid.size();
        int columns = arrayListGrid.get(0).size();
        grid = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = arrayListGrid.get(i).get(j);
            }
        }

        return grid;
    }

    /**
     * This Java function loads a text file containing integers into a 2D array
     * using a
     * CompletableFuture.
     * 
     * @return A `CompletableFuture` object that will eventually contain a 2D
     *         integer array
     *         representing the contents of a text file selected by the user through
     *         a file chooser dialog.
     */
    public static CompletableFuture<int[][]> loadFile() {
        CompletableFuture<int[][]> future = new CompletableFuture<>();

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File boardFile = fileChooser.showOpenDialog(null);

        if (boardFile == null) {
            future.complete(null);
            return future;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(boardFile.getAbsolutePath()));

            Thread loadThread = new Thread(() -> {
                int[][] grid;
                try {
                    grid = getInts(reader);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                future.complete(grid);
            });
            loadThread.start();

        } catch (IOException e) {
            e.printStackTrace();
            future.complete(null);
        }

        return future;
    }

    /**
     * This function saves a 2D integer array as a string to a file with a given
     * file name in the
     * user's home directory.
     * 
     * @param fileName The name of the file to be saved. It should include the file
     *                 extension (e.g.
     *                 ".txt", ".csv").
     * @param grid     A 2D integer array representing a grid or matrix.
     */
    public void saveFile(String fileName, int[][] grid) {
        try {
            String gridString = FileHandler.convertGridToString(grid);
            FileWriter writer = new FileWriter(fileName);
            writer.write(gridString);
            writer.close();
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file: " + e.getMessage());
        }
    }

    /**
     * The function loads the content of a file with the given file name and returns
     * it as a string.
     * 
     * @param fileName The parameter "fileName" is a String variable that represents
     *                 the name or path
     *                 of the file that needs to be loaded.
     * @return The method is returning a String variable named "fileContent" which
     *         contains the content
     *         of the file that was loaded.
     */
    public int[][] loadFile(String fileName) {
        int[][] grid = null;

        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            grid = getInts(reader);

        } catch (IOException e) {
            System.out.println("An error occurred while loading the file: " + e.getMessage());
        }
        return grid;
    }

    /**
     * This function reads a matrix of integers from a BufferedReader and converts
     * it into a 2D array.
     * 
     * @param reader The parameter "reader" is a BufferedReader object that is used
     *               to read input from
     *               a file or other input stream.
     * @return The method `getInts` is returning a 2D integer array.
     */
    private static int[][] getInts(BufferedReader reader) throws IOException {
        ArrayList<ArrayList<Integer>> arrayListGrid = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] rowValues = line.split(",");
            ArrayList<Integer> row = new ArrayList<>();
            for (String state : rowValues) {
                row.add(Integer.parseInt(state));
            }
            arrayListGrid.add(row);
        }
        reader.close();
        return convertArrayListToArray(arrayListGrid);
    }
}

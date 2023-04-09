package game.gameoflife;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;

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
    public void saveFile(int[][] grid) {
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
    private String convertGridToString(int[][] grid) {
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
     * This function reads a text file containing a grid of integers separated by
     * commas and returns a
     * 2D array of integers.
     *
     * @return The method is returning a 2D integer array representing a grid read
     *         from a text file.
     */
    public int[][] loadFile() {
        int[][] grid = null;

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File boardFile = fileChooser.showOpenDialog(null);

            assert boardFile != null;
            BufferedReader reader = new BufferedReader(new FileReader(boardFile.getAbsolutePath()));

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

            int rows = arrayListGrid.size();
            int columns = arrayListGrid.get(0).size();
            grid = new int[rows][columns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    grid[i][j] = arrayListGrid.get(i).get(j);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return grid;
    }
}

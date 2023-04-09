package game.gameoflife;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * The GameController class controls the game of life simulation, including
 * loading and saving game
 * states, initializing the game, and handling user input for running, stopping,
 * and stepping through
 * generations.
 */
public class GameController implements Initializable {
    @FXML
    private Canvas myCanvas;

    @FXML
    private Button stepButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button runButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button saveButton;

    @FXML
    Button menuButton;

    private GameOfLifeCanvas gameOfLifeCanvas;

    private GameOfLife gameOfLife;

    private GameOfLifeAnimationTimer animationTimer;

    private final int GRID_SIZE = 45;

    /**
     * This function reads a text file containing a grid of integers separated by
     * commas and returns a
     * 2D array of integers.
     * 
     * @return The method is returning a 2D integer array representing a grid read
     *         from a text file.
     */
    private int[][] getBoardFromFile() {
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

    /**
     * This function draws a grid on a canvas using the graphics context and the
     * GameOfLifeCanvas
     * class.
     */
    private void drawGrid() {
        gameOfLifeCanvas = new GameOfLifeCanvas(myCanvas.getGraphicsContext2D());
        gameOfLifeCanvas.draw(gameOfLife.getGrid());
    }

    /**
     * The function resets the game of life and redraws the grid.
     */
    private void handleReset() {
        gameOfLife.init();
        drawGrid();
        animationTimer.stop();
    }

    /**
     * This function converts a 2D integer array into a string with comma-separated
     * values and new line
     * characters.
     * 
     * @return The method `convertGridToString()` returns a string representation of
     *         a 2D integer array
     *         `grid` with each row separated by a newline character and each
     *         element in a row separated by a
     *         comma.
     */
    private String convertGridToString() {
        StringBuilder builder = new StringBuilder();
        int[][] grid = gameOfLife.getGrid();

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
     * This function saves a game to a text file and displays an alert message with
     * the file path.
     */
    private void handleSave() {
        String gridString = convertGridToString();

        try {
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
     * This function loads a new FXML file for the menu and sets it as the scene for
     * the current stage.
     */
    private void handleMenuChange() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("menu.fxml")));
            Stage stage = (Stage) menuButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The function generates the next generation of the Game of Life and draws the
     * grid.
     */
    public void handleStep() {
        gameOfLife.generateNextGeneration();
        drawGrid();
    }

    /**
     * The function loads a game of life by getting a board from a file, creating a
     * new game of life
     * with the board, and drawing the grid.
     */
    public void loadGame() {
        int[][] grid = getBoardFromFile();
        gameOfLife = new GameOfLife(grid);
        drawGrid();
        animationTimer = new GameOfLifeAnimationTimer(gameOfLifeCanvas, gameOfLife);
    }

    /**
     * The function initializes a GameOfLife object with a grid size of GRID_SIZE
     * and draws the grid.
     */
    public void initGame() {
        gameOfLife = new GameOfLife(GRID_SIZE, GRID_SIZE);
        gameOfLife.init();
        drawGrid();
        animationTimer = new GameOfLifeAnimationTimer(gameOfLifeCanvas, gameOfLife);
    }

    /**
     * This function initializes event handlers for various buttons in a Java
     * program.
     * 
     * @param location  The location of the FXML file that contains the UI layout
     *                  for the controller. It
     *                  is a URL object that represents the location of the
     *                  resource.
     * @param resources The ResourceBundle object contains locale-specific
     *                  resources. It is used to
     *                  retrieve localized values for keys in a properties file. In
     *                  JavaFX, it is often used to store
     *                  strings and other resources that are specific to a
     *                  particular language or region.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetButton.setOnAction(event -> handleReset());
        menuButton.setOnAction(event -> handleMenuChange());
        saveButton.setOnAction(event -> handleSave());
        stepButton.setOnAction(event -> handleStep());
        runButton.setOnAction(event -> animationTimer.start());
        stopButton.setOnAction(event -> animationTimer.stop());
    }
}
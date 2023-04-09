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
 * The type Game controller.
 */
public class GameController implements Initializable {
    @FXML
    private Canvas myCanvas;

    @FXML
    private Button stepButton;

    /**
     * Handle step.
     */
    public void handleStep() {
        gameOfLife.generateNextGeneration();
        drawGrid();
    }

    @FXML
    private Button resetButton;

    @FXML
    private void handleReset() {
        gameOfLife.init();
        drawGrid();
    }

    @FXML
    private Button runButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button saveButton;
    /**
     * The Game of life canvas.
     */
    GameOfLifeCanvas gameOfLifeCanvas;
    /**
     * The Game of life.
     */
    GameOfLife gameOfLife;

    /**
     * The Run animation.
     */
    AnimationTimer runAnimation;

    @FXML
    private void handleSave() {
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

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File file = fileChooser.showSaveDialog(null);

            assert file != null;
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(builder.toString().getBytes());
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
     * The Menu button.
     */
    @FXML
    Button menuButton;

    @FXML
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
     * Load game.
     */
    public void loadGame() {
            try {
                int[][] grid = getBoardFromFile();
                gameOfLife = new GameOfLife(grid);

                drawGrid();
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    private int[][] getBoardFromFile() throws IOException {
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
        int[][] grid = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = arrayListGrid.get(i).get(j);
            }
        }

        return grid;
    }

    private void drawGrid() {
        gameOfLifeCanvas = new GameOfLifeCanvas(myCanvas.getGraphicsContext2D());
        gameOfLifeCanvas.draw(gameOfLife.getGrid());
    }



    public void initGame() {
        gameOfLife = new GameOfLife(45, 45);
        gameOfLife.init();
        drawGrid();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        runAnimation = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if ((now - lastUpdate) >= TimeUnit.MILLISECONDS.toNanos(500)) {
                    gameOfLife.generateNextGeneration();
                    drawGrid();
                    lastUpdate = now;
                }
            }
        };

        resetButton.setOnAction(event -> handleReset());
        menuButton.setOnAction(event -> handleMenuChange());
        saveButton.setOnAction(event -> handleSave());
        stepButton.setOnAction(event -> handleStep());
        runButton.setOnAction(event -> runAnimation.start());
        stopButton.setOnAction(event -> runAnimation.stop());
    }
}
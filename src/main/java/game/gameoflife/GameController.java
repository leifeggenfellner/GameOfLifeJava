package game.gameoflife;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

import javafx.scene.control.Button;

import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CompletableFuture;

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
    private Button menuButton;

    private GameOfLifeCanvas gameOfLifeCanvas;

    private GameOfLife gameOfLife;

    private GameOfLifeAnimationTimer animationTimer;

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
     * This function saves the current state of the game grid using a file handler.
     */
    public void handleSave() {
        FileHandler.saveFile(gameOfLife.getGrid());
    }

    /**
     * The function generates the next generation of the Game of Life and draws the
     * grid.
     */
    public void handleStep() {
        gameOfLife.tick();
        drawGrid();
    }

    /**
     * The loadGame function loads a file asynchronously and initializes a
     * GameOfLife object if the
     * file is not null.
     */
    public void loadGame() {
        CompletableFuture<int[][]> future = FileHandler.loadFile();
        int[][] grid = future.join();

        if (grid != null) {
            Platform.runLater(() -> {
                gameOfLife = new GameOfLife(grid);
                drawGrid();
                animationTimer = new GameOfLifeAnimationTimer(gameOfLifeCanvas, gameOfLife);
            });
        } else {
            System.out.println("Error loading file.");
        }
    }

    /**
     * The function initializes a GameOfLife object with a grid size of GRID_SIZE
     * and draws the grid.
     */
    public void initGame() {
        int GRID_SIZE = 45;
        gameOfLife = new GameOfLife(GRID_SIZE, GRID_SIZE);
        gameOfLife.init();
        drawGrid();
        animationTimer = new GameOfLifeAnimationTimer(gameOfLifeCanvas, gameOfLife);
    }

    /**
     * This function initializes various buttons and assigns event handlers to them
     * in a Java program.
     * 
     * @param location  The location of the FXML file that contains the UI layout
     *                  for the controller.
     * @param resources The ResourceBundle parameter is used to access localized
     *                  resources, such as
     *                  strings, images, and other objects, that are specific to a
     *                  particular locale or language. It is
     *                  typically used in internationalized applications to provide
     *                  different versions of the same
     *                  resource for different locales. In this case, it is not
     *                  being used in
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
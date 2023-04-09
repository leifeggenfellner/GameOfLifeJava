package game.gameoflife;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;
import java.net.URL;
import java.util.*;

/**
 * The MenuController class handles the actions for the new game, load game, and
 * exit buttons in a
 * JavaFX application.
 */
public class MenuController implements Initializable {
    @FXML
    Button newGameButton;

    @FXML
    Button loadGameButton;

    @FXML
    Button exitButton;

    /**
     * This function loads a new game of life and initializes it.
     */
    private void handleNewGame() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("gameOfLife.fxml")));
            Parent root = loader.load();
            GameController controller = loader.getController();
            controller.initGame();

            Stage stage = (Stage) newGameButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function loads the game of life grid from a FXML file and sets it as the
     * grid for the next scene.
     */
    private void handleLoadGame() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("gameOfLife.fxml")));
            Parent root = loader.load();
            GameController gameController = loader.getController();
            gameController.loadGame();

            Stage stage = (Stage) loadGameButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function closes the current stage/window in a Java application when the
     * exit button is
     * clicked.
     */
    private void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * The function initializes the actions to be performed when the new game, load
     * game, and exit
     * buttons are clicked.
     * 
     * @param location  The location of the FXML file that contains the UI layout
     *                  for this controller.
     * @param resources The `resources` parameter in the `initialize` method is a
     *                  `ResourceBundle`
     *                  object that contains any resources (such as strings, images,
     *                  etc.) that may be needed by the
     *                  controller class. It is typically used to load localized
     *                  resources based on the user's locale.
     *                  If no resources are needed
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newGameButton.setOnAction(event -> handleNewGame());
        loadGameButton.setOnAction(event -> handleLoadGame());
        exitButton.setOnAction(event -> exit());
    }
}

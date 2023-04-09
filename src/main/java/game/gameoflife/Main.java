package game.gameoflife;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The Main class loads a menu.fxml file and sets it as the scene for a JavaFX
 * stage.
 */
public class Main extends Application {
    /**
     * This is the main function that launches a Java program.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This function loads a menu.fxml file and sets it as the scene for a JavaFX
     * stage.
     * 
     * @param stage The stage parameter is an instance of the Stage class, which
     *              represents the
     *              top-level container for a JavaFX application. It is responsible
     *              for managing the application
     *              window and its associated scene graph.
     */
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("menu.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
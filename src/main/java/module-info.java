module game.gameoflife {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens game.gameoflife to javafx.fxml;

    exports game.gameoflife;
}
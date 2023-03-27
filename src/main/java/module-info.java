module game.gameoflife {
    requires javafx.controls;
    requires javafx.fxml;


    opens game.gameoflife to javafx.fxml;
    exports game.gameoflife;
}
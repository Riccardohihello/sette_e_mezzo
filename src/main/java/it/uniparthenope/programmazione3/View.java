package it.uniparthenope.programmazione3;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class View extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu2.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("sette e mezzo");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //scene.getStylesheets().add(((URL)Objects.requireNonNull(this.getClass().getResource("styles.css"))).toExternalForm());
    }

    public static void main(String[] args) {
        launch();
    }
}
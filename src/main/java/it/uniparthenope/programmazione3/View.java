package it.uniparthenope.programmazione3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class View extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(View.class.getResource("test.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        stage.setTitle("sette e mezzo");
        stage.setScene(scene);
        stage.show();
    }

    // Metodo per cambiare scena
    public static void cambiaScena(String fxml, Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(View.class.getResource(fxml)));
        Scene scene = new Scene(root, 640 , 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
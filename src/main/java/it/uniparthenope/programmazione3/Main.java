package it.uniparthenope.programmazione3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu.fxml")));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("styles.css")).toExternalForm());
            stage.setScene(scene);
            stage.setTitle("sette e mezzo");
            stage.show();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void cambiaScena(String fxml, Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}
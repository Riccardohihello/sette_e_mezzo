package it.uniparthenope.programmazione3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Objects;

public class View extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(View.class.getResource("test2.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load(), 1280.0, 800.0);
        Controller controller = (Controller)fxmlLoader.getController();
        stage.setTitle("sette e mezzo");
        //scene.getStylesheets().add(((URL)Objects.requireNonNull(this.getClass().getResource("styles.css"))).toExternalForm());
        stage.setScene(scene);
        stage.show();
        // Turno turno = new Turno(controller);
    }

    public static void main(String[] args) {
        launch();
    }
}
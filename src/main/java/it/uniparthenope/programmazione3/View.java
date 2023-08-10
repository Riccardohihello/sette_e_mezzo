package it.uniparthenope.programmazione3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class View extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(View.class.getResource("test2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280  , 800);
        Controller controller = fxmlLoader.getController();  // Ottieni l'istanza del controllore
        stage.setTitle("sette e mezzo");
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        stage.setScene(scene);
        stage.show();

        Turno turno = new Turno(controller);

        //Partita partita = new Partita();
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

    }

    public static void main(String[] args) {
        launch();
    }
}
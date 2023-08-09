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
    public void start(Stage stage) throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(View.class.getResource("test2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280  , 800);
        Controller controller = fxmlLoader.getController();  // Ottieni l'istanza del controllore
        stage.setTitle("sette e mezzo");
        stage.setScene(scene);
        stage.show();

        Partita partita = new Partita(controller);

        //Partita partita = new Partita();
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

    }

    public static void main(String[] args) {
        launch();
    }
}
package it.uniparthenope.programmazione3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class ViewControll {
    public static void cambiaScena(String fxml, Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(View.class.getResource(fxml)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

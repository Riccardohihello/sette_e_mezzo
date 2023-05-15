package it.uniparthenope.programmazione3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class Controller {
    @FXML
    private void exitButton(){
        System.exit(0);
    }

    // Metodo per cambiare scena
    @FXML
    public void gameSceneButton(ActionEvent event) throws Exception {
        View.cambiaScena("Game.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}
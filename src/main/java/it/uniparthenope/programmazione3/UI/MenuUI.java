package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class MenuUI {

    // menu iniziale del gioco
    @FXML
    private Label labelRisultato;

    @FXML
    private void initialize() {
        labelRisultato.setText("Menu Del Gioco");
    }

    @FXML
    private void gameSceneButton(ActionEvent event) throws Exception {
        Main.cambiaScena("playerSelection.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }
    @FXML
    private void historySceneButton(ActionEvent event) throws Exception {
        Main.cambiaScena("savedGames.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }
    @FXML
    private void computerStatsButton(ActionEvent event) throws Exception {
        Main.cambiaScena("computerStats.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    private void exitButton() {
        System.exit(0);
    }

}


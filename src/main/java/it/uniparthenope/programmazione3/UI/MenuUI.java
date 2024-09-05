package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.Main;
import it.uniparthenope.programmazione3.game.SettingsSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;


public class MenuUI {
    @FXML
    private Spinner<Integer> spinnerGiocatori;

    @FXML
    private Label labelRisultato;

    @FXML
    private void initialize() {
        labelRisultato.setText("Seleziona il numero di giocatori");
        creaSpinner(spinnerGiocatori, 2, 4, 2);
        spinnerGiocatori.getStyleClass().add("split-arrows-horizontal");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory( 2, 4, 2, 1);
        spinnerGiocatori.setValueFactory(valueFactory);

    }

    public void creaSpinner(Spinner<Integer> spinner, int min, int max, int valoreIniziale) {
        spinner.getStyleClass().add("split-arrows-horizontal");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, valoreIniziale);
        spinner.setValueFactory(valueFactory);
    }

    @FXML
    public void gameSceneButton(ActionEvent event) throws Exception {
        SettingsSingleton.getInstance().setNumeroGiocatori(spinnerGiocatori.getValue());
        Main.cambiaScena("preGame.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    public void exitButton(ActionEvent event) {
        System.exit(0);
    }

}


package it.uniparthenope.programmazione3.controllers;

import it.uniparthenope.programmazione3.View;
import it.uniparthenope.programmazione3.game.SettingsSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;


public class MenuViewController {
    @FXML
    private Spinner<Integer> spinnerGiocatori;

    @FXML
    private Label labelRisultato;

    @FXML
    private void initialize() {
        inizializzaSpinner();
        aggiornaLabelRisultato();
        collegaSpinnerARisultato(spinnerGiocatori);
    }

    private void inizializzaSpinner() {
        inizializzaSpinner(spinnerGiocatori, 2, 4, 2);
    }

    public void inizializzaSpinner(Spinner<Integer> spinner, int min, int max, int valoreIniziale) {
        spinner.getStyleClass().add("split-arrows-horizontal");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, valoreIniziale);
        spinner.setValueFactory(valueFactory);
    }

    private void collegaSpinnerARisultato(Spinner<Integer> spinner) {
        spinner.valueProperty().addListener((observable , valorePrecedente, nuovoValore) ->
                aggiornaLabelRisultato());
    }

    private void aggiornaLabelRisultato() {
        SettingsSingleton.getInstance().setNumeroGiocatori(spinnerGiocatori.getValue());
        labelRisultato.setText("Numero di giocatori selezionati: " + spinnerGiocatori.getValue());
    }

    @FXML
    public void gameSceneButton(ActionEvent event) throws Exception {
        View.cambiaScena("preGame.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    public void exitButton(ActionEvent event) {
        System.exit(0);
    }

}


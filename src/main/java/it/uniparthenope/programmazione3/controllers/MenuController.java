package it.uniparthenope.programmazione3.controllers;

import it.uniparthenope.programmazione3.ViewControll;
import it.uniparthenope.programmazione3.model.ImpostazioniPartita;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;


public class MenuController {
    @FXML
    private Spinner<Integer> spinnerGiocatori;

    @FXML
    private Spinner<Integer> spinnerTurni;

    @FXML
    private Label labelRisultato;

    @FXML
    private void initialize() {
        inizializzaSpinner();
        inizializzaLabelRisultato();
        collegaSpinnerARisultato();
    }

    private void inizializzaSpinner() {
        inizializzaSpinner(spinnerGiocatori, 2, 4, 2);
        inizializzaSpinner(spinnerTurni, 1, 10, 1);
    }

    private void inizializzaSpinner(Spinner<Integer> spinner, int min, int max, int valoreIniziale) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, valoreIniziale);
        spinner.setValueFactory(valueFactory);
    }

    private void collegaSpinnerARisultato() {
        spinnerGiocatori.valueProperty().addListener((observable, valorePrecedente, nuovoValore) ->
                aggiornaLabelRisultato());
        spinnerTurni.valueProperty().addListener((observable, valorePrecedente, nuovoValore) ->
                aggiornaLabelRisultato());
    }

    private void aggiornaLabelRisultato() {
            inizializzaLabelRisultato();
            ImpostazioniPartita.getInstance().aggiornaImpostazioni(spinnerGiocatori.getValue(), spinnerTurni.getValue());
    }
    private void inizializzaLabelRisultato() {
        labelRisultato.setText("Numero di giocatori selezionati: " + spinnerGiocatori.getValue() +
                " e numero di turni selezionati: " + spinnerTurni.getValue());
    }

    @FXML
    public void gameSceneButton(ActionEvent event) throws Exception {
        ViewControll.cambiaScena("game.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    public void exitButton(ActionEvent event) {
        System.exit(0);
    }

}


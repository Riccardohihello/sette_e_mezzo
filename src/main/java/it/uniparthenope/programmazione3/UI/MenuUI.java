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
    private Spinner<Integer> spinnerTurni;

    @FXML
    private Label labelRisultato;

    @FXML
    private void initialize() {
        inizializzaSpinner();
        aggiornaLabelRisultato();
        collegaSpinnerARisultato(spinnerGiocatori);
        collegaSpinnerARisultato(spinnerTurni);
    }

    private void inizializzaSpinner() {
        inizializzaSpinner(spinnerGiocatori, 2, 4, 2);
        inizializzaSpinner(spinnerTurni,1,5,1);
    }

    public void inizializzaSpinner(Spinner<Integer> spinner, int min, int max, int valoreIniziale) {
        spinner.getStyleClass().add("split-arrows-horizontal");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, valoreIniziale);
        spinner.setValueFactory(valueFactory);
    }

    public void collegaSpinnerARisultato(Spinner<Integer> spinner) {
        spinner.valueProperty().addListener((observable , valorePrecedente, nuovoValore) ->
                aggiornaLabelRisultato());
    }

    private void aggiornaLabelRisultato() {
        SettingsSingleton.getInstance().setNumeroGiocatori(spinnerGiocatori.getValue());
        SettingsSingleton.getInstance().setNumeroTurni(spinnerTurni.getValue());
        System.out.println("Numero turni : "+spinnerTurni.getValue()+" tua madre è una troia\n");
        labelRisultato.setText("Numero di giocatori selezionati: " + spinnerGiocatori.getValue());
    }

    @FXML
    public void gameSceneButton(ActionEvent event) throws Exception {
        Main.cambiaScena("preGame.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    public void exitButton(ActionEvent event) {
        System.exit(0);
    }

}


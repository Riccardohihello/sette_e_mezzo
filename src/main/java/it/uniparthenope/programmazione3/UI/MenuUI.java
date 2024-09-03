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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;



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

    private void playSound(String soundFile) {
        Media sound = new Media(new File(soundFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
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
        spinner.valueProperty().addListener((observable , valorePrecedente, nuovoValore) -> {
                playSound("src/main/resources/it/uniparthenope/programmazione3/sounds/191754__leszek_szary__button-5.wav");
                aggiornaLabelRisultato();
        });
    }

    private void aggiornaLabelRisultato() {
        SettingsSingleton.getInstance().setNumeroGiocatori(spinnerGiocatori.getValue());
        SettingsSingleton.getInstance().setNumeroTurni(spinnerTurni.getValue());
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


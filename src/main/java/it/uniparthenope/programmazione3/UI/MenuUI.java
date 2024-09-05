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
    private Label labelRisultato;

    @FXML
    private void initialize() {
        labelRisultato.setText("Seleziona il numero di giocatori");
        creaSpinner(spinnerGiocatori, 2, 4, 2);
        spinnerGiocatori.getStyleClass().add("split-arrows-horizontal");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory( 2, 4, 2, 1);
        spinnerGiocatori.setValueFactory(valueFactory);

    }

    private void playSound(String soundFile) {
        Media sound = new Media(new File(soundFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void creaSpinner(Spinner<Integer> spinner, int min, int max, int valoreIniziale) {
        spinner.getStyleClass().add("split-arrows-horizontal");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, valoreIniziale);
        spinner.setValueFactory(valueFactory);
    }

    public void collegaSpinner(Spinner<Integer> spinner) {

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


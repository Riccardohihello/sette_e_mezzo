package it.uniparthenope.programmazione3.controllers;

import it.uniparthenope.programmazione3.ViewControll;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    private Spinner<Integer> playerSpinner;

    @FXML
    private Spinner<Integer> roundSpinner;

    @FXML
    private Label resultLabel;

    @FXML
    private void initialize() {
        SpinnerValueFactory<Integer> playerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 4, 2);
        playerSpinner.setValueFactory(playerValueFactory);

        SpinnerValueFactory<Integer> roundValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        roundSpinner.setValueFactory(roundValueFactory);
        int selectedPlayers = playerSpinner.getValue();
        int selectedRounds = roundSpinner.getValue();


        // Esegui qui la logica con i valori selezionati

        resultLabel.setText("Numero di giocatori selezionati: " + selectedPlayers +
                " e numero di turni selezionati: " + selectedRounds);

        playerSpinner.valueProperty().addListener((observable, oldValue, newValue) ->
                updateResultLabel(resultLabel, playerSpinner.getValue(), roundSpinner.getValue()));

        roundSpinner.valueProperty().addListener((observable, oldValue, newValue) ->
                updateResultLabel(resultLabel, playerSpinner.getValue(), roundSpinner.getValue()));

    }

    private Spinner<Integer> createSpinner(int min, int max, int initialValue) {
        Spinner<Integer> spinner = new Spinner<>();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, initialValue);
        spinner.setValueFactory(valueFactory);
        return spinner;
    }
    private void updateResultLabel(Label resultLabel, int selectedPlayers, int selectedRounds) {
        resultLabel.setText("Numero di giocatori selezionati: " + selectedPlayers +
                " e numero di turni selezionati: " + selectedRounds);
    }
    public void gameSceneButton(ActionEvent event) throws Exception {
        ViewControll.cambiaScena("game.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }
    public void exitButton(ActionEvent event) {
        System.exit(0);
    }
}

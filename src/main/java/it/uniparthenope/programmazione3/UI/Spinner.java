package it.uniparthenope.programmazione3.UI;

import javafx.scene.control.SpinnerValueFactory;

public class Spinner {

    public static void inizializzaSpinner(javafx.scene.control.Spinner<Integer> spinner, int min, int max, int valoreIniziale, int step) {
        spinner.getStyleClass().add("split-arrows-horizontal");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, valoreIniziale,step);
        spinner.setValueFactory(valueFactory);
    }

}

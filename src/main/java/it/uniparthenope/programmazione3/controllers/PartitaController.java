package it.uniparthenope.programmazione3.controllers;

import it.uniparthenope.programmazione3.model.Computer;
import it.uniparthenope.programmazione3.model.Giocatore;
import it.uniparthenope.programmazione3.model.StatistichePartita;
import it.uniparthenope.programmazione3.model.Turno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;


public class PartitaController  {

        public Button quotaButton;
        public Label quotaLabel;
        @FXML
        private Spinner<Integer> quotaSpinner;
        private Giocatore giocatoreSelezionato;

        private Turno turno;
        @FXML
        ListView<Giocatore> giocatoriSx;
        @FXML
        ListView<Giocatore> giocatoriDx;
        @FXML
        TextField quota;
        @FXML
        private TextArea textArea;
        private final ObservableList<String> carteList = FXCollections.observableArrayList();
        @FXML
        private ListView<String> carteListView;
        @FXML
        private ImageView show = new ImageView();
        @FXML
        private Button pesca;
        @FXML
        private Button stai;

        public void initialize() {
                inizializzaSpinnerQuota();
        }

        @FXML
        private void versaQuotaHandler(ActionEvent event) {

                int quotaVersata = quotaSpinner.getValue(); // Ottieni il valore dallo Spinner
                giocatoreSelezionato.puntataDaVersare(quotaVersata);
                System.out.println(giocatoreSelezionato.getNome() + " ha puntato: " + quotaVersata );

                inizializzaSpinnerQuota();

        }

        public void inizializzaSpinnerQuota(){
                giocatoreSelezionato = StatistichePartita.getInstance().scorriGiocatori();
                quotaLabel.setText(giocatoreSelezionato.getNome() + " inserisci la quota");

                int maxQuota = giocatoreSelezionato.getGettoni(); // Ottieni il numero di gettoni del giocatore

                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, maxQuota, 5,5);
                quotaSpinner.setValueFactory(valueFactory);

                if (giocatoreSelezionato.getNome().equals(Computer.getInstance().getNome())) {
                        quotaSpinner.setVisible(false);
                        quotaButton.setVisible(false);

                        quotaLabel.setText(StatistichePartita.getInstance().scorriGiocatori().getNome() + " tocca a te pescare");
                }
        }


}


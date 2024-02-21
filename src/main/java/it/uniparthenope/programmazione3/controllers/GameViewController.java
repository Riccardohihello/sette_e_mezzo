package it.uniparthenope.programmazione3.controllers;
import it.uniparthenope.programmazione3.game.Giocatore;
import it.uniparthenope.programmazione3.UI.CardUI;
import it.uniparthenope.programmazione3.UI.PlayerUI;
import it.uniparthenope.programmazione3.observerPattern.Partita;
import it.uniparthenope.programmazione3.observerPattern.gameObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.List;

public class GameViewController implements gameObserver {

        public Spinner quotaSpinner;
        private final Partita partita = new Partita();
        public Button button;
        public Label label;
        @FXML
        private final ObservableList<String> carteList = FXCollections.observableArrayList();
        @FXML
        private ListView<String> carteListView;
        @FXML
        private Button pesca;
        @FXML
        private Button stai;

        public void initialize() {
                riempiCarte(carteList);
                partita.addOsservatore(this);
                inizializzaSpinnerQuota();
                pesca.setVisible(false);
                stai.setVisible(false);
                label.setVisible(false);
        }

        @FXML
        private void versaQuotaHandler(ActionEvent event) {
        }

        public void inizializzaSpinnerQuota(){
                quotaSpinner.getStyleClass().add("split-arrows-horizontal");
                label.setText(partita.scorriGiocatori().getNome() + " inserisci la quota");

                int GettoniGiocatore = partita.scorriGiocatori().getGettoni(); // Ottieni il numero di gettoni del giocatore
                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, GettoniGiocatore, 5,5);
                quotaSpinner.setValueFactory(valueFactory);

        }


        public void inizioSfida(){
                button.setVisible(false);
                pesca.setVisible(true);
                stai.setVisible(true);
                label.setText(partita.scorriGiocatori().getNome() + " tocca a te pescare");
        }

        private void modifyButton(){
                label.setVisible(true);
                quotaSpinner.setVisible(true);
                button.setOnAction(this::versaQuotaHandler);
                button.setText("Versa");
        }

        public void riempiLista(ListView<Giocatore> lista, List<Giocatore> giocatori) {
                lista.setItems(FXCollections.observableArrayList(giocatori));
                lista.setCellFactory(param -> new PlayerUI());
                lista.setMouseTransparent(true); // Impedisce la selezione
        }



        public void stai(ActionEvent event) {
        }

        public void riempiCarte(ObservableList<String> imagePath) {
                carteListView.setItems(carteList);
                carteListView.setCellFactory(param -> new CardUI());
        }

        @Override
        public void update( String label) {
                if (label.equals("lista")) {
                } else if (label.equals("addGiocatore")) {
                        System.out.println("Lota!");
                }
        }

}


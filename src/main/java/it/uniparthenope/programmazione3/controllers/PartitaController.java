package it.uniparthenope.programmazione3.controllers;
import it.uniparthenope.programmazione3.UI.CardUI;
import it.uniparthenope.programmazione3.UI.PlayerUI;
import it.uniparthenope.programmazione3.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;


public class PartitaController  {

        public Button button;
        public Label label;
        @FXML
        private Spinner<Integer> quotaSpinner;
        private Giocatore giocatoreSelezionato = getTurno().scorriGiocatori();
        private final ArrayList<Integer> puntate = new ArrayList<>();

        private Turno turno;
        @FXML
        ListView<Giocatore> giocatoriSx;
        @FXML
        ListView<Giocatore> giocatoriDx;
        @FXML
        private TextArea textArea;
        private final ObservableList<String> carteList = FXCollections.observableArrayList();
        @FXML
        private ListView<String> carteListView;
        @FXML
        private Button pesca;
        @FXML
        private Button stai;

        public void initialize() {
                riempiCarte(carteList);
                Mazzo.getInstance().mischia();
                inizializzaSpinnerQuota();
                pesca.setVisible(false);
                stai.setVisible(false);
                quotaSpinner.setVisible(false);
                label.setVisible(false);
        }

        @FXML
        private void versaQuotaHandler(ActionEvent event) {
                int quotaVersata = quotaSpinner.getValue(); // Ottieni il valore dallo Spinner
                getTurno().riempiPiatto(quotaVersata);
                puntate.add(quotaVersata);
                giocatoreSelezionato.puntataDaVersare(quotaVersata);
                textArea.appendText(giocatoreSelezionato.getNome() + " ha puntato: " + quotaVersata + " gettoni\n" );
                scorriGiocatore();

                inizializzaSpinnerQuota();

        }

        public void inizializzaSpinnerQuota(){
                label.setText(giocatoreSelezionato.getNome() + " inserisci la quota");

                int GettoniGiocatore = giocatoreSelezionato.getGettoni(); // Ottieni il numero di gettoni del giocatore

                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, GettoniGiocatore, 5,5);
                quotaSpinner.setValueFactory(valueFactory);

                if (giocatoreSelezionato.getNome().equals(Computer.getInstance().getNome())) {
                        quotaSpinner.setVisible(false);
                        quotaComputer();
                }
        }

        private void quotaComputer(){
                label.setText("il computer sta puntando");
                int quotaComputer = Computer.getInstance().QuotaDaVersare(getTurno().getPiatto(),puntate.size());
                getTurno().riempiPiatto(quotaComputer);
                textArea.appendText(Computer.getInstance().getNome() + " ha puntato: " + quotaComputer + " gettoni\n" );
                inizioSfida();
                scorriGiocatore();
        }

        public void inizioSfida(){
                button.setVisible(false);
                pesca.setVisible(true);
                stai.setVisible(true);

        }

        private void modifyButton(){
                label.setVisible(true);
                quotaSpinner.setVisible(true);
                button.setOnAction(this::versaQuotaHandler);
                button.setText("Versa");
        }

        @FXML
        private void sceltaMazziereHandler(ActionEvent event) {
                getTurno().sceltaMazziere();
                Mazziere mazziere = getTurno().getMazziere();
                textArea.appendText(mazziere.getNome() + " è il mazziere\n" );
                modifyButton();
        }

        public void riempiLista(ObservableList<Giocatore> args) {
                giocatoriSx.setItems(args);
                giocatoriSx.setCellFactory(param -> new PlayerUI());
                giocatoriSx.setMouseTransparent(true); // Impedisce la selezione
        }
        public void pesca(ActionEvent event) {
                Carta carta = getTurno().getMazziere().daiCarte();
                giocatoreSelezionato.addCarta(carta);
                carteList.add(carta.getImagePath());
                textArea.appendText(giocatoreSelezionato.getNome() + " ha pescato " + carta.getSeme() + " " + carta.getValore() +"\n");
                if (giocatoreSelezionato.getMano().getValore() >= 7.5)
                {
                        textArea.setText(giocatoreSelezionato.getNome() + " hai sballato\n");
                        pesca.setVisible(false);
                }
        }
        public void stai(ActionEvent event) {
                giocatoreSelezionato.setStato("Stai");
                textArea.appendText(giocatoreSelezionato.getNome() + " termina con un valore di "+ giocatoreSelezionato.getMano().getValore() + "\n");
                carteList.clear();
                scorriGiocatore();
                pesca.setVisible(true);
        }

        public void scorriGiocatore(){
                this.giocatoreSelezionato = getTurno().scorriGiocatori();
        }
        
        private StatistichePartita getTurno(){
                return StatistichePartita.getInstance();
        }
        private boolean isMazziere() {
                return getTurno().getMazziere().getNome().equals(giocatoreSelezionato.getNome());
        }

        public void riempiCarte(ObservableList<String> imagePath) {
                carteListView.setItems(carteList);
                carteListView.setCellFactory(param -> new CardUI());
        }
}


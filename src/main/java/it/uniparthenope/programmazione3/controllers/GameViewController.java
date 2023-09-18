package it.uniparthenope.programmazione3.controllers;
import it.uniparthenope.programmazione3.UI.CardUI;
import it.uniparthenope.programmazione3.UI.PlayerUI;
import it.uniparthenope.programmazione3.model.*;
import it.uniparthenope.programmazione3.observerPattern.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameViewController implements Observer {

        public Button button;
        public Label label;
        @FXML
        private Spinner<Integer> quotaSpinner;
        private Giocatore giocatoreSelezionato = getTurno().scorriGiocatori();
        private final ArrayList<Integer> puntate = new ArrayList<>();

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
                StatistichePartita.getInstance().setOsservatore(this);
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

                if (getTurno().getIndiceScorrimento() == getTurno().getNumeroGiocatori()) {
                        quotaSpinner.setVisible(false);
                        quotaComputer();
                        inizioSfida();
                }

                scorriGiocatore();
                inizializzaSpinnerQuota();

        }

        public void inizializzaSpinnerQuota(){
                quotaSpinner.getStyleClass().add("split-arrows-horizontal");
                label.setText(giocatoreSelezionato.getNome() + " inserisci la quota");

                int GettoniGiocatore = giocatoreSelezionato.getGettoni(); // Ottieni il numero di gettoni del giocatore

                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, GettoniGiocatore, 5,5);
                quotaSpinner.setValueFactory(valueFactory);

        }

        private void quotaComputer(){
                label.setText("il computer sta puntando");
                int quotaComputer = Computer.getInstance().QuotaDaVersare(getTurno().getPiatto(),puntate.size());
                getTurno().riempiPiatto(quotaComputer);
                textArea.appendText(Computer.getInstance().getNome() + " ha puntato: " + quotaComputer + " gettoni\n" );

                scorriGiocatore();
        }

        public void inizioSfida(){
                button.setVisible(false);
                pesca.setVisible(true);
                stai.setVisible(true);
                label.setText(giocatoreSelezionato.getNome() + " tocca a te pescare");
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


        public void riempiLista(ListView<Giocatore> lista, List<Giocatore> giocatori) {
                lista.setItems(FXCollections.observableArrayList(giocatori));
                lista.setCellFactory(param -> new PlayerUI());
                lista.setMouseTransparent(true); // Impedisce la selezione
        }
        public void pesca(ActionEvent event) {
                Carta carta = getTurno().getMazziere().daiCarte();
                giocatoreSelezionato.aggiungiCarta(carta);
                carteList.add(carta.getImagePath());
                textArea.appendText(giocatoreSelezionato.getNome() + " ha pescato " + carta.getSeme() + " " + carta.getValore() +"\n");
                if (giocatoreSelezionato.getStrategia())
                {
                        textArea.appendText(giocatoreSelezionato.getNome() + " hai sballato\n");
                        pesca.setVisible(false);
                }
        }

        public void pescaMazziere(ActionEvent event) {
                Carta carta = getTurno().getMazziere().daiCarte();
                getmazziere().aggiungiCarta(carta);
                carteList.add(carta.getImagePath());
                textArea.appendText(giocatoreSelezionato.getNome() + " ha pescato " + carta.getSeme() + " " + carta.getValore() +"\n");
                if (isMazziere())
                {
                textArea.appendText(giocatoreSelezionato.getNome() + " hai sballato\n");
                pesca.setVisible(false);
                }
                else
                {
                        pesca.setVisible(true);
                }
        }
        public void stai(ActionEvent event) {
                giocatoreSelezionato.setStato("Stai");
                textArea.appendText(giocatoreSelezionato.getNome() + " termina con un valore di "+ giocatoreSelezionato.getMano().getValore() + "\n");
                carteList.clear();
                scorriGiocatore();
                label.setText(giocatoreSelezionato.getNome() + " tocca a te pescare");
                pesca.setVisible(true);
        }

        public void scorriGiocatore(){
                this.giocatoreSelezionato = getTurno().scorriGiocatori();
        }
        
        private StatistichePartita getTurno(){
                return StatistichePartita.getInstance();
        }
        private Mazziere getmazziere(){
                return getTurno().getMazziere();
        }
        private boolean isMazziere() {
                return getTurno().getMazziere().getNome().equals(giocatoreSelezionato.getNome());
        }

        public void riempiCarte(ObservableList<String> imagePath) {
                carteListView.setItems(carteList);
                carteListView.setCellFactory(param -> new CardUI());
        }

        public void azioniComputer(){
                if(StatistichePartita.getInstance().getIndiceScorrimento() == 1) {
                        quotaComputer();

                }
                else{
                        pescaComputer();
                }

        }

        public void pescaComputer() {
                Timeline timeline = new Timeline();
                int delayBetweenCards = 1000;

                while (Computer.getInstance().getStrategia()) {
                        Carta carta = getmazziere().daiCarte();
                        Computer.getInstance().aggiungiCarta(carta);

                        KeyFrame keyFrame = new KeyFrame(Duration.millis(delayBetweenCards), event -> {
                                carteList.add(carta.getImagePath());
                                textArea.appendText(Computer.getInstance().getNome() + " ha pescato " + carta.getSeme() + " " + carta.getValore() + "\n");
                                statoComputer();
                        });
                        timeline.getKeyFrames().add(keyFrame);

                        delayBetweenCards += 1000;
                }
                // Avvia l'animazione
                timeline.play();
        }

        public void statoComputer() {
                if (Computer.getInstance().getMano().getValore() > 7.5) {
                        textArea.appendText(Computer.getInstance().getNome() + " hai sballato\n");
                } else {
                        textArea.appendText(Computer.getInstance().getNome() + " sta\n");
                }
        }


        @Override
        public void update(StatistichePartita o, String label) {
                if (label.equals("lista")) {
                        int halfSize = o.getGiocatori().size() / 2;
                        List<Giocatore> nomiSx = new ArrayList<>(o.getGiocatori().subList(0, halfSize));
                        List<Giocatore> nomiDx = new ArrayList<>(o.getGiocatori().subList(halfSize, o.getGiocatori().size()));
                        riempiLista(giocatoriDx,nomiDx);
                        riempiLista(giocatoriSx,nomiSx);
                } else if (label.equals("addGiocatore")) {
                        System.out.println("Lota!");
                }
        }
}


package it.uniparthenope.programmazione3.observerPattern;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import it.uniparthenope.programmazione3.UI.CardUI;
import it.uniparthenope.programmazione3.UI.PlayerUI;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

public class GameUI implements gameObserver {

        public Spinner<Integer> quotaSpinner;
        private Partita partita;
        public Button quotaButton;

        @FXML
        public  ImageView flashText;
        public Label quotaLabel;
        public TextArea textArea;
        @FXML
        private final ObservableList<String> carteList = FXCollections.observableArrayList();
        public ListView<Giocatore> giocatoriSx;
        public ListView<Giocatore> giocatoriDx;
        @FXML
        private ListView<String> carteListView;
        @FXML
        private Button pesca;
        @FXML
        private Button stai;
        private int stato = 0;

        public void initialize() {
                partita = new Partita();
                partita.addOsservatore(this);
                pesca.setVisible(false);
                stai.setVisible(false);
                carteListView.setItems(carteList);
                carteListView.setCellFactory(param -> new CardUI());
                creaSpinner(quotaSpinner, 5, 100, 5, 5);
                partita.getGiocatoreAttuale().setStato(Action.bid);
                riempi(partita.getGiocatori());
                flashText.setVisible(true);
                update(Action.bid);


        }


        @FXML
        private void quotaButton() {
                System.out.println("ciaooo");
                if(stato == 1) {
                        int valoreMatta = quotaSpinner.getValue();
                        partita.setMatta(valoreMatta);
                        quotaLabel.setVisible(false);
                        quotaButton.setVisible(false);
                        quotaSpinner.setVisible(false);
                        stato = 0;
                        giocatoriDx.refresh();
                        giocatoriSx.refresh();
                } else {
                        int quotaVersata = quotaSpinner.getValue();
                        partita.setQuota(quotaVersata);
                        riempi(partita.getGiocatori());
                }
        }

        public void riempi(List<Giocatore> giocatori) {
                ArrayList<Giocatore> giocatoriSx = new ArrayList<>();
                ArrayList<Giocatore> giocatoriDx = new ArrayList<>();
                for (int i=0; i<giocatori.size(); i++) {
                        if (i%2==0)
                                giocatoriSx.add(giocatori.get(i));
                        else
                                giocatoriDx.add(giocatori.get(i));
                }
                riempiLista(this.giocatoriDx,giocatoriDx);
                riempiLista(this.giocatoriSx,giocatoriSx);

        }

        public void riempiLista(ListView<Giocatore> lista, List<Giocatore> giocatori) {
                lista.setItems(FXCollections.observableArrayList(giocatori));
                lista.setCellFactory(param -> new PlayerUI());
                lista.setMouseTransparent(true);
        }

        public void stai() {
                partita.stai();
        }

        public void pesca() {
                partita.pesca();
        }


        public void showFlashImage(String path) {
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
                disableInteractiveElements(true);
                flashText.setImage(image);
                flashText.setFitWidth(500);
                flashText.setPreserveRatio(true);
                flashText.setVisible(true);

                FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), flashText);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);

                FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), flashText);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);

                PauseTransition pauseBeforeAction = new PauseTransition(Duration.millis(2000)); // Ritardo di 2 secondi prima dell'inizio delle azioni

                fadeIn.setOnFinished(event -> {
                        pauseBeforeAction.play();
                });

                pauseBeforeAction.setOnFinished(event -> {
                        fadeOut.play();
                        disableInteractiveElements(false);
                });

                pauseBeforeAction.play();
                fadeIn.play();

                fadeOut.setOnFinished(event ->{
                        flashText.setVisible(false);
                });
        }


        private void disableInteractiveElements(boolean disable) {
                pesca.setDisable(disable);
                stai.setDisable(disable);
                quotaButton.setDisable(disable);
                quotaSpinner.setDisable(disable);
        }

        @Override
        public void update(Action action, String... message) {
                Platform.runLater(() -> {
                        switch (action) {
                                case match:
                                        textArea.clear();
                                        textArea.appendText("Inizia la partita:\n");
                                        startMatch();
                                        break;
                                case busted:
                                        handleBusted();
                                        break;
                                case clear:
                                        clearBoard();
                                        break;
                                case results:
                                        disableInteractiveElements(true);
                                        break;
                                case setteMezzo:
                                        showSetteMezzo();
                                        break;
                                case stampa:
                                        textArea.appendText(String.join("", message) + "\n");
                                        break;
                                case bid:
                                        prepareForBid();
                                        break;
                                case pescato:
                                        animazionePescata(String.join("", message));
                                        break;
                                case reset:
                                        initialize();
                                        break;
                                case matta:
                                        stato = 1;
                                        gestisciMatta();
                                        break;
                        }
                        // Refresh delle liste per aggiornare i colori delle card
                        giocatoriDx.refresh();
                        giocatoriSx.refresh();
                });
        }

        private void gestisciMatta() {
                stato = 1;
                quotaLabel.setText("Inserisci il valore per la matta");
                quotaButton.setText("Conferma");
                creaSpinner( quotaSpinner, 1, 10, 1, 1);  // Adatta lo spinner per la matta
                quotaLabel.setVisible(true);
                quotaButton.setVisible(true);
                quotaSpinner.setVisible(true);
        }

        public void creaSpinner(Spinner<Integer> spinner, int min, int max, int valoreIniziale, int passo) {
                quotaSpinner.getStyleClass().add("split-arrows-horizontal");
                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, valoreIniziale, passo);
                quotaSpinner.setValueFactory(valueFactory);
        }


        private void startMatch() {
                showFlashImage("/it/uniparthenope/programmazione3/images/money.gif");

                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> {
                        pesca.setVisible(true);
                        stai.setVisible(true);
                        quotaSpinner.setVisible(false);
                        quotaLabel.setVisible(false);
                        quotaButton.setVisible(false);
                });
                delay.play();
        }

        private void handleBusted() {
                showFlashImage("/it/uniparthenope/programmazione3/images/sballato.png");
                textArea.appendText(partita.getGiocatoreAttuale().getNome() + " ha sballato!\n");

                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> {
                                stai();
                });
                delay.play();
        }

        private void clearBoard() {
                carteList.clear();
                giocatoriDx.refresh();
                giocatoriSx.refresh();
        }

        private void animazionePescata(String message) {
                carteList.add(message);
                giocatoriDx.refresh();
                giocatoriSx.refresh();
                disableInteractiveElements(true);

                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> {
                        if(partita.getGiocatoreAttuale().getNome().equals("Computer"))
                                if(partita.getGiocatoreAttuale().strat())
                                        partita.pesca();
                                else if(!partita.getGiocatoreAttuale().getStato().equals(Action.busted))
                                        partita.stai();
                        disableInteractiveElements(false);

                });
                delay.play();
        }



        private void showResults() {
                pesca.setVisible(false);
                stai.setVisible(false);
                quotaButton.setVisible(false);
                carteListView.setVisible(false);
                carteList.clear();
                quotaLabel.setVisible(true);
                quotaLabel.setText("Risultati");
                giocatoriDx.refresh();
                giocatoriSx.refresh();
        }

        private void showSetteMezzo() {
                showFlashImage("/it/uniparthenope/programmazione3/images/setteMezzo.png");
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> {
                        partita.next();
                });
                delay.play();
        }

        private void prepareForBid() {
                pesca.setVisible(false);
                stai.setVisible(false);
                quotaSpinner.setVisible(true);
                quotaLabel.setVisible(true);
                quotaButton.setVisible(true);
                textArea.appendText("E' il turno di " + partita.getGiocatoreAttuale().getNome() + "\n");
        }
}


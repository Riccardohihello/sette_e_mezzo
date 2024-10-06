package it.uniparthenope.sette_e_mezzo.observerPattern;

import it.uniparthenope.sette_e_mezzo.Main;
import it.uniparthenope.sette_e_mezzo.memento.gameSettings;
import it.uniparthenope.sette_e_mezzo.memento.Caretaker;
import it.uniparthenope.sette_e_mezzo.memento.Memento;
import it.uniparthenope.sette_e_mezzo.strategyPattern.Giocatore;
import it.uniparthenope.sette_e_mezzo.UI.CardCell;
import it.uniparthenope.sette_e_mezzo.StatePattern.PlayerGameCell;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.animation.PauseTransition;



public class GameUI implements gameObserver {

        final boolean IS_VISIBLE = true, IS_NOT_VISIBLE = false;

        public Spinner<Integer> quotaSpinner;
        private final Sounds soundController = new Sounds();
        private Partita partita;
        public Button quotaButton;

        private Caretaker caretaker;

        @FXML
        private   ImageView flashText;

        public Label quotaLabel;

        public TextArea textArea;

        @FXML
        private final ObservableList<String> carteList = FXCollections.observableArrayList();

        public ListView<Giocatore> giocatoriSx;
        public ListView<Giocatore> giocatoriDx;

        @FXML
        private ListView<String> carteListView;
        @FXML
        private Button draw;
        @FXML
        private Button stai;

        public void initialize() {
                partita = new Partita();
                caretaker = new Caretaker();
                
                // inserimento dei dati dei giocatori a schermo
                updatePlayerUI(partita.getGiocatori());

                // inserimento degli osservatori
                partita.addObserver(this);
                partita.addObserver(soundController);

                // creazione del View delle carte
                carteListView.setItems(carteList);
                carteListView.setCellFactory(param -> new CardCell());

                creaSpinner( 5, 100, 5, 5);
                flashText.setVisible(IS_VISIBLE);
                nascondiBottoniPescata(IS_NOT_VISIBLE);
        }

        @FXML
        private void quotaButton() {
                // gestione della pressione del bottone quota
                int quotaVersata = quotaSpinner.getValue();
                partita.setQuota(quotaVersata);
                updatePlayerUI(partita.getGiocatori());
        }

        private void updatePlayerUI(List<Giocatore> giocatori) {
                ArrayList<Giocatore> giocatoriSx = new ArrayList<>();
                ArrayList<Giocatore> giocatoriDx = new ArrayList<>();
                for (int i=0; i<giocatori.size(); i++)
                        if (i%2==0)
                                giocatoriSx.add(giocatori.get(i));
                        else
                                giocatoriDx.add(giocatori.get(i));
                riempiLista(this.giocatoriDx,giocatoriDx);
                riempiLista(this.giocatoriSx,giocatoriSx);

        }

        private void riempiLista(ListView<Giocatore> lista, List<Giocatore> giocatori) {
                lista.setItems(FXCollections.observableArrayList(giocatori));
                lista.setCellFactory(param -> new PlayerGameCell());
                lista.setMouseTransparent(true);
        }

        public void stai() {
                // gestione della pressione del bottone stai
                PauseTransition delay = new PauseTransition(Duration.seconds(0.6));
                delay.setOnFinished(event -> {
                        nascondiBottoniPescata(IS_VISIBLE);
                        carteList.clear();
                        partita.stai();
                });
                delay.play();
        }

        public void pesca() {
                // gestione della pressione del bottone pesca
                partita.pesca();
        }

        private void showFlashImage(String filename) {
                // animazione delle immagini a schermo
                String path = "/it/uniparthenope/sette_e_mezzo/images/";
                filename = path + filename;
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(filename)));
                disableInteractiveElements(true);
                flashText.setImage(image);
                flashText.setVisible(true);

                FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), flashText);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);

                FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), flashText);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);

                fadeIn.setOnFinished(event -> {
                        fadeOut.play();
                        disableInteractiveElements(false);
                });

                fadeOut.setOnFinished(event -> flashText.setVisible(false));

                fadeIn.play();
        }


        private void disableInteractiveElements(boolean disable) {
                draw.setDisable(disable);
                stai.setDisable(disable);
                quotaButton.setDisable(disable);
                quotaSpinner.setDisable(disable);
        }

        @Override
        public void update(Action action, String... message) {
                // aggiornamento dell'interfaccia in base al nessaggio dell'osservatore
                Platform.runLater(() -> {
                        try {
                                switch (action) {
                                        case match:
                                                startMatch(String.join("", message));
                                                break;
                                        case busted:
                                                handleBusted();
                                                break;
                                        case results:
                                                showResults();
                                        case setteMezzo:
                                                showFlashImage("setteMezzo.png");
                                                carteList.clear();
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
                                        case matta:
                                                //gestire la situazione di quando un giocatore imposta un numero per sballare da solo
                                                gestisciMatta();
                                                break;
                                        case saveComputerWin:
                                                caretaker.saveOnDisk("src/main/resources/it/uniparthenope/sette_e_mezzo/storico_computer");
                                }
                        } catch (Exception e) {
                                        throw new RuntimeException(e);
                        }
                        // Refresh delle liste per aggiornare i colori delle card
                        giocatoriDx.refresh();
                        giocatoriSx.refresh();
                        });
        }


        private void gestisciMatta() {
                // modifica dei comandi a schermo per selezionare il valore della matta
                quotaLabel.setText("Inserisci il valore per la matta");
                quotaButton.setText("Conferma");
                creaSpinner( 1, 10, 1, 1);  // Adatta lo spinner per la matta
                nascondiBottoniQuota(IS_VISIBLE);
                nascondiBottoniPescata(IS_NOT_VISIBLE);
        }

        private void impostaValoreMatta(ActionEvent event) {
                partita.setMatta(quotaSpinner.getValue()); // imposto il valore in base a quello selezionato sullo spinner
                nascondiBottoniQuota(IS_NOT_VISIBLE);
                stai.setVisible(true);
                if (partita.getGiocatoreAttuale().getStato() != Action.busted)
                        draw.setVisible(true);
                giocatoriDx.refresh();
                giocatoriSx.refresh();
        }

        private void creaSpinner(int min, int max, int valoreIniziale, int passo) {
                quotaSpinner.getStyleClass().add("split-arrows-horizontal");
                quotaSpinner.setValueFactory( new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, valoreIniziale, passo));
        }

        private void startMatch(String message) {
                // notifica dell'inizio del gioco
                quotaLabel.setText(message);
                textArea.appendText("Inizia la partita:\n");
                quotaButton.setOnAction(this::impostaValoreMatta);
                showFlashImage("money.gif");

                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> {
                        nascondiBottoniPescata(IS_VISIBLE);
                        nascondiBottoniQuota(IS_NOT_VISIBLE);
                });
                delay.play();
        }

        private void handleBusted() {
                // gestione della sconfitta di un giocatore
                textArea.appendText(partita.getGiocatoreAttuale().getNome() + " ha sballato!\n");
                showFlashImage("sballato.png");
                draw.setVisible(IS_NOT_VISIBLE);
        }

        public void disableDraw(boolean disable) {
                draw.setDisable(disable);
                stai.setDisable(disable);
        }

        private void animazionePescata(String message) {
                // delay a schermo dopo la pescata di diversa lunghezza in base al tipo di giocatore
                Giocatore attuale = partita.getGiocatoreAttuale();
                double mills = 0.6;
                if (attuale.getNome().equals("Computer"))
                        mills = 1.0;
                carteList.add(message);
                disableDraw(true);
                PauseTransition delay = new PauseTransition(Duration.seconds(mills));
                delay.setOnFinished(event -> {
                        disableDraw(false);
                        if (attuale.getNome().equals("Computer"))
                                if (attuale.strat() && attuale.getStato() != Action.busted)
                                        pesca();
                                else
                                        stai();
                });
                delay.play();
        }

        private void showResults() throws Exception {
                // salvataggio della partita e passaggio alla scena dei risultati
                try {
                        salvaPartita();
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
                Stage stage = (Stage) textArea.getScene().getWindow();
                Main.cambiaScena("stats.fxml", stage);
        }

        private void nascondiBottoniPescata(boolean bool){
                draw.setVisible(bool);
                stai.setVisible(bool);
        }

        private void nascondiBottoniQuota(boolean bool){
                quotaSpinner.setVisible(bool);
                quotaButton.setVisible(bool);
        }

        private void prepareForBid() {
                // notifica a schermo per il giocatore che deve versare
                nascondiBottoniPescata(IS_NOT_VISIBLE);
                nascondiBottoniQuota(IS_VISIBLE);
                textArea.appendText("E' il turno di " + partita.getGiocatoreAttuale().getNome() + "\n");
        }

        private void salvaPartita() throws IOException {
                caretaker.add(new Memento(gameSettings.getSettings()));
        }


}


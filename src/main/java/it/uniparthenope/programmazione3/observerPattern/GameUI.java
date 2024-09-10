package it.uniparthenope.programmazione3.observerPattern;

import it.uniparthenope.programmazione3.Main;
import it.uniparthenope.programmazione3.game.SettingsSingleton;
import it.uniparthenope.programmazione3.memento.Caretaker;
import it.uniparthenope.programmazione3.memento.Memento;
import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import it.uniparthenope.programmazione3.UI.CardUI;
import it.uniparthenope.programmazione3.UI.PlayerUI;
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
        private Button pesca;
        @FXML
        private Button stai;

        public void initialize() {
                partita = new Partita();
                caretaker = new Caretaker();
                riempi(partita.getGiocatori());

                partita.addOsservatore(this);
                partita.addOsservatore(soundController);

                carteListView.setItems(carteList);
                carteListView.setCellFactory(param -> new CardUI());

                creaSpinner( 5, 100, 5, 5);
                flashText.setVisible(true);
                nascondiBottoniPescata(false);
        }

        @FXML
        private void quotaButton() {
                int quotaVersata = quotaSpinner.getValue();
                partita.setQuota(quotaVersata);
                riempi(partita.getGiocatori());
        }

        private void riempi(List<Giocatore> giocatori) {
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
                lista.setCellFactory(param -> new PlayerUI());
                lista.setMouseTransparent(true);
        }

        public void stai() {
                partita.stai();
        }

        public void pesca() {
                partita.pesca();
        }

        private void showFlashImage(String filename) {
                String path = "/it/uniparthenope/programmazione3/images/";
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
                pesca.setDisable(disable);
                stai.setDisable(disable);
                quotaButton.setDisable(disable);
                quotaSpinner.setDisable(disable);
        }

        @Override
        public void update(Action action, String... message) {
                Platform.runLater(() -> {
                        try {
                                switch (action) {
                                        case match:
                                                startMatch();
                                                break;
                                        case busted:
                                                handleBusted();
                                                break;
                                        case clear:
                                                carteList.clear();
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
                                        case reset:
                                                initialize();
                                                break;
                                        case matta:
                                                //gestire la situazione di quando un giocatore imposta un numero per sballare da solo
                                                gestisciMatta();
                                                break;

                                        case saveComputerWin:
                                                caretaker.saveOnDisk("src/main/resources/it/uniparthenope/programmazione3/storico_computer");
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
                quotaLabel.setText("Inserisci il valore per la matta");
                quotaButton.setText("Conferma");
                creaSpinner( 1, 10, 1, 1);  // Adatta lo spinner per la matta
                nascondiBottoniQuota(true);
                nascondiBottoniPescata(false);
        }

        private void impostaValoreMatta(ActionEvent event) {
                partita.setMatta(quotaSpinner.getValue()); // imposto il valore in base a quello selezionato sullo spinner

                nascondiBottoniQuota(false);
                nascondiBottoniPescata(true);
                giocatoriDx.refresh();
                giocatoriSx.refresh();
        }

        private void creaSpinner(int min, int max, int valoreIniziale, int passo) {
                quotaSpinner.getStyleClass().add("split-arrows-horizontal");
                quotaSpinner.setValueFactory( new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, valoreIniziale, passo));
        }

        private void startMatch() {
                textArea.appendText("Inizia la partita:\n");
                quotaButton.setOnAction(this::impostaValoreMatta);
                showFlashImage("money.gif");

                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> {
                        nascondiBottoniPescata(true);
                        nascondiBottoniQuota(false);
                });
                delay.play();
        }

        private void handleBusted() {
                nascondiBottoniPescata(false);
                textArea.appendText(partita.getGiocatoreAttuale().getNome() + " ha sballato!\n");
                showFlashImage("sballato.png");

                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> {
                                stai();
                                nascondiBottoniPescata(true);
                });
                delay.play();
        }

        private void animazionePescata(String message) {
                carteList.add(message);

                PauseTransition delay = new PauseTransition(Duration.seconds(0.6));
                delay.setOnFinished(event -> {
                        if(partita.getGiocatoreAttuale().getNome().equals("Computer"))
                                if(partita.getGiocatoreAttuale().strat())
                                        partita.pesca();
                });
                delay.play();
        }

        private void showResults() throws Exception {
                try {
                        salvaPartita();
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
                Stage stage = (Stage) textArea.getScene().getWindow();
                Main.cambiaScena("stats.fxml", stage);
        }

        private void nascondiBottoniPescata(boolean bool){
                pesca.setVisible(bool);
                stai.setVisible(bool);
        }

        private void nascondiBottoniQuota(boolean bool){
                quotaSpinner.setVisible(bool);
                quotaLabel.setVisible(bool);
                quotaButton.setVisible(bool);
        }

        private void prepareForBid() {
                nascondiBottoniPescata(false);
                nascondiBottoniQuota(true);
                textArea.appendText("E' il turno di " + partita.getGiocatoreAttuale().getNome() + "\n");
        }

        private void salvaPartita() throws IOException {
                SettingsSingleton settings = SettingsSingleton.getInstance();
                Memento newMemento = new Memento(settings);
                caretaker.add(newMemento);
        }


}


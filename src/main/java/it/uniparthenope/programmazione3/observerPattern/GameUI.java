package it.uniparthenope.programmazione3.observerPattern;

import it.uniparthenope.programmazione3.game.Giocatore;
import it.uniparthenope.programmazione3.UI.CardUI;
import it.uniparthenope.programmazione3.UI.PlayerUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

import static it.uniparthenope.programmazione3.UI.Spinner.inizializzaSpinner;

public class GameUI implements gameObserver {

        public Spinner<Integer> quotaSpinner;
        private final Partita partita = new Partita();
        public Button quotaButton;
        public final ImageView flashText = new ImageView();
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
                partita.addOsservatore(this);
                pesca.setVisible(false);
                stai.setVisible(false);
                carteListView.setItems(carteList);
                carteListView.setCellFactory(param -> new CardUI());
                inizializzaSpinner(quotaSpinner, 5, 100, 5,5);
                partita.getAttuale().setStato(Action.bid);
                riempi(partita.getGiocatori());
                update(Action.bid);


        }

        @FXML
        private void quotaButton() {
                int quotaVersata = quotaSpinner.getValue(); // Ottieni il valore dallo Spinner
                partita.setQuota(quotaVersata);
                riempi(partita.getGiocatori());
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
                lista.setMouseTransparent(true); // Impedisce la selezione
        }

        public void stai() {
                partita.stai();
        }

        public void pesca() {
                partita.pesca();
                giocatoriDx.refresh();
                giocatoriSx.refresh();
                if(partita.getManoGiocatore()!=null)
                        carteList.add(partita.getManoGiocatore());
        }

        public void showFlashImage(String path) {
                flashText.setFitWidth(120);  // Imposta la larghezza desiderata dell'immagine
                flashText.setFitHeight(70); // Imposta l'altezza desiderata dell'immagine
                flashText.setPreserveRatio(true);
                flashText.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(path))));
                flashText.setVisible(true);

                FadeTransition fadeIn = new FadeTransition(Duration.millis(1000),flashText);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
        }
        public void hideFlashImage() {
                FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), flashText);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);

                // Azione da eseguire quando l'animazione di fade-out termina
                fadeOut.setOnFinished(event -> flashText.setVisible(false));

                // Avvio dell'animazione di fade-out
                fadeOut.play();
        }
        @Override
        public void update(Action action,String... message) {
                if (action.equals(Action.match)) {
                        textArea.clear();
                        textArea.appendText("Inizio partita\nE'il turno di "+partita.getAttuale().getNome()+"\n");
                        partita.getAttuale().setStato(Action.match);
                        pesca.setVisible(true);
                        stai.setVisible(true);
                        quotaSpinner.setVisible(false);
                        quotaLabel.setVisible(false);
                        quotaButton.setVisible(false);
                } else if (action.equals(Action.busted)) {
                        showFlashImage("/it/uniparthenope/programmazione3/images/sballato.png");
                        //hideFlashImage();
                        pesca.setVisible(false);
                        textArea.appendText("Il giocatore "+partita.getAttuale().getNome()+" ha sballato!\n");
                } else if (action.equals(Action.clear)) {
                        pesca.setVisible(true);
                        carteList.clear();
                        giocatoriDx.refresh();
                        giocatoriSx.refresh();
                } else if (action.equals(Action.results)) {
                        pesca.setVisible(false);
                        stai.setVisible(false);
                        carteList.clear();
                        quotaLabel.setVisible(true);
                        quotaLabel.setText("Risultati");
                        giocatoriDx.refresh();
                        giocatoriSx.refresh();
                } else if (action.equals(Action.computer)) {
                        carteListView.refresh();
                } else if (action.equals(Action.stampa)) {
                        textArea.appendText(String.join("", message) + "\n");
                } else if (action.equals(Action.bid)) {
                        pesca.setVisible(false);
                        stai.setVisible(false);
                        inizializzaSpinner(quotaSpinner, 5, 100, 5,5);
                        quotaSpinner.setVisible(true);
                        quotaLabel.setVisible(true);
                        quotaButton.setVisible(true);
                        partita.getAttuale().setStato(Action.bid);
                        textArea.appendText("E' il turno di "+partita.getAttuale().getNome()+"\n");
                        if (partita.getAttuale().getNome()=="Computer")
                                partita.setQuota(0);

                }
        }
}


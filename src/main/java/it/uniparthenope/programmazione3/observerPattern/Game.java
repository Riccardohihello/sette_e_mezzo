package it.uniparthenope.programmazione3.observerPattern;
import it.uniparthenope.programmazione3.game.Giocatore;
import it.uniparthenope.programmazione3.UI.CardUI;
import it.uniparthenope.programmazione3.UI.PlayerUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

import static it.uniparthenope.programmazione3.UI.Spinner.inizializzaSpinner;

public class Game implements gameObserver {

        public Spinner<Integer> quotaSpinner;
        private final Partita partita = new Partita();
        public Button quotaButton;
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
                inizializzaSpinner(quotaSpinner, 5, partita.gettoni_giocatore(), 5,5);
        }

        @FXML
        private void quotaButton() {
                int quotaVersata = quotaSpinner.getValue(); // Ottieni il valore dallo Spinner
                partita.setQuota(quotaVersata);
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

        }

        public void riempiLista(ListView<Giocatore> lista, List<Giocatore> giocatori) {
                lista.setItems(FXCollections.observableArrayList(giocatori));
                lista.setCellFactory(param -> new PlayerUI());
                lista.setMouseTransparent(true); // Impedisce la selezione
        }



        public void stai(ActionEvent event) {
        }
        public void pesca(ActionEvent event) {
                partita.pesca();
        }

        @Override
        public void update(Action action) {
                if (action.equals(Action.next))
                        System.out.println("add");
                else if (action.equals(Action.match)) {
                        pesca.setVisible(true);
                        stai.setVisible(true);
                        quotaSpinner.setVisible(false);
                        quotaLabel.setVisible(false);
                        quotaButton.setVisible(false);
                }
        }
}


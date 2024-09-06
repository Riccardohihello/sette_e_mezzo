package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.Main;
import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import it.uniparthenope.programmazione3.game.SettingsSingleton;
import it.uniparthenope.programmazione3.observerPattern.Action;
import it.uniparthenope.programmazione3.observerPattern.gameObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class GameLoadingUI implements gameObserver {

    @FXML
    public Spinner<Integer> spinnerGiocatori;
    @FXML
    private Button avviaPartita;
    @FXML
    public ListView<Giocatore> giocatoriSx;
    public ListView<Giocatore> giocatoriDx;

    private ObservableList<Giocatore> giocatori;

    @FXML
    public void initialize() {
        creaSpinner(spinnerGiocatori, 1, 5, 1);
        spinnerGiocatori.getStyleClass().add("split-arrows-horizontal");
        giocatori = FXCollections.observableArrayList();

        giocatoriSx.setItems(giocatori);
        giocatoriDx.setItems(giocatori);
        giocatoriSx.setCellFactory(param -> new PlayerCreationUI());
        giocatoriDx.setCellFactory(param -> new PlayerCreationUI());

        giocatori.add(new Giocatore("Computer"));

        updatePlayerList(1);
        spinnerGiocatori.valueProperty().addListener((obs, oldValue, newValue) -> updatePlayerList(newValue));
        avviaPartita.setVisible(false);
    }

    @FXML
    //Handler del pulsante avvia partita
    public void avviaPartita(ActionEvent event) throws Exception {
        SettingsSingleton.getInstance().setNumeroGiocatori(giocatori.size());
        SettingsSingleton.getInstance().setListaGiocatori(new ArrayList<>(giocatori));
        Main.cambiaScena("game.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow() );
    }
    public void creaSpinner(Spinner<Integer> spinner, int min, int max, int valoreIniziale) {
        spinner.getStyleClass().add("split-arrows-horizontal");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, valoreIniziale);
        spinner.setValueFactory(valueFactory);
    }

    private void updatePlayerList(int numberOfPlayers) {
        int currentSize = giocatori.size();
        numberOfPlayers = numberOfPlayers + 1;
        if (numberOfPlayers > currentSize) {
            for (int i = currentSize; i < numberOfPlayers; i++) {
                Giocatore newPlayer = new Giocatore("Player " + (i));
                giocatori.add(newPlayer);
            }
        } else if (numberOfPlayers < currentSize) {
            giocatori.subList(numberOfPlayers, currentSize).clear();
        }



        riempi(giocatori);
        if (numberOfPlayers > 2) {
            avviaPartita.setVisible(true);
        } else { avviaPartita.setVisible(false); }
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
        lista.setCellFactory(param -> new PlayerCreationUI());
    }


    @Override
    public void update(Action action, String... message) {

    }
}



package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.game.SettingsSingleton;
import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class StatsUI {
    @FXML
    private ListView<String> Results;

    public void initialize() {
        setPlayerStats(SettingsSingleton.getInstance().getListaGiocatori());
    }

    public void setPlayerStats(ArrayList<Giocatore> listaGiocatori) {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Giocatore giocatore : listaGiocatori) {
            System.out.println(giocatore.getNome());
            items.add(giocatore.getNome() + " - Punti: " + giocatore.getMano().getValore());
        }
        Results.setItems(items);
    }

    @FXML
    private void nuovaPartita() {
    }

    @FXML
    private void rigiocaPartita() {
    }

    @FXML
    private void esciGioco() {
    }
}

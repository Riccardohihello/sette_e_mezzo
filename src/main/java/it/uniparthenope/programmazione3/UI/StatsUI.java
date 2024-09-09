package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.game.SettingsSingleton;
import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class StatsUI {
    @FXML
    private ListView<Giocatore> vincitori;

    @FXML
    private ListView<Giocatore> sconfitti;

    @FXML
    private ListView<Giocatore> mazziere;


    @FXML
    public void initialize() {
        setPlayerStats();
        vincitori.setCellFactory(param -> new PlayerUI());
        sconfitti.setCellFactory(param -> new PlayerUI());
        mazziere.setCellFactory(param -> new PlayerUI());
    }

    public void setPlayerStats() {
        vincitori.setItems(FXCollections.observableArrayList(SettingsSingleton.getInstance().getWinners()));
        vincitori.setItems(FXCollections.observableArrayList(SettingsSingleton.getInstance().getLosers()));
        mazziere.setItems(FXCollections.observableArrayList(SettingsSingleton.getInstance().getMazziere()));

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

package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.Main;
import it.uniparthenope.programmazione3.game.SettingsSingleton;
import it.uniparthenope.programmazione3.observerPattern.Action;
import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

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
        sconfitti.setItems(FXCollections.observableArrayList(SettingsSingleton.getInstance().getLosers()));
        mazziere.setItems(FXCollections.observableArrayList(SettingsSingleton.getInstance().getMazziere()));
    }

    @FXML
    public void nuovaPartita(ActionEvent event) throws Exception {
            Main.cambiaScena("playerSelection.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    public void rigiocaPartita(ActionEvent event) throws Exception {
        Main.cambiaScena("game.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());

    }

    @FXML
    public void backToMenu(ActionEvent event) throws Exception {
        Main.cambiaScena("menu.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}

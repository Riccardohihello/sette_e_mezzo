package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.Main;
import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import it.uniparthenope.programmazione3.game.SettingsSingleton;
import it.uniparthenope.programmazione3.observerPattern.Action;
import it.uniparthenope.programmazione3.observerPattern.gameObserver;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;



public class GameLoadingUI implements gameObserver {

    @FXML
    ListView<Giocatore> prePartita;
    @FXML
    private Label mainLabel;
    @FXML
    private Button avviaPartita;
    @FXML
    private TextField formPlayer;
    @FXML
    private Label secondLabel;
    private int priorità = 0;

    @FXML
    public void initialize() {
        avviaPartita.setVisible(false);
        secondLabel.setText("Numero giocatori umani: " + SettingsSingleton.getInstance().getNumeroGiocatori());
    }

    @FXML
    //Handler del pulsante avvia partita
    public void avviaPartita(ActionEvent event) throws Exception {
        Main.cambiaScena("game.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow() );
    }
    @FXML
    //Metodo per l'inserimento dei giocatori. Necessario per inserire dinamicamente i players
    public void riempiPlayers() {
        Giocatore giocatore = new Giocatore(formPlayer.getText());
        SettingsSingleton.getInstance().addGiocatore(giocatore);

        riempiLista(SettingsSingleton.getInstance().getListaGiocatori());

        int numeroMassimoGiocatori = SettingsSingleton.getInstance().getNumeroGiocatori();
        boolean tuttiGiocatoriAggiunti = SettingsSingleton.getInstance().getListaGiocatori().size() >= numeroMassimoGiocatori;

        aggiornaInterfacciaUtente(tuttiGiocatoriAggiunti);
    }

    private void aggiornaInterfacciaUtente(boolean tuttiGiocatoriAggiunti) {
        if (tuttiGiocatoriAggiunti) {
            Giocatore computer = new Giocatore("Computer");
            SettingsSingleton.getInstance().addGiocatore(computer);
            mainLabel.setText("Giocatori inseriti!");
            formPlayer.setVisible(false);
            avviaPartita.setVisible(true);
        }
        formPlayer.clear();
    }

    //Metodo generico per riempire una listView, controllo interno per vedere se è carta o lista giocatori
    public void riempiLista(ObservableList<Giocatore> args) {
            prePartita.setItems(args);
            prePartita.setCellFactory(param -> new PlayerUI());
            prePartita.setMouseTransparent(true); // Impedisce la selezione
        }

    @Override
    public void update(Action action, String... message) {

    }
}



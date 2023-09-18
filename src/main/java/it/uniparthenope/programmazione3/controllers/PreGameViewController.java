package it.uniparthenope.programmazione3.controllers;

import it.uniparthenope.programmazione3.UI.Cell;
import it.uniparthenope.programmazione3.View;
import it.uniparthenope.programmazione3.model.Giocatore;
import it.uniparthenope.programmazione3.model.StatistichePartita;
import it.uniparthenope.programmazione3.observerPattern.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class PreGameViewController implements Observer {

    @FXML
    ListView<String> prePartita;
    @FXML
    private Label mainLabel;
    @FXML
    private Button avviaPartita;
    @FXML
    private TextField formPlayer;
    @FXML
    private Label secondLabel;
    ObservableList<String> giocatori = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        riempiLista(this.giocatori);
        System.out.println("Setto osservatore PreGame");
        StatistichePartita.getInstance().setOsservatore(this);
        //Inizializzo variabili scena
        avviaPartita.setVisible(false);
    }

    @FXML
    //Handler del pulsante avvia partita
    public void avviaPartita(ActionEvent event) throws Exception {
        View.cambiaScena("game.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow() );
    }
    @FXML
    //Metodo per l'inserimento dei giocatori. Necessario per inserire dinamicamente i players
    public void riempiPlayers() {
        String nomeGiocatore = formPlayer.getText();

        if (!nomeGiocatore.isEmpty() || !(StatistichePartita.getInstance().getNomiGiocatori().size() >= StatistichePartita.getInstance().getNumeroGiocatori())) {

            aggiungiGiocatore(nomeGiocatore);

            int numeroMassimoGiocatori = StatistichePartita.getInstance().getNumeroGiocatori();
            boolean tuttiGiocatoriAggiunti = StatistichePartita.getInstance().getNomiGiocatori().size() == numeroMassimoGiocatori;

            aggiornaInterfacciaUtente(tuttiGiocatoriAggiunti, numeroMassimoGiocatori);
        }
    }

    private void aggiungiGiocatore(String nomeGiocatore) {
        StatistichePartita.getInstance().addGiocatore(nomeGiocatore);
        giocatori.add(nomeGiocatore);
        formPlayer.clear();
    }

    private void aggiornaInterfacciaUtente(boolean tuttiGiocatoriAggiunti, int numeroMassimoGiocatori) {
        if (tuttiGiocatoriAggiunti) {
            giocatori.add("COMPUTER");
            mainLabel.setText("Giocatori inseriti!");
            formPlayer.setVisible(false);
            avviaPartita.setVisible(true);
        } else {
            mainLabel.setText("Inserisci nome:");
        }

        secondLabel.setText("Numero giocatori: " + numeroMassimoGiocatori);
    }


    //Metodo generico per riempire una listView, controllo interno per vedere se è carta o lista giocatori
    public void riempiLista(ObservableList<String> args) {
            prePartita.setItems(args);
            prePartita.setCellFactory(param -> new Cell());
            prePartita.setMouseTransparent(true); // Impedisce la selezione
    }
    @Override
    public void update(StatistichePartita o, String label) {
         if (label.equals("addGiocatore")) {
            System.out.println("Lota!");
        }
    }
}


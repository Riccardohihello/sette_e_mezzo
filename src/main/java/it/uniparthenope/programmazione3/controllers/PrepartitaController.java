package it.uniparthenope.programmazione3.controllers;

import it.uniparthenope.programmazione3.FactoryPattern.Cell;
import it.uniparthenope.programmazione3.ViewControll;
import it.uniparthenope.programmazione3.model.StatistichePartita;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class PrepartitaController {


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

    @FXML
    //Handler del pulsante avvia partita
    public void avviaPartita(ActionEvent event) throws Exception {
        //Nasconde il pulsante appena cliccato, cambia la mainLabel e avvia il Turno
        avviaPartita.setVisible(false);
        mainLabel.setText("Partita Iniziata!");

        ViewControll.cambiaScena("partita.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow() );
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
        formPlayer.clear();
        riempiLista(prePartita, StatistichePartita.getInstance().getNomiGiocatori());
    }

    private void aggiornaInterfacciaUtente(boolean tuttiGiocatoriAggiunti, int numeroMassimoGiocatori) {
        if (tuttiGiocatoriAggiunti) {
            StatistichePartita.getInstance().addComputer();
            riempiLista(prePartita, StatistichePartita.getInstance().getNomiGiocatori());
            mainLabel.setText("Giocatori inseriti!");
            formPlayer.setVisible(false);
            avviaPartita.setVisible(true);
        } else {
            mainLabel.setText("Inserisci nome:");
        }

        secondLabel.setText("Numero giocatori: " + numeroMassimoGiocatori);
    }


    //Metodo generico per riempire una listView, controllo interno per vedere se è carta o lista giocatori
    public void riempiLista(ListView<String> lista, ObservableList<String> args) {
            lista.setItems(args);
            lista.setCellFactory(param -> new Cell());
            lista.setMouseTransparent(true); // Impedisce la selezione
    }
    @FXML
    public void initialize() {
       //Inizializzo variabili scena
        avviaPartita.setVisible(false);
    }


}


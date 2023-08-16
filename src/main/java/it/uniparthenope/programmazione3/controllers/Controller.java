package it.uniparthenope.programmazione3.controllers;

import it.uniparthenope.programmazione3.ViewControll;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import java.io.IOException;
import java.util.Objects;


public class Controller {


    @FXML
    ListView<String> prePartita;
    public ObservableList<String> nomiGiocatori = FXCollections.observableArrayList();
    @FXML
    private Label mainLabel;
    @FXML
    private Button avviaPartita;
    @FXML
    private TextField formPlayer;
    private int index = 0;
    @FXML
    private Label secondLabel;


    private Stage stage;
    private Scene scene;
    private Parent root;

    //REIMPLEMENTAZIONE DELLE CLASSI CELL DI LIST VIEW DA SISTEMARE!
    static class Cell extends ListCell<String> {
        VBox hbox = new VBox();
        Label nameLabel = new Label("");
        Label saldoLabel = new Label("");
        ImageView img = new ImageView();

        public Cell() {
            super();
            img.setFitWidth(140);  // Imposta la larghezza desiderata dell'immagine
            img.setFitHeight(80); // Imposta l'altezza desiderata dell'immagine
            img.setPreserveRatio(true);
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().addAll(img, nameLabel, saldoLabel);
            setGraphic(hbox);
        }

        public void updateItem(String name, boolean empty) {
            super.updateItem(name, empty);
            setText(null);

            if (name != null && !empty) {
                nameLabel.setText(name);
                saldoLabel.setText("Aggiunto");
                img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/uniparthenope/programmazione3/images/avatar.png"))));
                setGraphic(hbox);
            } else {
                setGraphic(null);
            }
        }

    }
    @FXML
    //Handler del pulsante avvia partita
    public void avviaPartita(ActionEvent event) throws IOException {
        //Nasconde il pulsante appena cliccato, cambia la mainLabel e avvia il Turno
        avviaPartita.setVisible(false);
        mainLabel.setText("Partita Iniziata!");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/uniparthenope/programmazione3/test2.fxml"));
        root = loader.load();
        MatchController matchController = loader.getController();
        matchController.avviaTurno(nomiGiocatori);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    //Metodo per l'inserimento dei giocatori. Necessario per inserire dinamicamente i players
    public void riempiPlayers() {
        //Primo input servirà per inserire il numero di giocatori
        if (index == 0 ) {
            index = Integer.parseInt(formPlayer.getText());
            formPlayer.setMaxWidth(300);
            mainLabel.setText("Inserisci nome: \n(Inserisci Paolo se vuoi pescare)");
            secondLabel.setText("Numero giocatori: "+index);
            formPlayer.clear();
            //Secondo input inserisce index nomi nella lista
        } else if (nomiGiocatori.size() < index) {
            nomiGiocatori.add(formPlayer.getText());
            formPlayer.clear();
            riempiLista(prePartita, nomiGiocatori);
            if (nomiGiocatori.size() == index) {
                mainLabel.setText("Giocatori inseriti!");
                formPlayer.setVisible(false);
                avviaPartita.setVisible(true);
            }
        }
    }

    //Metodo generico per riempire una listView, controllo interno per vedere se è carta o lista giocatori
    public void riempiLista(ListView<String> lista, ObservableList<String> args) {
            lista.setItems(args);
            lista.setCellFactory(param -> new Cell());
            //lista.setMouseTransparent(true); // Impedisce la selezione
    }
    @FXML
    public void initialize() {
       //Inizializzo variabili scena
        mainLabel.setText("Benvenuto, quanti giocatori siete?");
        avviaPartita.setVisible(false);
    }
    @FXML
    private void exitButton() {
        System.exit(0);
    }
    // Metodo per cambiare scena
    @FXML
    public void gameSceneButton(ActionEvent event) throws Exception {
        ViewControll.cambiaScena("game.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }


}


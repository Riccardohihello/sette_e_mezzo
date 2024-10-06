package it.uniparthenope.sette_e_mezzo.UI;

import it.uniparthenope.sette_e_mezzo.Main;
import it.uniparthenope.sette_e_mezzo.strategyPattern.Giocatore;
import it.uniparthenope.sette_e_mezzo.memento.gameSettings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;


public class PlayerSelectionUI {

    // scena d'intermezzo per la creazione dei giocatori
    // prima dell'inizio del gioco

    @FXML
    private Label vittorieComputer;
    @FXML
    public Spinner<Integer> spinnerGiocatori;
    @FXML
    private Button avviaPartita;

    @FXML
    public ListView<Giocatore> giocatoriSx = new ListView<>();

    @FXML
    public ListView<Giocatore> giocatoriDx = new ListView<>();

    private final ObservableList<Giocatore> giocatori = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        spinnerGiocatori.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1));
        spinnerGiocatori.getStyleClass().add("split-arrows-horizontal");
        File file = new File("src/main/resources/it/uniparthenope/sette_e_mezzo/storico_computer");
        File[] files = file.listFiles();
        if(files != null)
            vittorieComputer.setText("Vittorie Computer: "+ (Objects.requireNonNull(file.list())).length);
        else vittorieComputer.setText("Vittorie Computer: 0");
        giocatoriSx.setCellFactory(param -> new PlayerCreationCell());
        giocatoriDx.setCellFactory(param -> new PlayerCreationCell());

        giocatori.add(new Giocatore("Computer"));
        giocatoriSx.getItems().add(giocatori.get(0));

        spinnerGiocatori.valueProperty().addListener((obs, oldValue, newValue) -> updatePlayerList(oldValue, newValue));
        avviaPartita.setVisible(false);

    }

    @FXML
    //Handler del pulsante avvia partita
    public void avviaPartita(ActionEvent event) throws Exception {
        gameSettings.getSettings().setListaGiocatori(new ArrayList<>(giocatori));
        Main.cambiaScena("game.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow() );
    }


    private void updatePlayerList(int oldValue, int newValue) {
        if (oldValue < newValue) {
            Giocatore newPlayer = new Giocatore("Player " + (giocatori.size() + 1));
            giocatori.add(newPlayer);
            // sei il numero di giocatori è pari aggiungo a giocatoriSx altrimenti a giocatoriDx
            (giocatori.size() % 2 == 1 ? giocatoriSx : giocatoriDx).getItems().add(newPlayer);
        } else {
            Giocatore lastPlayer = giocatori.remove(giocatori.size() - 1);
            // sei il numero di giocatori è pari rimuovo da giocatoriSx altrimenti da giocatoriDx
            (giocatori.size() % 2 == 0 ? giocatoriSx : giocatoriDx).getItems().remove(lastPlayer);
        }
        avviaPartita.setVisible((giocatoriDx.getItems().size() + giocatoriSx.getItems().size()) > 2);
    }


    @FXML
    public void backToMenu(ActionEvent event) throws Exception {
        Main.cambiaScena("menu.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}



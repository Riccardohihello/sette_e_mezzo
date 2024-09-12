package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.Main;
import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import it.uniparthenope.programmazione3.memento.gameSettings;
import it.uniparthenope.programmazione3.observerPattern.Action;
import it.uniparthenope.programmazione3.observerPattern.gameObserver;
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


public class PlayerSelectionUI implements gameObserver {

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
        File file = new File("src/main/resources/it/uniparthenope/programmazione3/storico_computer");
        File[] files = file.listFiles();
        if(files != null)
            vittorieComputer.setText("Vittorie Computer: "+ (Objects.requireNonNull(file.list())).length);
        else vittorieComputer.setText("Vittorie Computer: 0");
        giocatoriSx.setCellFactory(param -> new PlayerCreationUI());
        giocatoriDx.setCellFactory(param -> new PlayerCreationUI());

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


    @Override
    public void update(Action action, String... message) {

    }
    @FXML
    public void backToMenu(ActionEvent event) throws Exception {
        Main.cambiaScena("menu.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}



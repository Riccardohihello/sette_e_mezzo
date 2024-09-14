package it.uniparthenope.sette_e_mezzo.UI;

import it.uniparthenope.sette_e_mezzo.Main;
import it.uniparthenope.sette_e_mezzo.memento.Caretaker;
import it.uniparthenope.sette_e_mezzo.memento.Memento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class ComputerStatsUI {
    @FXML
    private Label vittorieComputer;
    @FXML
    private ListView<Memento> History;
    private final Caretaker caretaker = new Caretaker();

    public void initialize() throws IOException {
        String path = "src/main/resources/it/uniparthenope/sette_e_mezzo/storico_computer";
        File directory = new File(path);
        if (!directory.exists())
            if (directory.mkdirs())
                System.out.println("Directory creata: " + path);
            else
                throw new IOException("Impossibile creare la directory: " + path);
        visualizzaStorico();
        History.setCellFactory(listView -> new ResultsCell());
        vittorieComputer.setText("Vittorie Computer: "+ Objects.requireNonNull(new File(path).list()).length);

    }

    @FXML
    private void visualizzaStorico() {
        History.getItems().setAll(caretaker.loadFromDisk("src/main/resources/it/uniparthenope/sette_e_mezzo/storico_computer"));
    }
    @FXML
    public void backToMenu(ActionEvent event) throws Exception {
        Main.cambiaScena("menu.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }

}

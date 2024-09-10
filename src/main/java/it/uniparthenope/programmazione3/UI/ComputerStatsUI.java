package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.Main;
import it.uniparthenope.programmazione3.memento.Caretaker;
import it.uniparthenope.programmazione3.memento.Memento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class ComputerStatsUI {
    @FXML
    private ListView<Memento> History;
    private final Caretaker caretaker = new Caretaker();

    public void initialize(){
        visualizzaStorico();
        History.setCellFactory(listView -> new ResultsCellUI());
    }

    @FXML
    private void visualizzaStorico() {
        History.getItems().setAll(caretaker.loadFromDisk("src/main/resources/it/uniparthenope/programmazione3/storico_computer"));
    }
    @FXML
    public void backToMenu(ActionEvent event) throws Exception {
        Main.cambiaScena("menu.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }

}

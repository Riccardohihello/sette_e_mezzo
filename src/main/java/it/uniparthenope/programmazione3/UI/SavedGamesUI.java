package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.Main;
import it.uniparthenope.programmazione3.memento.Caretaker;
import it.uniparthenope.programmazione3.memento.Memento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class SavedGamesUI {
    @FXML
    private ListView<Memento> History;
    private final Caretaker caretaker = new Caretaker();

    public void initialize(){
        visualizzaStorico();
        History.setCellFactory(listView -> new ResultsCell());
    }

    @FXML
    private void visualizzaStorico() {
        if (!caretaker.getMementoList().isEmpty()) {
            History.getItems().setAll(caretaker.getMementoList());
        } else {
            System.out.println("Nessuna partita salvata.");
        }
    }
    @FXML
    public void backToMenu(ActionEvent event) throws Exception {
        Main.cambiaScena("menu.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }

}

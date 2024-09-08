package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.memento.Caretaker;
import it.uniparthenope.programmazione3.memento.Memento;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class SavedGamesUI {
    @FXML
    private ListView<Memento> History;
    private final Caretaker caretaker = new Caretaker();

    public void initialize(){
        visualizzaStorico();
        History.setCellFactory(listView -> new ResultsCellUI());
    }

    @FXML
    private void visualizzaStorico() {
        if (!caretaker.getMementoList().isEmpty()) {
            History.getItems().setAll(caretaker.getMementoList());
        } else {
            System.out.println("Nessuna partita salvata.");
        }
    }

}

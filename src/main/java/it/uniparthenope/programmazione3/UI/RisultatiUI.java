package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.memento.Caretaker;
import it.uniparthenope.programmazione3.memento.Memento;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;

public class RisultatiUI {
    @FXML
    private ListView<Memento> History; // Usare il ListView definito in FXML
    private final Caretaker caretaker = new Caretaker();
    private final String saveFilePath = "storico_partite/salvataggi.dat";

    public void initialize() throws IOException {
        visualizzaStorico();
        History.setCellFactory(listView -> new ResultsCellUI());
    }

    @FXML
    private void visualizzaStorico() throws IOException {
        if (!caretaker.getMementoList().isEmpty()) {
            History.getItems().setAll(caretaker.getMementoList());
        } else {
            System.out.println("Nessuna partita salvata.");
        }
    }

}

package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.Main;
import it.uniparthenope.programmazione3.game.SettingsSingleton;
import it.uniparthenope.programmazione3.memento.Memento;
import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ResultsCellUI extends ListCell<Memento> {

    private final VBox vbox = new VBox(10);
    private final Label gameIdLabel = new Label();
    private final Button loadGame = new Button("Load game");
    private boolean isExpanded = false;

    public ResultsCellUI() {
        super();
        vbox.getChildren().addAll(gameIdLabel, loadGame);
        setGraphic(vbox);
        setStyle("-fx-background-color: #f5f5dc; -fx-border-radius: 5; -fx-background-radius: 5");
        gameIdLabel.setStyle("-fx-text-fill: #333333;");

        // Handle the button action
        loadGame.setOnAction(event -> {
            Memento memento = getItem();
            if (memento != null) {
                try {
                    System.out.println("Load game " + memento.getSettings().getSaveDateTime());
                    caricaPartitaSelezionata(memento, event);
                } catch (Exception e) {
                    e.printStackTrace();  // Log the exception or handle it as needed
                }
            }
        });

        setOnMouseClicked(event -> toggleExpanded());
    }

    @Override
    protected void updateItem(Memento memento, boolean empty) {
        super.updateItem(memento, empty);

        if (empty || memento == null) {
            setText(null);
            setGraphic(null);
        } else {
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy hh-mm-ss");
            String formattedDate = memento.getSettings().getSaveDateTime().format(myFormatObj);

            gameIdLabel.setText("Partita " + (getIndex() + 1) + " - " + formattedDate);

            if (isExpanded) {
                List<Giocatore> giocatori = memento.getSettings().getListaGiocatori();
                VBox playersBox = new VBox(5);
                for (Giocatore giocatore : giocatori) {
                    HBox playerRow = new HBox(10);
                    playerRow.setAlignment(Pos.CENTER_LEFT);
                    Label playerName = new Label(giocatore.getNome() + ": ");
                    Label playerTokens = new Label(giocatore.getGettoni() + " gettoni");
                    playerName.setStyle("-fx-text-fill: #333333;");
                    playerTokens.setStyle("-fx-text-fill: #333333;");
                    playerRow.getChildren().addAll(playerName, playerTokens);
                    playersBox.getChildren().add(playerRow);
                }
                vbox.getChildren().add(playersBox);
            } else {
                // Remove player details if expanded
                if (vbox.getChildren().size() > 2) {
                    vbox.getChildren().remove(2, vbox.getChildren().size());
                }
            }

            setGraphic(vbox);
        }
    }

    private void toggleExpanded() {
        isExpanded = !isExpanded;
        updateItem(getItem(), isEmpty());
    }

    private void caricaPartitaSelezionata(Memento memento, ActionEvent event) throws Exception {
        SettingsSingleton.setInstance(memento.getSettings());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Main.cambiaScena("game.fxml", stage);
        System.out.println("Partita caricata con successo.");
        // Aggiungi logica per riprendere la partita se necessario
    }
}

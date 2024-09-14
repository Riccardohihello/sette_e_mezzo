package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.Main;
import it.uniparthenope.programmazione3.UI.StatePattern.PlayerGameCell;
import it.uniparthenope.programmazione3.memento.gameSettings;
import it.uniparthenope.programmazione3.memento.Memento;
import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;


public class ResultsCell extends ListCell<Memento> {

    private final VBox vbox = new VBox(10);
    private final Label gameIdLabel = new Label();
    private boolean isExpanded = false;

    public ResultsCell() {
        super();
        Region spacer = new Region();
        HBox hBox = new HBox(5);
        Button loadGame = new Button("Load game");
        hBox.getChildren().addAll(gameIdLabel, spacer, loadGame);
        hBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        vbox.getChildren().addAll(hBox);
        vbox.setStyle("-fx-background-color: white;-fx-background-radius: 15;-fx-padding: 10");
        setGraphic(vbox);
        setStyle("-fx-background-color: #f5f5dc; -fx-background-radius: 15");
        gameIdLabel.setStyle("-fx-text-fill: #333333;");

        loadGame.setOnAction(event -> {
            Memento memento = getItem();
            if (memento != null) {
                try {
                    System.out.println("Load game " + memento.getSettings().getSaveDateTime());
                    caricaPartitaSelezionata(memento, event);
                } catch (Exception e) {
                    e.printStackTrace();
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
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM hh:mm");
            String formattedDate = memento.getSettings().getSaveDateTime().format(myFormatObj);

            gameIdLabel.setText("Partita " + (getIndex() + 1) + " - " + formattedDate);

            if (isExpanded) {
                vbox.setStyle("-fx-background-color: #e1dfdf; -fx-background-radius: 15;-fx-padding: 10");
                ListView<Giocatore> vincitori = new ListView<>();
                ListView<Giocatore> sconfitti = new ListView<>();
                ListView<Giocatore> mazziere = new ListView<>();
                vincitori.setItems(FXCollections.observableArrayList(memento.getSettings().getWinners()));
                sconfitti.setItems(FXCollections.observableArrayList(memento.getSettings().getLosers()));
                mazziere.setItems(FXCollections.observableArrayList(memento.getSettings().getMazziere()));

                vincitori.setCellFactory(param -> new PlayerGameCell());
                sconfitti.setCellFactory(param -> new PlayerGameCell());
                mazziere.setCellFactory(param -> new PlayerGameCell());

                vincitori.setStyle( "-fx-background-color: #c8f4ba; -fx-background-radius: 15");
                sconfitti.setStyle("-fx-background-color: #f3c6c6; -fx-background-radius: 15");

                VBox winCol = new VBox(10);
                Label win = new Label("Vincitori");
                win.setStyle(" -fx-text-alignment: CENTER; -fx-text-fill: black");
                VBox lossCol = new VBox(10);
                Label loss = new Label("Sconfitti");
                loss.setStyle(" -fx-text-alignment: CENTER; -fx-text-fill: black");
                VBox mazzCol = new VBox(10);
                Label mazz = new Label("Mazziere");
                mazz.setStyle(" -fx-text-alignment: CENTER; -fx-text-fill: black");
                winCol.getChildren().addAll(win, vincitori);
                winCol.setAlignment(Pos.CENTER);
                lossCol.getChildren().addAll(loss, sconfitti);
                lossCol.setAlignment(Pos.CENTER);
                mazzCol.getChildren().addAll(mazz,mazziere);
                mazzCol.setAlignment(Pos.CENTER);
                Label countTurni = new Label();
                countTurni.setStyle(" -fx-text-alignment: CENTER; -fx-text-fill: black");
                countTurni.setText("Turni Giocati: " + memento.getSettings().getCountTurni() + "\nRisultati ultimo turno: ");

                HBox players = new HBox(3);
                players.setAlignment(Pos.CENTER);
                players.getChildren().addAll(winCol, lossCol, mazzCol);
                vbox.getChildren().addAll(countTurni,players);
            } else {
                if (vbox.getChildren().size() > 1) {
                    vbox.getChildren().remove(1, vbox.getChildren().size());
                    vbox.setStyle("-fx-background-color: white; -fx-background-radius: 15;-fx-padding: 10");

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
        gameSettings.updateSettings(memento.getSettings());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Main.cambiaScena("game.fxml", stage);
        System.out.println("Partita caricata con successo.");
    }
}


package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.game.Giocatore;
import it.uniparthenope.programmazione3.observerPattern.Action;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.util.Objects;

public class PlayerUI extends ListCell<Giocatore> {
    VBox vbox = new VBox();
    Label nameLabel = new Label("");
    Label saldoLabel = new Label("");
    Label statoLabel = new Label("");
    ImageView img = new ImageView();

    public PlayerUI() {
        super();
        img.setFitWidth(120);  // Imposta la larghezza desiderata dell'immagine
        img.setFitHeight(70); // Imposta l'altezza desiderata dell'immagine
        img.setPreserveRatio(true);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(img, nameLabel, saldoLabel,statoLabel);
        setGraphic(vbox);
    }

    public void updateItem(Giocatore giocatore, boolean empty) {
        super.updateItem(giocatore, empty);
        setText(null);

        if (giocatore!= null && !empty) {
            nameLabel.setText(giocatore.getNome());
            nameLabel.setStyle("-fx-fill: lightseagreen;");
            nameLabel.setStyle("-fx-text-fill: #00ff55;-fx-font-weight: bold");
            if (Action.bid.equals(giocatore.getStato())) {
                saldoLabel.setText("offri");
            } else if(Action.bidded.equals(giocatore.getStato())){
                saldoLabel.setText("wait");
            } else {
                statoLabel.setStyle(""); // Rimuove eventuali stili precedenti
                saldoLabel.setStyle("");
            }
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/uniparthenope/programmazione3/images/avatar.png"))));
            setGraphic(vbox);
        } else {
            setGraphic(null);
        }
    }

}

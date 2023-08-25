package it.uniparthenope.programmazione3.FactoryPattern;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class Cell extends ListCell<String> {
    VBox hbox = new VBox();
    Label nameLabel = new Label("");
    Label saldoLabel = new Label("");
    ImageView img = new ImageView();

    public Cell() {
        super();
        img.setFitWidth(140);  // Imposta la larghezza desiderata dell'immagine
        img.setFitHeight(80); // Imposta l'altezza desiderata dell'immagine
        img.setPreserveRatio(true);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(img, nameLabel, saldoLabel);
        setGraphic(hbox);
    }

    public void updateItem(String name, boolean empty) {
        super.updateItem(name, empty);
        setText(null);

        if (name != null && !empty) {
            nameLabel.setText(name);
            saldoLabel.setText("Aggiunto");
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/uniparthenope/programmazione3/images/avatar.png"))));
            setGraphic(hbox);
        } else {
            setGraphic(null);
        }
    }

}
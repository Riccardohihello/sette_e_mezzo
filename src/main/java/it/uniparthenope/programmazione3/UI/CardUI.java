package it.uniparthenope.programmazione3.UI;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class CardUI extends ListCell<String> {
    private final ImageView img = new ImageView();

    public CardUI() {
        super();
        img.setFitWidth(100);  // Imposta la larghezza desiderata dell'immagine
        img.setFitHeight(150); // Imposta l'altezza desiderata dell'immagine
        img.setPreserveRatio(true);
        setGraphic(img);

    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            img.setImage(null);
        } else {
            // Carica l'immagine della carta in base all'item (potresti dover creare una logica specifica)
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(item)));
            img.setImage(image);
        }
    }

}
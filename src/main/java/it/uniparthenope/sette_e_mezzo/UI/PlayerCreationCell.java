package it.uniparthenope.sette_e_mezzo.UI;


import it.uniparthenope.sette_e_mezzo.strategyPattern.Giocatore;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

import java.util.Objects;

public class PlayerCreationCell extends ListCell<Giocatore> {


    private final HBox hbox = new HBox(10); // spacing tra l'immagine e il testo
    private final TextField nameField = new TextField();
    private final ImageView img = new ImageView();
    private final Spinner<Integer> gettoni;
    public PlayerCreationCell() {
        super();
        img.setFitHeight(30);
        img.setFitWidth(30);
        img.setPreserveRatio(true);

        gettoni = new Spinner<>(0, 100, 50);
        gettoni.setEditable(true);
        gettoni.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 50));
        gettoni.getEditor().setTextFormatter(new TextFormatter<>(new NumberStringConverter()));

        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(nameField, gettoni);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().addAll(img, vbox);
        vbox.setAlignment(Pos.CENTER);


        setGraphic(hbox);
        hbox.setStyle("-fx-background-color: #f5f5dc; -fx-border-radius: 5; -fx-background-radius: 5");
        vbox.maxWidth(70);
    }


    @Override
    protected void updateItem(Giocatore player, boolean empty) {
        super.updateItem(player, empty);

        if (empty || player == null) {
            setText(null);
            setGraphic(null);
        } else {
            nameField.setText(player.getNome());
            nameField.textProperty().addListener((obs, oldName, newName) -> player.setNome(newName));

            gettoni.getValueFactory().setValue(player.getGettoni());
            gettoni.valueProperty().addListener((obs, oldValue, newValue) -> player.setGettoni(newValue));

            String imagePath = "/it/uniparthenope/sette_e_mezzo/images/";
            try {
                if (player.getNome().equals("Computer")) {
                    img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + "cpu.png"))));
                    nameField.setEditable(false);
                } else
                    img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + "player.png"))));
            } catch (NullPointerException e) {
                System.err.println("Image not found: " + e.getMessage());
            }
            setGraphic(hbox);
        }
    }

}
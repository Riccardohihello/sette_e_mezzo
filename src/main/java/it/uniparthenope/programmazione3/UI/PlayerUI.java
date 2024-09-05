package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import it.uniparthenope.programmazione3.strategyPattern.StrategiaMazziere;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Objects;

public class PlayerUI extends ListCell<Giocatore> {
    private static final String DEFAULT_BACKGROUND_COLOR = "#f5f5dc";
    private static final String DEALER_BACKGROUND_COLOR = "#0a55a6";
    private static final String BORDER_RADIUS = "5px";
    private static final String DEFAULT_TEXT_COLOR = "#2a2828";
    private static final String BORDER_GREEN = "#28a745";
    private static final String BORDER_RED = "#dc3545";

    HBox hbox = new HBox(10); // spacing tra l'immagine e il testo
    VBox vbox = new VBox(5); // spacing tra le etichette
    Label nameLabel = new Label("");
    Label balanceLabel = new Label("");
    Label stateLabel = new Label("");
    ImageView img = new ImageView();

    public PlayerUI() {
        super();
        img.setFitWidth(70);
        img.setFitHeight(70);
        img.setPreserveRatio(true);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(nameLabel, balanceLabel, stateLabel);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().addAll(img, vbox);
        setGraphic(hbox);

        setDefaultStyles();
    }

    private void setDefaultStyles() {
        hbox.setStyle(String.format("-fx-background-color: %s; -fx-border-radius: %s; -fx-background-radius: %s", DEFAULT_BACKGROUND_COLOR, BORDER_RADIUS, BORDER_RADIUS));
        vbox.maxWidth(70);
        setTextStyle(nameLabel, DEFAULT_TEXT_COLOR, true);
        setTextStyle(balanceLabel, DEFAULT_TEXT_COLOR, false);
        setTextStyle(stateLabel, DEFAULT_TEXT_COLOR, false);
    }

    private void setTextStyle(Label label, String color, boolean bold) {
        label.setStyle(String.format("-fx-text-fill: %s; %s", color, bold ? "-fx-font-weight: bold" : ""));
    }

    private void setBackgroundColor(String color) {
        hbox.setStyle(String.format("-fx-background-color: %s; -fx-border-radius: %s; -fx-background-radius: %s", color, BORDER_RADIUS, BORDER_RADIUS));
    }

    private void setBorderColor(String color) {
        hbox.setStyle(String.format("-fx-background-color: %s; -fx-border-color: %s; -fx-border-radius: %s; -fx-background-radius: %s; -fx-border-width: 5;", DEFAULT_BACKGROUND_COLOR, color, BORDER_RADIUS, BORDER_RADIUS));
    }

    @Override
    public void updateItem(Giocatore player, boolean empty) {
        super.updateItem(player, empty);
        setText(null);

        if (player != null && !empty) {
            nameLabel.setText(player.getNome());
            balanceLabel.setText("Gettoni: " + player.getGettoni());
            setBorderColor(DEFAULT_BACKGROUND_COLOR);
            setTextStyle(stateLabel, DEFAULT_TEXT_COLOR, true);
            setTextStyle(balanceLabel, DEFAULT_TEXT_COLOR, true);
            if (player.getStrategia() instanceof StrategiaMazziere) {
                setBackgroundColor(DEALER_BACKGROUND_COLOR);
            } else
                setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
            if (player.getStato() != null) {
                switch (player.getStato()) {
                    case bid:
                        stateLabel.setText("Deve versare");
                        setBorderColor(BORDER_GREEN);
                        setTextStyle(stateLabel, BORDER_GREEN, true);
                        break;

                    case match:
                        stateLabel.setText("Gioca");
                        setBorderColor(BORDER_GREEN);
                        setTextStyle(stateLabel, BORDER_GREEN, true);
                        stateLabel.setText("Valore mano: " + player.getMano().getValore());
                        break;

                    case bidded:
                        stateLabel.setText("Versato...");
                        break;

                    case wait:
                        stateLabel.setText("In attesa...");
                        break;

                    case busted:
                        setBorderColor(BORDER_RED);
                        stateLabel.setText("Sballato!");
                        break;

                    case results:
                        balanceLabel.setVisible(true);
                        stateLabel.setVisible(true);
                        stateLabel.setText("Valore mano: " + player.getMano().getValore());
                        break;

                    default:
                        setDefaultStyles();
                        break;
                }
            }
            String imagePath = "/it/uniparthenope/programmazione3/images/";
            if (player.getNome().equals("Computer"))
                img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + "cpu.png"))));
            else if (player.getStrategia() instanceof StrategiaMazziere){
                img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + "dealer.png"))));
            } else
                img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + "player.png"))));
            setGraphic(hbox);
        } else {
            setGraphic(null);
        }
    }
}
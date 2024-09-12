package it.uniparthenope.programmazione3.UI.playerStates;

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
    public static final String DEFAULT_BACKGROUND_COLOR = "#f5f5dc";
    public static final String DEALER_BACKGROUND_COLOR = "#0a55a6";
    public static final String BORDER_RADIUS = "5px";
    public static final String DEFAULT_TEXT_COLOR = "#2a2828";
    public static final String BORDER_GREEN = "#28a745";
    public static final String BORDER_RED = "#dc3545";

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
    }

    public void setTextStyle(Label label, String color) {
        label.setStyle(String.format("-fx-text-fill: %s; %s", color,"-fx-font-weight: bold"));
    }

    public void setBackgroundColor(String color) {
        hbox.setStyle(String.format("-fx-background-color: %s; -fx-border-radius: %s; -fx-background-radius: %s", color, BORDER_RADIUS, BORDER_RADIUS));
    }

    public void setBorderColor(String color) {
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
            setTextStyle(nameLabel, DEFAULT_TEXT_COLOR);
            setTextStyle(stateLabel, DEFAULT_TEXT_COLOR);
            setTextStyle(balanceLabel, DEFAULT_TEXT_COLOR);

            if (player.getStrategia() instanceof StrategiaMazziere) {
                setBackgroundColor(DEALER_BACKGROUND_COLOR);
            } else {
                setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
            }

            // Gestione dello stato tramite il pattern State
            PlayerState state = switch (player.getStato()) {
                case bid -> new BidState();
                case match -> new MatchState();
                case bidded -> new BiddedState();
                case wait -> new WaitState();
                case busted -> new BustedState();
                case results -> new ResultsState();
                default -> null;
            };
            if (state != null) {
                state.updateState(this, player);
            }

            String imagePath = "/it/uniparthenope/programmazione3/images/";
            String imageName;
            if (player.getNome().equals("Computer")) {
                imageName = "cpu.png";
            } else if (player.getStrategia() instanceof StrategiaMazziere) {
                imageName = "dealer.png";
            } else {
                imageName = "player.png";
            }
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + imageName))));
            setGraphic(hbox);
        } else {
            setGraphic(null);
        }
    }
}
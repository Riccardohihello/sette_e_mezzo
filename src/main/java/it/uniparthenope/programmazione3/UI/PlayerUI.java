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
    Label ruolo = new Label("");
    Label saldoLabel = new Label("");
    Label statoLabel = new Label("");
    ImageView img = new ImageView();

    public PlayerUI() {
        super();
        img.setFitWidth(120);  // Imposta la larghezza desiderata dell'immagine
        img.setFitHeight(70); // Imposta l'altezza desiderata dell'immagine
        img.setPreserveRatio(true);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(img, nameLabel, saldoLabel,statoLabel,ruolo);
        setGraphic(vbox);

        vbox.setStyle("-fx-background-color: #FF0000; -fx-border-radius: 10px;-fx-background-radius: 10px" );
        nameLabel.setStyle("-fx-text-fill: #2a2828;-fx-font-weight: bold");
        saldoLabel.setStyle("-fx-text-fill: #2a2828");
        statoLabel.setStyle("-fx-text-fill: #2a2828");
        ruolo.setStyle("-fx-text-fill: #2a2828");
    }

    private void check_state_color(Action stato) {
        if (stato.equals(Action.bid) || stato.equals(Action.match))
            statoLabel.setStyle("-fx-text-fill: #17cb17;");
        else
            statoLabel.setStyle("-fx-text-fill: #2a2828");

    }
    public void updateItem(Giocatore giocatore, boolean empty) {
        super.updateItem(giocatore, empty);
        setText(null);

        if (giocatore!= null && !empty) {
            nameLabel.setText(giocatore.getNome());
            saldoLabel.setText("Gettoni: " + giocatore.getGettoni());
            if (giocatore.isMazziere)
                    ruolo.setText("Mazziere");
            else ruolo.setText("Player");
            if (giocatore.getStato() != null)
                check_state_color(giocatore.getStato());
            if (Action.bid.equals(giocatore.getStato())) {
                statoLabel.setText("Deve versare");
            } else if (Action.match.equals(giocatore.getStato())){
                statoLabel.setText("Gioca");
                saldoLabel.setText("Valore mano: "+giocatore.getMano().getValore());
            } else if (Action.bidded.equals(giocatore.getStato())) {
                statoLabel.setText("Versato...");
            } else if (Action.wait.equals(giocatore.getStato())) {
                statoLabel.setText("In attesa..");
            } else if (Action.busted.equals(giocatore.getStato())) {
                statoLabel.setText("Sballato!");
            } else if (Action.results.equals(giocatore.getStato())) {
                saldoLabel.setVisible(true);
                statoLabel.setVisible(true);
                statoLabel.setText("Valore mano: " + giocatore.getMano().getValore());
        } else {
                saldoLabel.setStyle("-fx-text-fill: #2a2828");
                statoLabel.setStyle("-fx-text-fill: #2a2828");
            }
            img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/uniparthenope/programmazione3/images/avatar.png"))));
            setGraphic(vbox);
        } else {
            setGraphic(null);
        }
    }

    public void setRuolo(String ruolo) {
        this.ruolo.setText(ruolo);
    }

}

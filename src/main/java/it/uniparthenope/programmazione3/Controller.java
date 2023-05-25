package it.uniparthenope.programmazione3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Controller {
    Mazzo mazzo = Mazzo.creaMazzo();

    @FXML
    private ImageView cardImage;
    private Image image;

    private String getCartaImagePath(Carta carta) {
        String seme = carta.getSeme();
        String valore = String.valueOf((int)carta.getValore());
        char inizialeSeme = Character.toUpperCase(seme.charAt(0));
        String nomeCartella = Character.toUpperCase(inizialeSeme) + seme.substring(1);
        return "src/main/resources/it/uniparthenope/programmazione3/images/Carte/" + nomeCartella + "/" + valore + inizialeSeme + ".png";
    }
    public void initialize() {
        //Di default imposta la carta come girata verifico di trovarmi nella scena in cui viene usato cardImage
        //Per evitare di trovare null
        if (cardImage != null) {
            try {
                Image defaultImage = new Image(new FileInputStream("src/main/resources/it/uniparthenope/programmazione3/images/Carte/Retro.png"));
                cardImage.setImage(defaultImage);
            } catch (FileNotFoundException e) {
                // Gestisci l'eccezione se l'immagine predefinita non può essere trovata
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void exitButton(){
        System.exit(0);
    }


    // Metodo per cambiare scena
    @FXML
    public void gameSceneButton(ActionEvent event) throws Exception {
        ViewControll.cambiaScena("test.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }
    @FXML
    public void precedente() {
        Carta carta = this.mazzo.previous();
        try {
            String imagePath = getCartaImagePath(carta);
            image = new Image(new FileInputStream(imagePath));
            cardImage.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Valore: " + carta.getValore() + " Seme: " + carta.getSeme() + " Iteratore: " + this.mazzo.getIteratorPosition());
    }
    @FXML
    public void successiva() {
        Carta carta = this.mazzo.next();
        try {
            String imagePath = getCartaImagePath(carta);
            image = new Image(new FileInputStream(imagePath));
            cardImage.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Valore: " + carta.getValore() + " Seme: " + carta.getSeme() + " Iteratore: " + this.mazzo.getIteratorPosition());
    }
    @FXML
    public void mischia() {
        //Mischia e resetta l'immagine
        this.mazzo.mischia();
        initialize();
    }
}
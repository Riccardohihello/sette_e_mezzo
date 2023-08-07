package it.uniparthenope.programmazione3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

public class Controller {
    Mazzo mazzo = Mazzo.creaMazzo();
    boolean premuto = false;
    private int index = 0;

    @FXML
    TextArea textArea;
    @FXML
    private ImageView cardImage1;// Associa questo campo all'elenco di ImageView nel FXML
    @FXML
    private ImageView cardImage2;
    @FXML
    private ImageView cardImage3;
    @FXML
    private ImageView cardImage4;

    private List<ImageView> cardImages = new ArrayList<>();
    private Image image;
    private void addTextToArea(String text) {
        textArea.appendText(text+"\n");
    }
    private String getCartaImagePath(Carta carta) {
        String seme = carta.getSeme();
        String valore = String.valueOf((int)carta.getValore());
        char inizialeSeme = Character.toUpperCase(seme.charAt(0));
        String nomeCartella = Character.toUpperCase(inizialeSeme) + seme.substring(1);
        return "src/main/resources/it/uniparthenope/programmazione3/images/Carte/" + nomeCartella + "/" + valore + inizialeSeme + ".png";
    }
    public void initialize() {
        this.mazzo.mischia();
        index = 0;
        premuto = false;
        // Aggiungi gli ImageView alla lista
        textArea.clear();
        String text = "Mazzo resettato";
        addTextToArea(text);
        cardImages.add(cardImage1);
        cardImages.add(cardImage2);
        cardImages.add(cardImage3);
        cardImages.add(cardImage4);
        for (int i = 1; i < 4; i++) {
            cardImages.get(i).setVisible(false);
        }
        // Aggiungi altri ImageView alla lista se necessario
        // cardImages.add(cardImage5);
        // ...
        //Di default imposta la carta come girata verifico di trovarmi nella scena in cui viene usato cardImage
        //Per evitare di trovare null
        //if (cardImages.get(0) != null) {
            try {
                Image defaultImage = new Image(new FileInputStream("src/main/resources/it/uniparthenope/programmazione3/images/Carte/Retro.png"));
                cardImages.get(0).setImage(defaultImage);
            } catch (FileNotFoundException e) {
                // Gestisci l'eccezione se l'immagine predefinita non può essere trovata
                e.printStackTrace();
            }
       // }
    }

    @FXML
    private void exitButton(){
        System.exit(0);
    }


    // Metodo per cambiare scena
    @FXML
    public void gameSceneButton(ActionEvent event) throws Exception {
        ViewControll.cambiaScena("game.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }
    @FXML
    public void successiva() {
        Carta c;
        if (!premuto) {
            c = this.mazzo.primaPosizione();
            premuto= true;
        } else {
            c = this.mazzo.next();
        }
        if (index < 4) {
            try {
                String imagePath = getCartaImagePath(c);
                image = new Image(new FileInputStream(imagePath));
                cardImages.get(index).setImage(image);
                cardImages.get(index).setVisible(true);
                String text = "Hai pescato un " +  c.getNome() + " di " + c.getSeme();
                addTextToArea(text);
                System.out.println("Valore: " + c.getValore() + " Seme: " + c.getSeme() + " Iteratore: " + this.mazzo.getIteratorPosition());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            index++;
        } else {
            mischia();
        }


    }
    @FXML
    public void mischia() {
        //Mischia e resetta l'immagine
        initialize();
    }
}


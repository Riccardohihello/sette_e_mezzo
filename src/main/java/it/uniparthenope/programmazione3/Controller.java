package it.uniparthenope.programmazione3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


public class Controller implements Observer{

    Mazzo mazzo = Mazzo.creaMazzo();
    boolean premuto = false;
    private int index = 0;
    private int numPlayers = 0;
    @FXML
    ListView<String> giocatoriSx;
    @FXML
    ListView<String> giocatoriDx;
    @FXML
    private TextArea textArea;
    @FXML
    private ImageView cardImage1;// Associa questo campo all'elenco di ImageView nel FXML
    @FXML
    private ImageView cardImage2;
    @FXML
    private ImageView cardImage3;
    @FXML
    private ImageView cardImage4;
    List<String> nomiGiocatori = new ArrayList<>();

    public boolean isViewInitialized = false;
    ObservableList<String> listView = FXCollections.observableArrayList();
    private final List<ImageView> cardImages = new ArrayList<>();
    private  Image image;
    @FXML
    private void addTextToArea(String text) {
        textArea.appendText(text+"\n");
    }
    @Override
    public void update(String label, String args) {
        if (isViewInitialized) {

            if (textArea != null) {
                addTextToArea(args);
            } else {
                System.out.println("textArea is null");
            }
        }
        // Aggiorna l'interfaccia utente con le nuove informazioni dai giocatori e dal mazziere
        // Chiamate ai metodi per aggiornare l'interfaccia grafica qui
    }
    @Override
    public void partecipanti(Giocatore giocatori,int size) {
        // Creare una lista di nomi dei giocatori
        nomiGiocatori.add(giocatori.getNome());
        numPlayers++;
        if (numPlayers == size) {
            int halfSize = size / 2;
            List<String> nomiSx = new ArrayList<>(nomiGiocatori.subList(0, halfSize));
            List<String> nomiDx = new ArrayList<>(nomiGiocatori.subList(halfSize, size));

            listView.clear();
            listView.addAll(nomiGiocatori);

            riempiLista(giocatoriSx, FXCollections.observableArrayList(nomiSx));
            riempiLista(giocatoriDx, FXCollections.observableArrayList(nomiDx));

            nomiGiocatori.clear();
            numPlayers = 0; // Reimposta il contatore
        }

        // Aggiornare la lista Pietro con i nomi dei giocatori
    }

    static class Cell extends ListCell<String> {
            VBox vbox = new VBox();
            Label nameLabel = new Label("");
            Label saldoLabel = new Label("");
            ImageView img = new ImageView();

            public Cell() {
                super();
                img.setFitWidth(120);  // Imposta la larghezza desiderata dell'immagine
                img.setFitHeight(70); // Imposta l'altezza desiderata dell'immagine
                img.setPreserveRatio(true);
                vbox.setAlignment(Pos.CENTER);
                vbox.getChildren().addAll(img, nameLabel, saldoLabel);
                setGraphic(vbox);            }

            public void updateItem(String name,boolean empty) {
                super.updateItem(name,empty);
                setText(null);

                if (name != null && !empty) {
                    nameLabel.setText(name);
                    saldoLabel.setText("In attesa");
                    img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/uniparthenope/programmazione3/images/avatar.png"))));
                    setGraphic(vbox);
                } else {
                    setGraphic(null);
                }
            }

    }

    private String getCartaImagePath(Carta carta) {
        String seme = carta.getSeme();
        String valore = String.valueOf((int)carta.getValore());
        char inizialeSeme = Character.toUpperCase(seme.charAt(0));
        String nomeCartella = Character.toUpperCase(inizialeSeme) + seme.substring(1);
        return "src/main/resources/it/uniparthenope/programmazione3/images/Carte/" + nomeCartella + "/" + valore + inizialeSeme + ".png";
    }
    public void initializeCardImages() {
        cardImages.clear();
        cardImages.add(cardImage1);
        cardImages.add(cardImage2);
        cardImages.add(cardImage3);
        cardImages.add(cardImage4);
    }
    public void resetCardImages() {
        for (ImageView cardImage : cardImages) {
            cardImage.setVisible(false);
            try {
                Image defaultImage = new Image(new FileInputStream("src/main/resources/it/uniparthenope/programmazione3/images/Carte/Retro.png"));
                cardImage.setImage(defaultImage);
                cardImage.setVisible(true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void riempiLista(ListView<String> lista,ObservableList<String> players) {
        lista.setItems(players);
        lista.setCellFactory(param-> new Cell() );
        lista.setMouseTransparent(true); // Impedisce la selezione

    }
    @FXML
    public void initialize() {
        giocatoriSx.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0; -fx-border-color: transparent;");
        giocatoriDx.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0; -fx-border-color: transparent;");

        this.mazzo.mischia();
        index = 0;
        premuto = false;
        textArea.clear();
        String text = "Mazzo resettato";
        addTextToArea(text);

        initializeCardImages();
        resetCardImages();
        this.isViewInitialized = true;
        System.out.println("DIOCSAJIJIAJ");

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
        this.mazzo.mischia();
        textArea.clear();
        index = 0;
        premuto = false;
        resetCardImages();
    }




}


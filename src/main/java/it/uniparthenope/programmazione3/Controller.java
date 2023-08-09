package it.uniparthenope.programmazione3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


public class Controller implements Observer {

    Mazzo mazzo = Mazzo.creaMazzo();
    boolean premuto = false;
    private int index = 0;
    private int numPlayers = 0;
    private boolean sto = false;
    @FXML
    ListView<String> giocatoriSx;
    @FXML
    ListView<String> giocatoriDx;
    @FXML
    private TextArea textArea;
    List<String> nomiGiocatori = new ArrayList<>();
    ObservableList<String> listView = FXCollections.observableArrayList();
    private ObservableList<String> carteList = FXCollections.observableArrayList();
    @FXML
    private ListView<String> carteListView;

    @FXML
    private void addTextToArea(String text) {
        textArea.appendText(text + "\n");
    }

    @Override
    public void update(String label, String args) {

            if (textArea != null) {
                addTextToArea(args);
            } else {
                System.out.println("textArea is null");
            }
        // Aggiorna l'interfaccia utente con le nuove informazioni dai giocatori e dal mazziere
        // Chiamate ai metodi per aggiornare l'interfaccia grafica qui
    }

    @Override
    public void partecipanti(Giocatore giocatori, int size) {
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
            setGraphic(vbox);
            setStyle("-fx-background-color: transparent;");
        }

        public void updateItem(String name, boolean empty) {
            super.updateItem(name, empty);
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
        String valore = String.valueOf((int) carta.getValore());
        char inizialeSeme = Character.toUpperCase(seme.charAt(0));
        String nomeCartella = Character.toUpperCase(inizialeSeme) + seme.substring(1);
        String imagePath = "/it/uniparthenope/programmazione3/images/Carte/" + nomeCartella + "/" + valore + inizialeSeme + ".png";
        return getClass().getResource(imagePath).toExternalForm();
    }


   /* public void resetCardImages() {
        int i = 0;
        for (ImageView cardImage : cardImages) {
            if (i == 0) {
                i++;
                try {
                    Image defaultImage = new Image(new FileInputStream("src/main/resources/it/uniparthenope/programmazione3/images/Carte/Retro.png"));
                    cardImage.setImage(defaultImage);
                    cardImage.setVisible(true);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                cardImage.setVisible(false);
            }
        }
    }*/

    public void riempiLista(ListView<String> lista, ObservableList<String> args) {
        if (lista == carteListView) {
            System.out.println("Url: "+ args);
            lista.setCellFactory(param -> new CartaCell());
            lista.setItems(args);
        } else {
            lista.setItems(args);
            lista.setCellFactory(param -> new Cell());
            lista.setMouseTransparent(true); // Impedisce la selezione
        }

    }

    @FXML
    public void initialize() {
        giocatoriSx.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0; -fx-border-color: transparent;");
        giocatoriDx.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0; -fx-border-color: transparent;");
        carteListView.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0; -fx-border-color: transparent;");

        this.mazzo.mischia();
        index = 0;
        premuto = false;
        textArea.clear();
        String text = "Mazzo resettato";
        addTextToArea(text);
        System.out.println("DIOCSAJIJIAJ");


    }


    @FXML
    private void exitButton() {
        System.exit(0);
    }

    // Metodo per cambiare scena
    @FXML
    public void gameSceneButton(ActionEvent event) throws Exception {
        ViewControll.cambiaScena("game.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }
    @FXML
    public void sto() {
        sto = true;
        mischia();
    }
    @FXML
    public void successiva() {
        Carta c;
        boolean sballato = false;
        //Sballato deve arrivare da turno
        if(!sballato) {
            if (!premuto) {
                c = this.mazzo.primaPosizione();
                premuto = true;
            } else {
                c = this.mazzo.next();
            }
            String imagePath = getCartaImagePath(c);
            carteList.add(imagePath);
            riempiLista(carteListView, carteList);
            String text = "Hai pescato un " + c.getNome() + " di " + c.getSeme();
            addTextToArea(text);
            System.out.println("Valore: " + c.getValore() + " Seme: " + c.getSeme() + " Iteratore: " + this.mazzo.getIteratorPosition());
        }

    }

    public void mischia() {
        this.mazzo.mischia();
        textArea.clear();
        carteList.clear();
        riempiLista(carteListView,carteList);
        premuto = false;
    }


    public class CartaCell extends ListCell<String> {
        private ImageView img = new ImageView();


        public CartaCell() {
            super();
            img.setFitWidth(100);  // Imposta la larghezza desiderata dell'immagine
            img.setFitHeight(150); // Imposta l'altezza desiderata dell'immagine
            img.setPreserveRatio(true);
            setGraphic(img);
            setStyle("-fx-background-color: transparent;");

        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);


            if (empty) {
                img.setImage(null);
            } else {
                // Carica l'immagine della carta in base all'item (potresti dover creare una logica specifica)
                Image image = new Image(item);
                img.setImage(image);
            }
        }

    }

}


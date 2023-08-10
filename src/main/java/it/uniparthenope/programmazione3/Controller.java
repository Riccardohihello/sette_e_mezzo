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
    private boolean premuto = false;
    @FXML
    ListView<String> giocatoriSx;
    @FXML
    ListView<String> prePartita;
    @FXML
    ListView<String> giocatoriDx;
    @FXML
    private TextArea textArea;
    public ObservableList<String> nomiGiocatori = FXCollections.observableArrayList();
    ObservableList<String> listView = FXCollections.observableArrayList();
    private ObservableList<String> carteList = FXCollections.observableArrayList();
    @FXML
    private Label mainLabel;
    @FXML
    private Button avviaPartita;
    @FXML
    private ListView<String> carteListView;
    @FXML
    private TextField formPlayer;
    @FXML
    private void addTextToArea(String text) {
        textArea.appendText(text + "\n");
    }
    @Override
    //Metodo definito in observer, gestisce le notifiche da parte di turno
    public void update(String label, String args) {
            if (textArea != null) {
                addTextToArea(args);
            } else {
                System.out.println("textArea is null");
            }

    }

    @Override
    //Metodo definito in observer, riceve gli aggiornamenti che riguardano i giocatori (stato,punteggio)
    public void partecipanti(Giocatore giocatori, int size) {
        // Crea una lista di nomi dei giocatori
        nomiGiocatori.add(giocatori.getNome());
        if (nomiGiocatori.size() == size) {
            //Divido i giocatori in due liste per averli in due colonne separate, ai lati della scena
            int halfSize = size / 2;
            List<String> nomiSx = new ArrayList<>(nomiGiocatori.subList(0, halfSize));
            List<String> nomiDx = new ArrayList<>(nomiGiocatori.subList(halfSize, size));

            listView.clear();
            listView.addAll(nomiGiocatori);

            riempiLista(giocatoriSx, FXCollections.observableArrayList(nomiSx));
            riempiLista(giocatoriDx, FXCollections.observableArrayList(nomiDx));

            nomiGiocatori.clear();
        }

    }
    //REIMPLEMENTAZIONE DELLE CLASSI CELL DI LIST VIEW DA SISTEMARE!
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
    @FXML
    //Handler del pulsante avvia partita
    public void avviaPartita() {
        //Nasconde il pulsante appena cliccato, cambia la mainLabel e avvia il Turno
        avviaPartita.setVisible(false);
        mainLabel.setText("Partita Iniziata!");
        new Turno(this,nomiGiocatori);
    }
    @FXML
    //Metodo per l'inserimento dei giocatori. Necessario per inserire dinamicamente i players
    public void riempiPlayers() {
        int n = 0;
        //Primo input servirà per inserire il numero di giocatori
        if (n == 0 ) {
            n = Integer.parseInt(formPlayer.getText());
            formPlayer.setMaxWidth(300);
            mainLabel.setText("Inserisci nome giocatore");
            formPlayer.clear();
            //Secondo input inserisce n nomi nella lista
        } else if (nomiGiocatori.size() < n) {
            nomiGiocatori.add(formPlayer.getText());
            formPlayer.clear();
            riempiLista(prePartita, (ObservableList<String>) nomiGiocatori);
            if (nomiGiocatori.size() == n) {
                mainLabel.setText("Giocatori inseriti!");
                formPlayer.setVisible(false);
                avviaPartita.setVisible(true);
            }
        }
    }
    //Ricava dai campi di carta il path dell'immagine relativa a quella carta
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
    //Metodo generico per riempire una listView, controllo interno per vedere se è carta o lista giocatori
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
        //Metodi inutili, vanno messi nel css
        giocatoriSx.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0; -fx-border-color: transparent;");
        giocatoriDx.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0; -fx-border-color: transparent;");
        carteListView.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0; -fx-border-color: transparent;");
        prePartita.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0; -fx-border-color: transparent;");
       //Inizializzo variabili scena
        mainLabel.setText("Benvenuto, quanti giocatori siete?");
        this.mazzo.mischia();
        textArea.clear();
        avviaPartita.setVisible(false);
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
        mischia();
    }
    @FXML
    //Simula lo svolgimento del turno, quando un giocatore pesca una carta la si aggiunge alla view
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
    //Resetta il mazzo e pulisce la scena
    public void mischia() {
        this.mazzo.mischia();
        textArea.clear();
        carteList.clear();
        riempiLista(carteListView,carteList);
        premuto = false;
    }
    //REIMPLEMENTAZIONE DELLE CLASSI CELL DI LIST VIEW DA SISTEMARE!
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


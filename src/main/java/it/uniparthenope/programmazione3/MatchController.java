package it.uniparthenope.programmazione3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MatchController  implements Observer{
        private Turno turno;
        @FXML
        ListView<String> giocatoriSx;
        @FXML
        ListView<String> giocatoriDx;
        @FXML
        private TextArea textArea;
        ObservableList<String> listView = FXCollections.observableArrayList();
        private ObservableList<String> carteList = FXCollections.observableArrayList();
        @FXML
        private Label mainLabel;
        @FXML
        private ListView<String> carteListView;
        @FXML
        private void addTextToArea(String text) {
            textArea.appendText(text + "\n");
        }
        @Override
        //Metodo definito in observer, gestisce le notifiche da parte di turno
        public void update(String label, String args,Mano mano) {

            if (label.equals("risultati")) {
                if (textArea != null) {
                    addTextToArea(args);
                } else {
                    System.out.println("textArea is null");
                }
            } else if (label.equals("carta")) {
                carteList.add(args);
                String valoreMano = String.valueOf(mano.getValore());
                addTextToArea(valoreMano);
                riempiLista(carteListView, carteList);
            } else if (label.equals("valore")) {
                mainLabel.setText(args);
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
        //Avvia il turno e crea l'istanza, per comunicare
        public void avviaTurno(ObservableList<String> nomiGiocatori) {
            turno = new Turno(this,nomiGiocatori);
            System.out.println(turno.getComputer());
        }

        @FXML
        public void successiva() {
            String text = turno.pesca();
            mainLabel.setText(text);
        }
        public void sto() {
            turno.stai();
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
                lista.setCellFactory(param -> new it.uniparthenope.programmazione3.MatchController.CartaCell());
                lista.setItems(args);
            } else {
                lista.setItems(args);
                lista.setCellFactory(param -> new it.uniparthenope.programmazione3.Controller.Cell());
                lista.setMouseTransparent(true); // Impedisce la selezione
            }
        }
        @FXML
        public void initialize() {
            //Metodi inutili, vanno messi nel css
            giocatoriSx.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0; -fx-border-color: transparent;");
            giocatoriDx.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0; -fx-border-color: transparent;");
            carteListView.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0; -fx-border-color: transparent;");
            //Inizializzo variabili scena
            textArea.clear();
            mainLabel.setText("Partita iniziata");
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
    /*       @FXML
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

       */
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


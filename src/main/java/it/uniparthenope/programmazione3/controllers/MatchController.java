package it.uniparthenope.programmazione3.controllers;

import com.sun.javafx.menu.MenuItemBase;
import it.uniparthenope.programmazione3.classes.Giocatore;
import it.uniparthenope.programmazione3.classes.Mano;
import it.uniparthenope.programmazione3.Turno;
import it.uniparthenope.programmazione3.interfaces.Observer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class MatchController  implements Observer {
        private Turno turno;
        @FXML
        ListView<Giocatore> giocatoriSx;
        @FXML
        ListView<Giocatore> giocatoriDx;
        @FXML
        TextField quota;
        @FXML
        private TextArea textArea;
        //ObservableList<String> listView = FXCollections.observableArrayList();
        private final ObservableList<String> carteList = FXCollections.observableArrayList();
        @FXML
        private Label mainLabel;
        @FXML
        private ListView<String> carteListView;
        @FXML
        private ImageView show = new ImageView();
        @FXML
        private Button pesca;
        @FXML
        private Button stai;
        boolean terminaTurno = false;
        boolean pescato = false;

    @FXML
        private void addTextToArea(String text) {
            textArea.appendText(text + "\n");
        }
        @Override
        //Metodo definito in observer, gestisce le notifiche da parte di turno
        public void update(String label, String args, Mano mano) {

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
                riempiCarte(carteListView, carteList);
            } else if (label.equals("valore")) {
                mainLabel.setText(args);
            } else if (label.equals("shuffle")) {
                Platform.runLater(() -> mainLabel.setText(args));
                show.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/uniparthenope/programmazione3/images/shuffle.gif"))));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                show.setVisible(false);
                turno.eseguiAzione();
            }
        }
        @Override
        public void raccoltaQuote(ArrayList<Giocatore> giocatori) {
            AtomicInteger giocatoreIndex = new AtomicInteger(0);
            avviaVersamentoQuota(giocatori, giocatoreIndex);
        }
        private CompletableFuture<Void> avviaVersamentoQuota(ArrayList<Giocatore> giocatori, AtomicInteger giocatoreIndex) {
        CompletableFuture<Void> result = new CompletableFuture<>();

        if (giocatoreIndex.get() < giocatori.size()) {
            Giocatore giocatore = giocatori.get(giocatoreIndex.getAndIncrement());
            giocatore.setStato("Deve versare");
            if (giocatoreIndex.get() < giocatori.size()) {
                Giocatore prossimo = giocatori.get(giocatoreIndex.get());
                prossimo.setStato("Prossimo a versare");
            }
            if (giocatoreIndex.get()>1) {
                turno.inviaPartecipanti(turno.getGiocatori(),turno.getGiocatori().size());
            }

            System.out.println("Turno di " + giocatore.getNome());
            Platform.runLater(() -> mainLabel.setText(giocatore.getNome() + " quanto ti vuoi giocare? Credito = " + giocatore.getGettoni()));
            CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
            quota.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    String inputText = quota.getText();
                    if (inputText.isEmpty()) {
                        Platform.runLater(() -> mainLabel.setText("Scrivi qualcosa coglione!"));
                    } else {
                        int quotaInserita = Integer.parseInt(inputText);
                        if (quotaInserita < 0) {
                            Platform.runLater(() -> mainLabel.setText("Devi inserire un valore positivo"));
                        } else if (quotaInserita > giocatore.getGettoni()) {
                            Platform.runLater(() -> mainLabel.setText("Sei troppo povero!"));
                        } else {
                            System.out.println("Quota inserita: " + quotaInserita);
                            addTextToArea("Il giocatore "+giocatore.getNome()+" ha puntato "+quotaInserita);
                            giocatore.setStato("Ha versato");
                            giocatore.setGettoni(giocatore.getGettoni()-quotaInserita);
                            turno.inviaPartecipanti(turno.getGiocatori(),turno.getGiocatori().size());
                            turno.setQuota(quotaInserita);
                            completableFuture.complete(quotaInserita);
                        }
                    }
                    quota.clear();
                }
            });

            completableFuture.thenAcceptAsync(quotaInserita -> {
                turno.numeroPuntate++;
                avviaVersamentoQuota(giocatori, giocatoreIndex).thenAccept(result::complete);
            });
        } else {
            quota.setVisible(false);
            Platform.runLater(() -> mainLabel.setText("Tutti hanno inserito la propria puntata!"));
                result.complete(null);
                turno.eseguiAzione();
        }

        return result;
    }
        @Override
        public void eseguiMatch(ArrayList<Giocatore> giocatori) {
            AtomicInteger giocatoreIndex = new AtomicInteger(0);
            giocatoriTurno(giocatori, giocatoreIndex);
        }
        private CompletableFuture<Void> giocatoriTurno(ArrayList<Giocatore> giocatori, AtomicInteger giocatoreIndex) {
        CompletableFuture<Void> result = new CompletableFuture<>();

        if (giocatoreIndex.get() < giocatori.size()) {
            Giocatore giocatore = giocatori.get(giocatoreIndex.get());
            System.out.println("Turno di " + giocatore.getNome());
            giocatore.setStato("E' il tuo turno");
            if (giocatoreIndex.get()+1 < giocatori.size()) {
                Giocatore prossimo = giocatori.get(giocatoreIndex.get()+1);
                prossimo.setStato("Prossimo a giocare");
            }
            if (giocatoreIndex.get()>1) {
                turno.inviaPartecipanti(turno.getGiocatori(),turno.getGiocatori().size());
            }
            Platform.runLater(() -> mainLabel.setText(giocatore.getNome() + " peschi o stai? Valore Mano = " + giocatore.getMano().getValore()));
            CompletableFuture<Integer> completableFuture = new CompletableFuture<>();

            pesca.setOnAction(event -> {
                turno.pesca(giocatore);
                if (giocatore.getMano().getValore() > 7.5) {
                    System.out.println("Il coglione "+giocatore.getNome()+" ha sballato");
                    giocatore.setStato("Sballato");
                    carteList.clear();
                    carteListView.setItems(carteList);
                    turno.stai(giocatore);
                    giocatoreIndex.incrementAndGet();
                    completableFuture.complete(0);
                } else {
                    Platform.runLater(() -> mainLabel.setText(giocatore.getNome() + " peschi o stai? Valore Mano = " + giocatore.getMano().getValore()));
                }
            });
            stai.setOnAction(event -> {
                System.out.println("Il coglione "+giocatore.getNome()+" si è stato");
                giocatore.setStato("Ha finito, mano: "+giocatore.getMano().getValore());
                carteList.clear();
                carteListView.setItems(carteList);
                turno.stai(giocatore);
                giocatoreIndex.incrementAndGet();
                completableFuture.complete(0);
            });
            turno.inviaPartecipanti(turno.getGiocatori(),turno.getGiocatori().size());
            completableFuture.thenAcceptAsync(turnoGiocatoreFinito -> {
                giocatoriTurno(giocatori, giocatoreIndex).thenAccept(result::complete);
            });
        } else {
            Platform.runLater(() -> mainLabel.setText("Tutti hanno completato il turno, vediamo i risultati!"));
            pesca.setVisible(false);
            stai.setVisible(false);
            turno.inviaPartecipanti(turno.getGiocatori(),turno.getGiocatori().size());
            result.complete(null);
            turno.eseguiAzione();
        }

        return result;
    }

        @Override
        //Metodo definito in observer, riceve gli aggiornamenti che riguardano i giocatori (stato,punteggio)
        public void partecipanti(ArrayList<Giocatore> giocatori, int size) {
                int halfSize = size / 2;
                List<Giocatore> nomiSx = new ArrayList<>(giocatori.subList(0, halfSize));
                List<Giocatore> nomiDx = new ArrayList<>(giocatori.subList(halfSize, size));
                Platform.runLater(() -> {
                    riempiLista(giocatoriSx, FXCollections.observableArrayList(nomiSx));
                    riempiLista(giocatoriDx, FXCollections.observableArrayList(nomiDx));
                });

        }

        //REIMPLEMENTAZIONE DELLE CLASSI CELL DI LIST VIEW DA SISTEMARE!
        static class Cell extends ListCell<Giocatore> {
            VBox vbox = new VBox();
            Label nameLabel = new Label("");
            Label saldoLabel = new Label("");
            Label statoLabel = new Label("");
            ImageView img = new ImageView();

            public Cell() {
                super();
                img.setFitWidth(120);  // Imposta la larghezza desiderata dell'immagine
                img.setFitHeight(70); // Imposta l'altezza desiderata dell'immagine
                img.setPreserveRatio(true);
                vbox.setAlignment(Pos.CENTER);
                vbox.getChildren().addAll(img, nameLabel, saldoLabel,statoLabel);
                setGraphic(vbox);
                setStyle("-fx-background-color: transparent;");
            }

            public void updateItem(Giocatore giocatore, boolean empty) {
                super.updateItem(giocatore, empty);
                setText(null);

                if (giocatore != null && !empty) {
                    nameLabel.setText(giocatore.getNome());
                    saldoLabel.setText("Gettoni: "+(giocatore.getGettoni()));
                    statoLabel.setText(giocatore.getStato());
                    if ("Deve versare".equals(giocatore.getStato())) {
                        statoLabel.setStyle("-fx-text-fill: #00ff55;-fx-font-weight: bold");
                    } else {
                        statoLabel.setStyle(""); // Rimuove eventuali stili precedenti
                    }
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
        //Metodo generico per riempire una listView, controllo interno per vedere se è carta o lista giocatori
        public void riempiLista(ListView<Giocatore> lista, ObservableList<Giocatore> args) {
                lista.setItems(args);
                lista.setCellFactory(param -> new Cell());
                lista.setMouseTransparent(true); // Impedisce la selezione
        }
        public void riempiCarte(ListView<String> lista,ObservableList<String> args) {
            System.out.println("Url: "+ args);
            lista.setCellFactory(param -> new MatchController.CartaCell());
            lista.setItems(args);
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
        // Metodo per cambiare scena
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
        public static class CartaCell extends ListCell<String> {
            private final ImageView img = new ImageView();


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


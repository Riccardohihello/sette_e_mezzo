package it.uniparthenope.programmazione3.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Mano {
    private final ObservableList<Carta> ManoDicarte;
    private double valore = 0;

    public Mano() {
        this.ManoDicarte = FXCollections.observableArrayList();
    }

    public void addCarta(Carta c) {
        this.ManoDicarte.add(c);

        if (c.matta()) {
            System.out.println("Matta");
            gestisciMatta();
        } else {
            aggiornaValoreCartaNormale(c);
        }
    }

    public ObservableList<Carta> getCarte(){
        return this.ManoDicarte;
    }

    private void gestisciMatta() {
        if (this.valore % 1.0 == 0.5) {
            this.valore = 7.5;
        } else {
            this.valore = 7;
        }
    }

    private void aggiornaValoreCartaNormale(Carta c) {
            this.valore += c.getValore();
    }

    public Carta cartaPescata(){
        if(this.ManoDicarte.isEmpty())
            return null;
        else
            return this.ManoDicarte.get(ManoDicarte.size()-1);
    }

    public double getValore(){
        return this.valore;
    }
}
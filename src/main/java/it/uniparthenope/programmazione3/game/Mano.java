package it.uniparthenope.programmazione3.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class Mano {
    private final ArrayList<Carta> manoDicarte = new ArrayList<>();
    private double valore = 0;

    public void addCarta(Carta c) {
        this.manoDicarte.add(c);

        if (c.matta()) {
            System.out.println("Matta");
            gestisciMatta();
        } else {
            aggiornaValoreCartaNormale(c);
        }
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
        if(this.manoDicarte.isEmpty())
            return null;
        else
            return this.manoDicarte.get(manoDicarte.size()-1);
    }

    public double getValore(){
        return this.valore;
    }
}
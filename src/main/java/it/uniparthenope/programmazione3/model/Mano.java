package it.uniparthenope.programmazione3.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Mano implements CartaInterface {
    private final ObservableList<Carta> ManoDicarte;
    private double valore;

    public Mano() {
        this.ManoDicarte = FXCollections.observableArrayList();
    }

    public void addCarta(Carta c) {
        this.ManoDicarte.add(c);

        if (Matta(c)) {
            gestisciMatta(c);
        } else if (isCartaSpeciale(c)) {
            aggiornaValoreCartaSpeciale();
        } else {
            aggiornaValoreCartaNormale(c);
        }
    }

    public ObservableList<Carta> getCarte(){
        return this.ManoDicarte;
    }

    private boolean isCartaSpeciale(Carta c) {
        return c.getValore() > 7;
    }

    private boolean Matta(Carta c) {
        return c.getValore() == 10 && "denari".equals(c.getSeme());
    }

    private void gestisciMatta(Carta c) {
        System.out.println("Matta");

        if (this.valore % 1.0 == 0.5) {
            this.valore = 7.5;
        } else {
            this.valore = 7;
        }
    }

    private void aggiornaValoreCartaSpeciale() {
        this.valore += 0.5;
    }

    private void aggiornaValoreCartaNormale(Carta c) {
        this.valore += c.getValore();
    }

    public Carta cartaPescata(){
        return this.ManoDicarte.get(this.ManoDicarte.size()-1);
    }

    @Override
    public double getValore(){
        return this.valore;
    }
}
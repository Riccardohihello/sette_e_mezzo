package it.uniparthenope.programmazione3.game;


import java.io.Serializable;
import java.util.ArrayList;


public class Mano implements Serializable {
    // mano di carte dei giocatori
    private final ArrayList<Carta> manoDicarte = new ArrayList<>();
    private double valore = 0;


    public void addCarta(Carta c) {
        this.manoDicarte.add(c);

        if (c.matta()) {
            gestisciMatta();
        } else {
            aggiornaValoreMano(c);
        }
    }

    private void gestisciMatta() {
        //assegnazione automatica del valore della matta per il computer
        if (this.valore % 1.0 == 0.5) {
            this.valore = 7.5;
        } else {
            this.valore = 7;
        }
    }

    private void aggiornaValoreMano(Carta c) {
            this.valore += c.getValore();
    }

    public Carta primaCarta() {
        // controllo della prima carta pescata
        if (this.manoDicarte.isEmpty())
            return null;
        return this.manoDicarte.get(0);
    }

    public double getValore(){
        return this.valore;
    }
}
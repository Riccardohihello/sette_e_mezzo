package it.uniparthenope.programmazione3.game;


import java.util.ArrayList;


public class Mano {
    private final ArrayList<Carta> manoDicarte = new ArrayList<>();
    private double valore = 0;


    public void addCarta(Carta c) {
        this.manoDicarte.add(c);

        if (c.matta()) {
            gestisciMatta();
        } else {
            aggiornaValoreCarta(c);
        }
    }

    private void gestisciMatta() {
        if (this.valore % 1.0 == 0.5) {
            this.valore = 7.5;
        } else {
            this.valore = 7;
        }
    }

    private void aggiornaValoreCarta(Carta c) {
            this.valore += c.getValore();
    }

    public Carta primaCarta() {
        if (this.manoDicarte.isEmpty())
            return null;
        return this.manoDicarte.get(0);
    }

    public double getValore(){
        return this.valore;
    }
}
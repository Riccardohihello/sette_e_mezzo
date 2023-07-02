package it.uniparthenope.programmazione3;

import java.util.ArrayList;

public class Mano implements CartaInterface {
    private final ArrayList<Carta> ManoDicarte;
    private double valore;

    public Mano() {
        this.ManoDicarte = new ArrayList<>();
    }

    public void addCarta(Carta c) {
        this.ManoDicarte.add(c);
        if (c.getValore() == 10 && "denari".equals(c.getSeme()) ) {
            //Pescata matta il giocatore deve scegliere il valore (necessità input)
            System.out.println("Matta");
        } else if (c.getValore() > 7) {
            //Pescata figura, valgono mezzo punto
            this.valore += 0.5;
        } else {
            //Tutte le altre carte valgono tanti punti quanto è il loro valore numerico
            this.valore += c.getValore();
        }
    }

    public Carta cartaPescata(){
        return this.ManoDicarte.get(this.ManoDicarte.size()-1);
    }

    @Override
    public double getValore(){
        return this.valore;
    }
}
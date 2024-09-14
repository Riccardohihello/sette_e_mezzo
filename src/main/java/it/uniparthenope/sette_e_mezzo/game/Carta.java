package it.uniparthenope.sette_e_mezzo.game;

import java.io.Serializable;

public class Carta implements Serializable {
    // classe rappresentate le singole carte
    private final int valore;
    private final String seme;
    public Carta(int valore, String seme) {
        this.valore = valore;
        this.seme = seme;
    }

    public double getValore() {
     if (valore <= 7.0) {
         return valore;
     }
     else
         return 0.5;
    }

    public boolean matta() {
        return valore == 10.0 && seme.equals("Denari");
    }

    public String getImagePath() {
        return "/it/uniparthenope/sette_e_mezzo/images/Carte/" + seme +"/"+ valore +seme.charAt(0) + ".png";
    }

}
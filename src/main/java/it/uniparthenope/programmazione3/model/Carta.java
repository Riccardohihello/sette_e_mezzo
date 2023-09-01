package it.uniparthenope.programmazione3.model;

public class Carta implements CartaInterface {
    private final int valore;
    private final String seme;
    public Carta(int valore, String seme) {
        this.valore = valore;
        this.seme = seme;
    }

    @Override
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

    public String getSeme() {
        return seme;
    }

    public String getImagePath() {
        return "/it/uniparthenope/programmazione3/images/Carte/" + seme +"/"+ valore +seme.charAt(0) + ".png";
    }

}
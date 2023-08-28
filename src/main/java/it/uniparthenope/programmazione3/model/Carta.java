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
        return valore;
    }

    public String getSeme() {
        return seme;
    }

    public String getImagePath() {
        return "/it/uniparthenope/programmazione3/images/Carte/" + seme +"/"+ valore +seme.charAt(0) + ".png";
    }

}
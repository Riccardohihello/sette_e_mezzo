package it.uniparthenope.programmazione3;

public class Carta implements CartaInterface {
    private final int valore;
    private final String seme;

    public Carta(int valore, String seme) {
        this.valore = valore;
        this.seme = seme;
    }

    @Override
    public int getValore() {
        return valore;
    }

    public String getSeme() {
        return seme;
    }

}
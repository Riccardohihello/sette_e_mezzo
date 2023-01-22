package src;

public class Carta {
    private final int valore;
    private final String seme;

    public Carta(int valore, String seme) {
        this.valore = valore;
        this.seme = seme;
    }

    public int getValore() {
        return valore;
    }

    public String getSeme() {
        return seme;
    }
}
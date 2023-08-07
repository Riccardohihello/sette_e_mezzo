package it.uniparthenope.programmazione3;

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

    public String getNome(){
        switch ((int)getValore()) {
            case 1:
                return "Asso";
            case 2:
                return "Due";
            case 3:
                return "Tre";
            case 4:
                return "Quattro";
            case 5:
                return "Cinque";
            case 6:
                return "Sei";
            case 7:
                return "Sette";
            case 8:
                return "Fante";
            case 9:
                return "Cavallo";
            case 10:
                return "Re";
            default:
                return  "";
        }
    }
}
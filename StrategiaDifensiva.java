import java.util.ArrayList;

public class StrategiaDifensiva {
    public String gioca(ArrayList<Carta> carte, Mano mano) {
        int somma = sommaCarte(carte);
        if (somma >= 5) {
            return "stai";
        } else {
            return "prendi un'altra carta";
        }
    }

    private int sommaCarte(ArrayList<Carta> carte) {
        int somma = 0;
        for (Carta carta : carte) {
            somma += carta.getValore();
        }
        return somma;
    }
 }

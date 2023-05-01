import java.util.ArrayList;

public class StrategiaAggressiva {
    public String gioca(ArrayList<Carta> carte) {
        int somma = sommaCarte(carte);
        if (somma <= 5.5) {
            return "prendi un'altra carta";
        } else {
            return "stai";
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

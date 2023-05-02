import java.util.ArrayList;

public class StrategiaDifensiva implements Strategia {
    @Override
    public String strategiaDiGioco(Mano m) {
        int somma = m.getValore();
        if (somma >= 5) {
            return "stai";
        } else {
            return "prendi un'altra carta";
        }
    }
 }

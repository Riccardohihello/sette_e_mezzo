
public class StrategiaDifensiva implements Strategia {
    @Override
    public String strategiaDiGioco(Mano m) {
        if (m.getValore() >= 3) {
            return "stai";
        } else {
            return "prendi un'altra carta";
        }
    }
 }
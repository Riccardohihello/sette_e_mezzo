import java.util.ArrayList;

public class StrategiaAggressiva implements Strategia {
    @Override
    public String strategiaDiGioco(Mano m) {
        int somma = m.getValore();
        if (somma <= 5.5) {
            return "stai";
        } else {
            return "prendi un'altra carta";
        }
    }
}

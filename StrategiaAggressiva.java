
public class StrategiaAggressiva implements Strategia {
    @Override
    public String strategiaDiGioco(Mano m) {
        if (m.getValore() <= 5) {
            return "prendi un'altra carta";
        } else {
            return "stai";
        }
    }
}

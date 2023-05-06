public class StrategiaComputer implements Strategia {
    @Override
    public String strategiaDiGioco(Mano m) {
        if (m.getValore() >= 4) {
            return "stai";
        } else {
            return "prendi un'altra carta";
        }
    }
}


public class StrategiaAggressiva implements Strategia {
    @Override
    public boolean strategiaDiGioco(Mano m) {
        int somma = m.getValore();
        if (somma <= 5.5) {
            return true;
        } else {
            return false;
        }
    }
}

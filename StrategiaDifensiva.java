
public class StrategiaDifensiva implements Strategia {
    @Override
    public boolean strategiaDiGioco(Mano m) {
        int somma = m.getValore();
        if (somma >= 5) {
            return true;
        } else {
            return false;
        }
    }
 }

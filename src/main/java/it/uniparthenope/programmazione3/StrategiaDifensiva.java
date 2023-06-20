package it.uniparthenope.programmazione3;

public class StrategiaDifensiva implements Strategia {
    @Override
    public boolean strategiaDiGioco(Mano m) {
        double somma = m.getValore();
        return somma >= 4;
    }
 }

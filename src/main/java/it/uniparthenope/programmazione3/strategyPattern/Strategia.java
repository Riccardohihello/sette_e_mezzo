package it.uniparthenope.programmazione3.strategyPattern;

import it.uniparthenope.programmazione3.partita.Mano;

public interface Strategia {
    boolean strategiaDiGioco(Mano m);
}

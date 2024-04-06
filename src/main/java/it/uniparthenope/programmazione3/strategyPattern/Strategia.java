package it.uniparthenope.programmazione3.strategyPattern;

import it.uniparthenope.programmazione3.game.Giocatore;
import it.uniparthenope.programmazione3.game.Mano;

public interface Strategia {
    boolean scelta(Mano mano);
    int daiGettoni(Giocatore giocatore, int quotaDaVersare);
}

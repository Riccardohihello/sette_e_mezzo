package it.uniparthenope.programmazione3.strategyPattern;

import it.uniparthenope.programmazione3.game.Mano;

public interface Strategia {
    boolean applicaStrategia(Mano mano);
    int daiGettoni(Giocatore giocatore, int quotaDaVersare);
}

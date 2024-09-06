package it.uniparthenope.programmazione3.strategyPattern;

import it.uniparthenope.programmazione3.game.Mano;

import java.io.Serializable;

public interface Strategia extends Serializable {
    boolean applicaStrategia(Mano mano);
    int daiGettoni(Giocatore giocatore, int quotaDaVersare);
}

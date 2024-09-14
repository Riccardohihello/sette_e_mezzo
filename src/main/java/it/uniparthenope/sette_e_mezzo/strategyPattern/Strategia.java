package it.uniparthenope.sette_e_mezzo.strategyPattern;

import it.uniparthenope.sette_e_mezzo.game.Mano;

import java.io.Serializable;

public interface Strategia extends Serializable {
    Giocatore giocatore = null;

    boolean applicaStrategia(Mano mano);
    int daiGettoni(Giocatore giocatore, int quotaDaVersare);
}

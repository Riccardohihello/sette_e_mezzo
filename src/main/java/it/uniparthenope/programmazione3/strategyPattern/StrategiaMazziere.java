package it.uniparthenope.programmazione3.strategyPattern;

import it.uniparthenope.programmazione3.game.Giocatore;
import it.uniparthenope.programmazione3.game.Carta;
import it.uniparthenope.programmazione3.game.Mano;
import it.uniparthenope.programmazione3.game.MazzoIterator;

public class StrategiaMazziere implements Strategia {
    private MazzoIterator mazzo;

    public Mano getMano() {
        return mano;
    }

    public void aggiungiCarta(Carta c) {
        this.mano.addCarta(c);
    }

    @Override
    public boolean chiediCarta(Giocatore giocatore) {
        return mano.getValore()<7.5;
    }

    @Override
    public void daiGettoni(Giocatore giocatore, int puntataGiocatore) {
        giocatore.daiGettoni(puntataGiocatore);
        giocatore.riscuoti(puntataGiocatore*2);
    }

    public Carta daiCarte() {
        return MazzoIterator.getInstance().next();
    }
}

package it.uniparthenope.programmazione3.strategyPattern;
import it.uniparthenope.programmazione3.game.Giocatore;

public class StrategiaMazziere implements Strategia {

    @Override
    public boolean chiediCarta() {
        return mano.getValore()<7.5;
    }

    @Override
    public void daiGettoni(Giocatore giocatore, int puntataGiocatore) {
        giocatore.daiGettoni(puntataGiocatore);
        giocatore.riscuoti(puntataGiocatore*2);
    }

}

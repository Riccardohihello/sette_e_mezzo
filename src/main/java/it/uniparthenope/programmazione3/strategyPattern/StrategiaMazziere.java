package it.uniparthenope.programmazione3.strategyPattern;
import it.uniparthenope.programmazione3.game.Mano;

public class StrategiaMazziere implements Strategia {

    @Override
    public boolean applicaStrategia(Mano mano) {
        return mano.getValore()<7.5;
    }

    @Override
    public int daiGettoni(Giocatore giocatore, int percentualeVittoria) {
        giocatore.daiGettoni(percentualeVittoria);
        return percentualeVittoria;
    }
}

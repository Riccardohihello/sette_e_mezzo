package it.uniparthenope.sette_e_mezzo.strategyPattern;
import it.uniparthenope.sette_e_mezzo.game.Mano;

public class StrategiaMazziere implements Strategia {

    @Override
    public boolean applicaStrategia(Mano mano) {
        return mano.getValore()<7.5;
    }

    @Override
    public int daiGettoni(Giocatore mazziere, int vincitaDaPagare) {
        vincitaDaPagare = vincitaDaPagare*2;
        mazziere.daiGettoni(vincitaDaPagare);
        return vincitaDaPagare;
    }
}

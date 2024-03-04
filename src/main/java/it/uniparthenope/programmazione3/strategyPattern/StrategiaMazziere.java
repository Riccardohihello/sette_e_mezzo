package it.uniparthenope.programmazione3.strategyPattern;
import it.uniparthenope.programmazione3.game.Giocatore;
import it.uniparthenope.programmazione3.game.Mano;

public class StrategiaMazziere implements Strategia {

    @Override
    public boolean chiediCarta(Mano mano) {
        return mano.getValore()<7.5;
    }

    @Override
    public void daiGettoni(Giocatore giocatore, int puntataGiocatore) {
        giocatore.daiGettoni(puntataGiocatore);
        giocatore.riscuoti(puntataGiocatore*2);
    }

    @Override
    public String nomeStrategia() {
        return "mazziere";
    }
}

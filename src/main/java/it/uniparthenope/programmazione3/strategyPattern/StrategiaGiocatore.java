package it.uniparthenope.programmazione3.strategyPattern;
import it.uniparthenope.programmazione3.game.Giocatore;
import it.uniparthenope.programmazione3.game.Mano;

public class StrategiaGiocatore implements Strategia{
    public Mano getMano() {
        return mano;
    }

    @Override
    public boolean chiediCarta(Giocatore giocatore) {
        return mano.getValore()>7.5;
    }

    @Override
    public void daiGettoni(Giocatore giocatore,int puntataDaVersare) {
        giocatore.daiGettoni(puntataDaVersare);
    }
}

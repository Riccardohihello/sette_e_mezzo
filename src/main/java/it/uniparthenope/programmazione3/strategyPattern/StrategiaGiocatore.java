package it.uniparthenope.programmazione3.strategyPattern;
import it.uniparthenope.programmazione3.game.Giocatore;
import it.uniparthenope.programmazione3.game.Mano;

public class StrategiaGiocatore implements Strategia{

    @Override
    public boolean chiediCarta(Mano mano) {
        return mano.getValore()>7.5;
    }

    @Override
    public void daiGettoni(Giocatore giocatore, int puntataDaVersare) {
        giocatore.daiGettoni(puntataDaVersare);
    }

}

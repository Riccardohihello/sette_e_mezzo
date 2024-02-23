package it.uniparthenope.programmazione3.strategyPattern;
import it.uniparthenope.programmazione3.game.Giocatore;

public class StrategiaGiocatore implements Strategia{

    @Override
    public boolean chiediCarta() {
        return mano.getValore()>7.5;
    }

    @Override
    public void daiGettoni(Giocatore giocatore,int puntataDaVersare) {
        giocatore.daiGettoni(puntataDaVersare);
    }

    @Override
    public String nomeStrategia() {
        return "giocatore";
    }
}

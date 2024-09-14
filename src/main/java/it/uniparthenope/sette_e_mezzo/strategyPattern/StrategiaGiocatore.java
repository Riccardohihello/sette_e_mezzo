package it.uniparthenope.sette_e_mezzo.strategyPattern;
import it.uniparthenope.sette_e_mezzo.game.Mano;

public class StrategiaGiocatore implements Strategia{

    @Override
    public boolean applicaStrategia(Mano mano) {
        return mano.getValore()<7.5;
    }

    @Override
    public int daiGettoni(Giocatore giocatore, int puntataDaVersare) {
        giocatore.daiGettoni(puntataDaVersare);
        return puntataDaVersare;
    }

}

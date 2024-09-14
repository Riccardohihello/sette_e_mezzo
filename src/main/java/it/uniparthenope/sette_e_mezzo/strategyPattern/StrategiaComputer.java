package it.uniparthenope.sette_e_mezzo.strategyPattern;
import it.uniparthenope.sette_e_mezzo.game.Mano;


public class StrategiaComputer implements Strategia {

    @Override
    public boolean applicaStrategia(Mano mano) {
        boolean manovuota;
        if (mano.primaCarta() == null)
            manovuota = true;
        else
            manovuota = mano.primaCarta().getValore() < 4;
        return manovuota && mano.getValore() < 5;
    }

    @Override
    public int daiGettoni(Giocatore computer, int mediaPuntate) {
        int puntataFissa = 5;
        if(mediaPuntate<5) {
            computer.daiGettoni(puntataFissa);
            return puntataFissa;
        } else{
            computer.daiGettoni(mediaPuntate);
            return  mediaPuntate;
        }
    }
}

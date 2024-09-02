package it.uniparthenope.programmazione3.strategyPattern;
import it.uniparthenope.programmazione3.game.Mano;


public class StrategiaComputer implements Strategia {

    @Override
    public boolean applicaStrategia(Mano mano) {
        return (mano.getValore() < 5);
    }

    @Override
    public int daiGettoni(Giocatore giocatore, int mediaPuntate) {
        int puntataFissa = 5;
        if(mediaPuntate<5) {
            giocatore.daiGettoni(puntataFissa);
            return puntataFissa;
        } else{
            giocatore.daiGettoni(mediaPuntate);
            return  mediaPuntate;
        }
    }
}

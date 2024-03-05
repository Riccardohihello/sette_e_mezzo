package it.uniparthenope.programmazione3.strategyPattern;
import it.uniparthenope.programmazione3.game.Giocatore;
import it.uniparthenope.programmazione3.game.Mano;

public class StrategiaComputer implements Strategia {

    @Override
    public boolean chiediCarta(Mano mano) {
        return (mano.cartaPescata().getValore() < 4 && mano.getValore() < 5);
    }

    @Override
    public void daiGettoni(Giocatore giocatore, int mediaPuntate) {
        int puntataFissa = 5;
        if(mediaPuntate==0)
            giocatore.daiGettoni(puntataFissa);
        else{
            giocatore.daiGettoni(mediaPuntate);
        }
    }
}

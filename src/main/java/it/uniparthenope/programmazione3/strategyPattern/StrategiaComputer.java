package it.uniparthenope.programmazione3.strategyPattern;
import it.uniparthenope.programmazione3.game.Giocatore;

public class StrategiaComputer implements Strategia {


    @Override
    public boolean chiediCarta(Giocatore giocatore) {
        return (mano.cartaPescata()) < 4 && mano.getValore() < 5;
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

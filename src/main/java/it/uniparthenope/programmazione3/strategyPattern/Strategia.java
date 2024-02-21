package it.uniparthenope.programmazione3.strategyPattern;
import it.uniparthenope.programmazione3.game.Carta;
import it.uniparthenope.programmazione3.game.Giocatore;
import it.uniparthenope.programmazione3.game.Mano;

public interface Strategia {
    Mano mano = new Mano();

    default Mano getMano(){
        return mano;
    }

    default void aggiungiCarta(Carta c) {
        this.mano.addCarta(c);
    }

    boolean chiediCarta(Giocatore giocatore);
    void daiGettoni(Giocatore giocatore,int quotaDaVersare);
}

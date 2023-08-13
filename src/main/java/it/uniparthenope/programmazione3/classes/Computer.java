package it.uniparthenope.programmazione3.classes;

import it.uniparthenope.programmazione3.StrategiaComputer;
import it.uniparthenope.programmazione3.interfaces.Strategia;

public class Computer extends Giocatore{
    private final Strategia strat = new StrategiaComputer();

    Computer() {
        super("COMPUTER");
    }

    private static Computer instanza = null;
    public static Computer getInstanza()
    {
        if(instanza == null) {
            instanza = new Computer();
        }
        return instanza;
    }

    public int quotaComputer(int piatto,int numPuntate, int quotaDaVersare){
        if (numPuntate==0)
            return quotaDaVersare;
        else
            return piatto/numPuntate;
    }


}

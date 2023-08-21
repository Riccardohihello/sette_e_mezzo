package it.uniparthenope.programmazione3.model;

import it.uniparthenope.programmazione3.strategyPattern.StrategiaComputer;
import it.uniparthenope.programmazione3.strategyPattern.Strategia;

public class Computer extends Giocatore{
    private final Strategia strat = new StrategiaComputer();

    Computer() {
        super("COMPUTER");
    }

    private static Computer instance;
    public static Computer getInstance()
    {
        if(instance == null) {
            instance = new Computer();
        }
        return instance;
    }

    public int QuotaDaVersare(int piatto,int numPuntate){
        int quotaDaVersare;
        if (numPuntate==0)
            quotaDaVersare = 5;
        else
            quotaDaVersare = piatto/numPuntate;
        return quotaDaVersare;
    }


}

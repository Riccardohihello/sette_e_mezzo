package it.uniparthenope.programmazione3.strategyPattern;

import it.uniparthenope.programmazione3.model.Mano;

public class StrategiaGiocatore implements Strategia{

    @Override
    public boolean strategiaDiGioco(Mano m) {
        return m.getValore() > 7.5; //il metodo controlla se il giocatore ha sballato
    }
}

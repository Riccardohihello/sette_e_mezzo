package it.uniparthenope.programmazione3.strategyPattern;

import it.uniparthenope.programmazione3.model.Mano;

public class StrategiaComputer implements Strategia {

    @Override
    public boolean strategiaDiGioco(Mano m) {
        return m.cartaPescata().getValore() < 4 && m.getValore() < 5;
    }

}

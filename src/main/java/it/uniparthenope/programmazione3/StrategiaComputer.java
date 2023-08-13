package it.uniparthenope.programmazione3;

import it.uniparthenope.programmazione3.classes.Mano;
import it.uniparthenope.programmazione3.interfaces.Strategia;

public class StrategiaComputer implements Strategia {

    @Override
    public boolean strategiaDiGioco(Mano m) {
        return m.cartaPescata().getValore() < 4 && m.getValore() < 5;
    }

}

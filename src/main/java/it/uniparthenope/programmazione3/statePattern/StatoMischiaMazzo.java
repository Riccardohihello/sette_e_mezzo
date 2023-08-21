package it.uniparthenope.programmazione3.statePattern;

import it.uniparthenope.programmazione3.model.Mazzo;
import it.uniparthenope.programmazione3.model.Turno;

public class StatoMischiaMazzo implements StatoTurno {
@Override
public void eseguiAzione(Turno turno) {
        Mazzo.getInstance().mischia();
        System.out.println("Mazzo mischiato");
        // Passa allo stato successivo: svolgimento del match
        turno.setStatoTurno(new StatoSvolgiMatch());
        turno.notificaOsservatore("shuffle","Sto mischiando..",null);

        }
}
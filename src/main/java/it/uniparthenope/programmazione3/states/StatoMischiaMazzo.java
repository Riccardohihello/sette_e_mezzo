package it.uniparthenope.programmazione3.states;

import it.uniparthenope.programmazione3.Turno;
import it.uniparthenope.programmazione3.interfaces.StatoTurno;

public class StatoMischiaMazzo implements StatoTurno {
@Override
public void eseguiAzione(Turno turno) {
        turno.mischia();
        System.out.println("Mazzo mischiato");
        // Passa allo stato successivo: svolgimento del match
        turno.setStatoTurno(new StatoSvolgiMatch());
        turno.notificaOsservatore("shuffle","Sto mischiando..",null);

        }
}
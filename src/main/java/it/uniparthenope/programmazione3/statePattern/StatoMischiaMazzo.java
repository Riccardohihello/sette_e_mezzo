package it.uniparthenope.programmazione3.statePattern;

import it.uniparthenope.programmazione3.partita.Turno;

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
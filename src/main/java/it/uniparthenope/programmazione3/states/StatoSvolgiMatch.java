package it.uniparthenope.programmazione3.states;

import it.uniparthenope.programmazione3.Turno;
import it.uniparthenope.programmazione3.interfaces.StatoTurno;

public class StatoSvolgiMatch implements StatoTurno {
    @Override
    public void eseguiAzione(Turno turno) {
        turno.setStatoTurno(new StatoAssegnaVincite());
    }
}

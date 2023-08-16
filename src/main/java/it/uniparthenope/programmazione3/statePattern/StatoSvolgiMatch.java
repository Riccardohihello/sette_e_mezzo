package it.uniparthenope.programmazione3.statePattern;

import it.uniparthenope.programmazione3.partita.Turno;

public class StatoSvolgiMatch implements StatoTurno {
    @Override
    public void eseguiAzione(Turno turno) {
        turno.setStatoTurno(new StatoAssegnaVincite());
    }
}

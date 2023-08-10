package it.uniparthenope.programmazione3;

import java.util.Objects;

class StatoSvolgiMatch implements StatoTurno {
    @Override
    public void eseguiAzione(Turno turno) {
        turno.setStatoTurno(new StatoAssegnaVincite());
    }
}

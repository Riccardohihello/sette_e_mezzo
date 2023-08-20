package it.uniparthenope.programmazione3.statePattern;

import it.uniparthenope.programmazione3.model.Turno;
import it.uniparthenope.programmazione3.model.Giocatore;

import java.util.concurrent.atomic.AtomicInteger;

public class StatoSvolgiMatch implements StatoTurno {
    @Override
    public void eseguiAzione(Turno turno) {
        System.out.println("We");

        AtomicInteger index = new AtomicInteger();
        for (Giocatore giocatore : turno.getGiocatori()) {
            if(index.get() == 0) {
                giocatore.setStato("E' il tuo turno");
            } else if (index.get() == 1) {
                giocatore.setStato("Prossimo a giocare");
            } else {
                giocatore.setStato("In attesa");
            }
            index.getAndIncrement();
        }
        //In svolgi match ogni player deve fare svolgere il proprio turno
        turno.eseguiMatch(turno.getGiocatori());
        turno.setStatoTurno(new StatoAssegnaVincite());
    }
}

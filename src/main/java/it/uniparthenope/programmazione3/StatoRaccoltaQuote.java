package it.uniparthenope.programmazione3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

class StatoRaccoltaQuote implements StatoTurno {
    @Override
    public void raccoltaQuote(Turno turno) {
        ArrayList<Giocatore> giocatoriShuffled = new ArrayList<>(turno.giocatori);
        giocatoriShuffled.add(turno.computer);
        Collections.shuffle(giocatoriShuffled);

        for (Giocatore giocatore : giocatoriShuffled) {
            if (!Objects.equals(giocatore.getNome(), turno.mazziere.getNome()) && !Objects.equals(giocatore.getNome(), "COMPUTER"))
                turno.setQuota(giocatore.versaQuota(turno.quotaDaVersare));
            turno.numeroPuntate++;
            if (Objects.equals(giocatore.getNome(), "COMPUTER"))
                turno.setQuota(turno.computer.quotaComputer(turno.piatto, turno.numeroPuntate, turno.quotaDaVersare));
        }

        // Passa allo stato successivo: mischiatura del mazzo
        turno.setStatoTurno(new StatoMischiaMazzo());
    }

    // Implementazione degli altri metodi, se necessario (potrebbero essere vuoti)
    @Override
    public void mischiaMazzo(Turno turno) {}

    @Override
    public void svolgiMatch(Turno turno) {}

    @Override
    public void assegnaVincite(Turno turno) {}

    @Override
    public void stampaRisultati(Turno turno) {}
}

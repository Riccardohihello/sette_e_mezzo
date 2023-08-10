package it.uniparthenope.programmazione3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

class StatoRaccoltaQuote implements StatoTurno {
    @Override
    public void eseguiAzione(Turno turno) {
        raccoltaQuote(turno);
        turno.setStatoTurno(new StatoMischiaMazzo());
    }

    private void raccoltaQuote(Turno turno) {
        ArrayList<Giocatore> giocatoriShuffled = mescolaGiocatori(turno.getGiocatori());
        giocatoriShuffled.add(turno.getComputer());
        giocatoriShuffled = mescolaGiocatori(turno.getGiocatori());

        for (Giocatore giocatore : giocatoriShuffled) {
            gestisciVersamentoQuota(giocatore, turno);
            turno.numeroPuntate++;
            gestisciCalcoloQuotaComputer(giocatore, turno);
        }
    }

    private ArrayList<Giocatore> mescolaGiocatori(ArrayList<Giocatore> giocatori) {
        ArrayList<Giocatore> shuffled = new ArrayList<>(giocatori);
        Collections.shuffle(shuffled);
        return shuffled;
    }

    private void gestisciVersamentoQuota(Giocatore giocatore, Turno turno) {
        if (deveVersareQuota(giocatore, turno)) {
            turno.setQuota(giocatore.versaQuota(turno.quotaDaVersare));
        }
    }

    private boolean deveVersareQuota(Giocatore giocatore, Turno turno) {
        return !Objects.equals(giocatore.getNome(), turno.getMazziere().getNome()) &&
                !Objects.equals(giocatore.getNome(), "COMPUTER");
    }

    private void gestisciCalcoloQuotaComputer(Giocatore giocatore, Turno turno) {
        if (deveCalcolareQuotaComputer(giocatore)) {
            turno.setQuota(turno.getComputer().quotaComputer(turno.piatto, turno.numeroPuntate, turno.quotaDaVersare));
        }
    }

    private boolean deveCalcolareQuotaComputer(Giocatore giocatore) {
        return Objects.equals(giocatore.getNome(), "COMPUTER");
    }}

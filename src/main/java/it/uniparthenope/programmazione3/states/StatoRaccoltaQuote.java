package it.uniparthenope.programmazione3.states;

import it.uniparthenope.programmazione3.classes.Giocatore;
import it.uniparthenope.programmazione3.Turno;
import it.uniparthenope.programmazione3.interfaces.StatoTurno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

public class StatoRaccoltaQuote implements StatoTurno {
    @Override
    public void eseguiAzione(Turno turno) {
        raccoltaQuote(turno);
        turno.setStatoTurno(new StatoMischiaMazzo());
    }

    private void raccoltaQuote(Turno turno) {
        //ArrayList<Giocatore> giocatoriShuffled = mescolaGiocatori(turno.getGiocatori());
        //giocatoriShuffled.add(turno.getComputer());
        //giocatoriShuffled = mescolaGiocatori(turno.getGiocatori());
        int index = 0;
        for (Giocatore giocatore : turno.getGiocatori()) {
            if(index == 0) {
                giocatore.setStato("Deve versare");
            } else if (index == 1) {
                giocatore.setStato("Prossimo a versare");
            } else {
                giocatore.setStato("In attesa");
            }
            index++;
        }
        turno.notificaQuota(turno.getGiocatori());
        turno.inviaPartecipanti(turno.getGiocatori(),turno.getGiocatori().size());
        System.out.println("Richiesta quote avviata attendo che tutti inseriscano quota");
        turno.setStatoTurno(new StatoMischiaMazzo());
        /*
        for (Giocatore giocatore : giocatoriShuffled) {
            System.out.println("Turno di "+giocatore.getNome());
            gestisciVersamentoQuota(giocatore, turno);
            turno.numeroPuntate++;
            gestisciCalcoloQuotaComputer(giocatore, turno);
            }*/
    }

    private ArrayList<Giocatore> mescolaGiocatori(ArrayList<Giocatore> giocatori) {
        ArrayList<Giocatore> shuffled = new ArrayList<>(giocatori);
        Collections.shuffle(shuffled);
        return shuffled;
    }

    private void gestisciVersamentoQuota(ArrayList<Giocatore> giocatori, Turno turno) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        /*if (deveVersareQuota(giocatore, turno)) {
            //turno.setQuota(giocatore.versaQuota(turno.quotaDaVersare));
            turno.notificaQuota(giocatore);

        }*/
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

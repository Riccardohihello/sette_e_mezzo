package it.uniparthenope.programmazione3.observerPattern;

import it.uniparthenope.programmazione3.game.*;
import it.uniparthenope.programmazione3.memento.gameSettings;
import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import it.uniparthenope.programmazione3.strategyPattern.StrategiaComputer;
import it.uniparthenope.programmazione3.strategyPattern.StrategiaGiocatore;
import it.uniparthenope.programmazione3.strategyPattern.StrategiaMazziere;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Partita  {
    private final ArrayList<gameObserver> osservatori = new ArrayList<>();
    private int indiceScorrimento = 0;
    private final ArrayList<Giocatore> giocatori = new ArrayList<>();
    private final MazzoIterator mazzoIterator = new MazzoIterator();
    private Giocatore mazziere;
    private Giocatore computer;
    private final ArrayList<Integer> puntate = new ArrayList<>();

    public Partita() {
        giocatori.addAll(gameSettings.getSettings().getListaGiocatori());
        mazzoIterator.mischia();
        sceltaRuoli();
        getGiocatoreAttuale().setStato(Action.bid);
        notificaOsservatore(Action.bid);
        if(getGiocatoreAttuale().getNome().equals("Computer"))
            setQuota(getPiatto()/giocatori.size());
        if (getGiocatoreAttuale().getStrategia() instanceof StrategiaMazziere)
            setQuota(0);
    }

    public int getPiatto(){
        int valorePiatto = 0;
        for (Integer puntata : puntate) {
            valorePiatto += puntata;
        }
        return valorePiatto;
    }

    private void notificaOsservatore(Action action, String... message) {
        if (!osservatori.isEmpty())
            for (gameObserver observer : this.osservatori)
                observer.update(action,message);
    }

    public void addOsservatore(gameObserver osservatore) {
        this.osservatori.add(osservatore);
    }

    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }

    private void sceltaRuoli() {
        Random random = new Random();
        int rand;

        do
            rand = random.nextInt(giocatori.size());
        while (giocatori.get(rand).getNome().equals("Computer"));
        Giocatore mazziere = giocatori.get(rand);

        do
            Collections.shuffle(giocatori);
        while (giocatori.get(0).getNome().equals("Computer"));

        for (Giocatore g : giocatori) {
            g.setStato(Action.wait);
            g.resetMano();
            if (g.getNome().equals("Computer")) {
                g.setStrategia(new StrategiaComputer());
                this.computer = g;
            }
            else if (g.equals(mazziere)) {
                g.setStrategia(new StrategiaMazziere());
                this.mazziere = g;
            }
            else
                g.setStrategia(new StrategiaGiocatore());
        }
        giocatori.remove(mazziere);
        giocatori.add(mazziere);
    }

    public void pesca(){
        Giocatore giocatore = getGiocatoreAttuale();
        Carta cartaPescata = mazzoIterator.next();
        if (cartaPescata.matta() && !giocatore.getNome().equals("Computer"))
                notificaOsservatore(Action.matta);
        else {
            giocatore.aggiungiCarta(cartaPescata);

            if (giocatore.getStato() == Action.busted)
                notificaOsservatore(Action.busted);
        }
        notificaOsservatore(Action.pescato, cartaPescata.getImagePath());
    }

    public void stai() {
        if (getGiocatoreAttuale().getMano().getValore() == 7.5)
            notificaOsservatore(Action.setteMezzo);
        else
        if(!(getGiocatoreAttuale().getStato() == Action.busted))
            getGiocatoreAttuale().setStato(Action.results);

        scorriGiocatori();

    }

    private void scorriGiocatori(){
        indiceScorrimento += 1;
        int statoPartita = indiceScorrimento / giocatori.size();


        if(indiceScorrimento % giocatori.size() == 0) {
            if (statoPartita == 1) {
                for (Giocatore g : giocatori)
                    g.setStato(Action.wait);
                notificaOsservatore(Action.match, "valore piatto: " + getPiatto());
            }
            else if (statoPartita == 2) {
                declareWinners();
                for (Giocatore g : giocatori)
                    g.setStato(Action.results);
                notificaOsservatore(Action.results);
                if (gameSettings.getSettings().getWinners().contains(computer)) {
                    notificaOsservatore(Action.saveComputerWin);
                }
            }
        }

        if(getGiocatoreAttuale().getNome().equals("Computer"))
            if(statoPartita == 0)
                setQuota(getPiatto()/giocatori.size());
            else if(statoPartita == 1)
                    pesca();

        if(statoPartita == 1) {
            notificaOsservatore(Action.stampa, "E'il turno di " + getGiocatoreAttuale().getNome());
            getGiocatoreAttuale().setStato(Action.match);
        }
    }

    private void declareWinners() {
        mazziere.riscuoti(getPiatto());
        ArrayList<Giocatore> winners = new ArrayList<>();
        ArrayList<Giocatore> losers = new ArrayList<>();
        double valoreMazziere = this.mazziere.getMano().getValore();

        if(valoreMazziere>7.5) valoreMazziere = 0.0;

        for (Giocatore giocatore : giocatori) {
            if (giocatore.getMano().getValore() <= 7.5 && giocatore.getMano().getValore() > valoreMazziere) {
                winners.add(giocatore);
                giocatore.riscuoti(mazziere.daiGettoniStrat(puntate.get(giocatori.indexOf(giocatore))));
            } else
                losers.add(giocatore);
        }
        gameSettings.getSettings().setWinners(winners);
        gameSettings.getSettings().setLosers(losers);
        if (winners.contains(computer))
            notificaOsservatore(Action.saveComputerWin);
    }

    public Giocatore getGiocatoreAttuale() {
        return giocatori.get(indiceScorrimento % giocatori.size());
    }

    public void setQuota(int quotaVersata) {
        Giocatore attuale = getGiocatoreAttuale();
        puntate.add(attuale.daiGettoniStrat(quotaVersata));
        notificaOsservatore(Action.stampa, attuale.getNome() + " ha versato " + quotaVersata);
        attuale.setStato(Action.bidded);
        scorriGiocatori();

        if(indiceScorrimento % giocatori.size() != 0 && getGiocatoreAttuale().getStato() != Action.match)
            getGiocatoreAttuale().setStato(Action.bid);
        if (getGiocatoreAttuale().getStrategia() instanceof StrategiaMazziere)
            setQuota(0);

    }

    public void setMatta(int matta) {
        getGiocatoreAttuale().aggiungiCarta(new Carta(matta, "Matta"));
    }
}

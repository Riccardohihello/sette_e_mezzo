package it.uniparthenope.programmazione3.observerPattern;

import it.uniparthenope.programmazione3.game.*;
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
    MazzoIterator mazzoIterator = new MazzoIterator();
    private int piatto;
    private Giocatore mazziere;

    public Partita() {
        giocatori.addAll(SettingsSingleton.getInstance().getListaGiocatori());
        mazzoIterator.mischia();
        sceltaRuoli();
        getGiocatoreAttuale().setStato(Action.bid);
        notificaOsservatore(Action.bid);
        if(getGiocatoreAttuale().getNome().equals("Computer"))
            setQuota(piatto/giocatori.size());
        if (getGiocatoreAttuale().getStrategia() instanceof StrategiaMazziere)
            setQuota(0);
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
            if (g.getNome().equals("Computer"))
                g.setStrategia(new StrategiaComputer());
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
        if (giocatore.getNome().equals("Computer") && giocatore.getMano().getValore()<=7.5 && !giocatore.strat())
            stai();
    }

    public void stai() {
        Giocatore giocatore = getGiocatoreAttuale();
        if (giocatore.getMano().getValore() == 7.5)
            notificaOsservatore(Action.setteMezzo);
        else
            if(!(getGiocatoreAttuale().getStato() == Action.busted))
                getGiocatoreAttuale().setStato(Action.results);
        notificaOsservatore(Action.clear);
        scorriGiocatori();
    }

    private void scorriGiocatori(){
        indiceScorrimento += 1;
        int statoPartita = indiceScorrimento / giocatori.size();
        if(indiceScorrimento % giocatori.size() == 0) {
            if (statoPartita == 1) {
                for (Giocatore g : giocatori)
                    g.setStato(Action.wait);
                notificaOsservatore(Action.match);
            }
            else if (statoPartita == 2) {
                declareWinners();
                for (Giocatore g : giocatori)
                    g.setStato(Action.results);
                notificaOsservatore(Action.results);
            }
        }

        if(getGiocatoreAttuale().getNome().equals("Computer"))
            if(statoPartita == 0)
                setQuota(piatto/giocatori.size());
            else if(statoPartita == 1) {
                    pesca();
            }

        if(statoPartita == 1) {
            notificaOsservatore(Action.stampa, "E'il turno di " + getGiocatoreAttuale().getNome());
            getGiocatoreAttuale().setStato(Action.match);
        }
    }

    private void declareWinners() {
        ArrayList<Giocatore> winners = new ArrayList<>();
        ArrayList<Giocatore> losers = new ArrayList<>();
        double valoreMazziere = this.mazziere.getMano().getValore();

        if (valoreMazziere > 7.5) {
            giocatori.remove(this.mazziere);
            winners.addAll(giocatori);
            giocatori.add(this.mazziere);
        } else {
            for (Giocatore giocatore : giocatori) {
                if (giocatore == this.mazziere) continue; // Salta il mazziere

                double valoreGiocatore = giocatore.getMano().getValore();

                if (valoreGiocatore <= 7.5 && valoreGiocatore > valoreMazziere) {
                    winners.add(giocatore);
                    giocatore.incrementaVittorie();
                } else {
                    losers.add(giocatore);
                }
            }
        }
        if(!winners.isEmpty())
            distributeMoney(winners);
        else
            mazziere.riscuoti(piatto);
        SettingsSingleton.getInstance().setMazziere(mazziere);
        SettingsSingleton.getInstance().setWinners(winners);
        SettingsSingleton.getInstance().setLosers(losers);
    }

    private void distributeMoney(ArrayList<Giocatore> vincitori) {
        int quotaVincita = piatto / vincitori.size();

        for (Giocatore giocatore : giocatori) {
            if(giocatore.getMano().getValore() > this.mazziere.getMano().getValore() && giocatore.getMano().getValore() < 7.5) {
                this.mazziere.daiGettoniStrat(giocatore, quotaVincita);
            }
        }
    }

    public Giocatore getGiocatoreAttuale() {
        return giocatori.get(indiceScorrimento % giocatori.size());
    }

    public void setQuota(int quotaVersata) {
        Giocatore attuale = getGiocatoreAttuale();
        piatto += attuale.daiGettoniStrat(getGiocatoreAttuale(), quotaVersata);
        notificaOsservatore(Action.stampa, attuale.getNome() + " ha versato " + quotaVersata);
        attuale.setStato(Action.bidded);
        scorriGiocatori();

        if(indiceScorrimento % giocatori.size() != 0 && getGiocatoreAttuale().getStato() != Action.match) {
            getGiocatoreAttuale().setStato(Action.bid);
        }
        if (getGiocatoreAttuale().getStrategia() instanceof StrategiaMazziere)
            setQuota(0);

    }

    public void setMatta(int matta) {
        getGiocatoreAttuale().aggiungiCarta(new Carta(matta, "Matta"));
    }
}

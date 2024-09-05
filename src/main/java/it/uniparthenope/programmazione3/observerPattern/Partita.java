package it.uniparthenope.programmazione3.observerPattern;

import it.uniparthenope.programmazione3.game.*;
import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import it.uniparthenope.programmazione3.strategyPattern.StrategiaComputer;
import it.uniparthenope.programmazione3.strategyPattern.StrategiaGiocatore;
import it.uniparthenope.programmazione3.strategyPattern.StrategiaMazziere;

import java.util.ArrayList;
import java.util.Random;

public class Partita  {
    private final ArrayList<gameObserver> osservatori = new ArrayList<>();
    private int indiceScorrimento = 0;
    private final ArrayList<Giocatore> giocatori = new ArrayList<>();
    MazzoIterator mazzoIterator = new MazzoIterator();
    public int piatto;

    public Partita() {
        giocatori.addAll(SettingsSingleton.getInstance().getListaGiocatori());
        mazzoIterator.mischia();
        sceltaRuoli();
    }

    public void notificaOsservatore(Action action, String... message) {
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
        do {
            rand = random.nextInt(giocatori.size());
        } while (giocatori.get(rand).getNome().equals("Computer"));

        Giocatore mazziere = giocatori.get(rand);
        giocatori.remove(rand);

        for (Giocatore g : giocatori) {
            g.setStato(Action.wait);
            g.resetMano();
            if (g.getNome().equals("Computer")) {
                g.setStrategia(new StrategiaComputer());
            } else {
                g.setStrategia(new StrategiaGiocatore());
            }
        }
        //Mazziere aggiunto dopo cosi è sempre l'ultimo (vantaggio tattico)
        mazziere.setStrategia(new StrategiaMazziere());
        mazziere.setStato(Action.wait);
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
        Giocatore giocatore = getGiocatoreAttuale();
        if (giocatore.getMano().getValore() == 7.5)
            notificaOsservatore(Action.setteMezzo);
        else {
            if(!(getGiocatoreAttuale().getStato() == Action.busted))
                getGiocatoreAttuale().setStato(Action.results);
            notificaOsservatore(Action.clear);
        }
        scorriGiocatori();
    }

    public void scorriGiocatori(){
        indiceScorrimento += 1;
        int statoPartita = indiceScorrimento / giocatori.size();
        if(indiceScorrimento % giocatori.size() == 0) {
            if (statoPartita == 1) {
                for (Giocatore g : giocatori)
                    g.setStato(Action.wait);
                notificaOsservatore(Action.match);
            }
            else if (statoPartita == 2)
                notificaOsservatore(Action.results);
        }
        if(getGiocatoreAttuale().getNome().equals("Computer"))
            if(statoPartita == 0)
                setQuota(piatto/giocatori.size());
            else if(statoPartita == 1) {
                while (getGiocatoreAttuale().strat())
                    pesca();
                notificaOsservatore(Action.computer);
            }

        if(statoPartita == 1) {
            notificaOsservatore(Action.stampa, "E'il turno di " + getGiocatoreAttuale().getNome());
            getGiocatoreAttuale().setStato(Action.match);
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

        if(indiceScorrimento%giocatori.size() != 0 )
            getGiocatoreAttuale().setStato(Action.bid);
        if (getGiocatoreAttuale().getStrategia() instanceof StrategiaMazziere) {
            setQuota(0);
        }

    }

    public void setMatta(int matta) {
        getGiocatoreAttuale().aggiungiCarta(new Carta(matta, "Matta"));
    }
}

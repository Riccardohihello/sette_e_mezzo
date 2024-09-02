package it.uniparthenope.programmazione3.observerPattern;

import it.uniparthenope.programmazione3.game.*;
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

    // Costruttore privato per impedire istanze multiple
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
        mazziere.isMazziere = true;
        giocatori.add(mazziere);

        System.out.println("Il Mazziere è: " + mazziere.getNome() + "\n");
    }

    public void pesca(){
        Giocatore giocatore = getGiocatoreAttuale();
        giocatore.aggiungiCarta(mazzoIterator.next());
        notificaOsservatore(Action.pescato);
        if(giocatore.getStato() == Action.busted)
            notificaOsservatore(Action.busted);

    }

    private void resetStato() {
        SettingsSingleton.getInstance().updateList(giocatori);
        notificaOsservatore(Action.reset);
    }

    public void stai() {
        Giocatore giocatore = getGiocatoreAttuale();
        if (giocatore.getMano().getValore() == 7.5)
            notificaOsservatore(Action.setteMezzo);

        if(!(getGiocatoreAttuale().getStato() == Action.busted))
            getGiocatoreAttuale().setStato(Action.results);
        notificaOsservatore(Action.clear);
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
            else if(statoPartita == 1)
                pesca();

        if(statoPartita == 1) {
            notificaOsservatore(Action.stampa, "E'il turno di " + getGiocatoreAttuale().getNome());
            getGiocatoreAttuale().setStato(Action.match);
        }
    }

    public Giocatore getGiocatoreAttuale() {
        return giocatori.get(indiceScorrimento % giocatori.size());
    }

    public String getCardImage(){
        if (getGiocatoreAttuale().getMano().cartaPescata()!=null)
            return getGiocatoreAttuale().getMano().cartaPescata().getImagePath();
        else
            return null;
    }

    public void setQuota(int quotaVersata) {
        Giocatore attuale = getGiocatoreAttuale();
        piatto += attuale.daiGettoniStrat(getGiocatoreAttuale(), quotaVersata);
        notificaOsservatore(Action.stampa, attuale.getNome() + " ha versato " + quotaVersata);
        attuale.setStato(Action.bidded);
        scorriGiocatori();

        if(!(indiceScorrimento % giocatori.size() == 0) && !getGiocatoreAttuale().isMazziere)
            getGiocatoreAttuale().setStato(Action.bid);
        else if (getGiocatoreAttuale().isMazziere) {
            setQuota(0);
        }

    }
}

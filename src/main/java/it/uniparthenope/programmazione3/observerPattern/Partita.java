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
        rand = random.nextInt(giocatori.size() - 1);
        for (Giocatore g : giocatori) {
            g.setStato(Action.results);
            g.resetMano();
            if (g.getNome().equals("Computer"))
                g.setStrategia(new StrategiaComputer());
            else if (g.getNome().equals(giocatori.get(rand).getNome())) {
                g.setStrategia(new StrategiaMazziere());
                g.setStato(Action.mazziere);
            }else
                g.setStrategia(new StrategiaGiocatore());
        }
    }

    public void pesca(){
        Giocatore giocatore = getGiocatoreAttuale();
        giocatore.aggiungiCarta(mazzoIterator.next());
        notificaOsservatore(Action.pescato);
        if(giocatore.getStato() == Action.busted)
            notificaOsservatore(Action.busted);
        if(!giocatore.strat())
            stai();
    }

    private void resetStato() {
        SettingsSingleton.getInstance().updateList(giocatori);
        notificaOsservatore(Action.reset);
    }

    public void stai() {
        Giocatore giocatore = getGiocatoreAttuale();
        if (giocatore.getMano().getValore() == 7.5)
            notificaOsservatore(Action.setteMezzo);
        if (!giocatore.getNome().equals("Computer")){
            getGiocatoreAttuale().setStato(Action.results);
            notificaOsservatore(Action.clear);
        }
        scorriGiocatori();
        if (getGiocatoreAttuale().getNome().equals("Computer"))
            pesca();
    }

    public void scorriGiocatori(){
        indiceScorrimento += 1;
        int statoPartita = indiceScorrimento / giocatori.size();
        if(indiceScorrimento % giocatori.size() == 0) {
            if (statoPartita == 1)
                notificaOsservatore(Action.match);
            else if (statoPartita == 2)
                notificaOsservatore(Action.results);
        }
        if(statoPartita == 0 && getGiocatoreAttuale().getNome().equals("Computer"))
            setQuota(piatto/giocatori.size());

        if(statoPartita == 1) {
            notificaOsservatore(Action.stampa,"E'il turno di " + getGiocatoreAttuale().getNome());
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
        notificaOsservatore(Action.stampa,attuale.getNome()+" ha versato "+quotaVersata);
        attuale.setStato(Action.bidded);
        scorriGiocatori();

        if (getGiocatoreAttuale().getStato().equals(Action.mazziere))
            scorriGiocatori();
        if(!(indiceScorrimento % giocatori.size() == 0))
            getGiocatoreAttuale().setStato(Action.bid);
    }
}

package it.uniparthenope.programmazione3.observerPattern;

import it.uniparthenope.programmazione3.game.*;
import it.uniparthenope.programmazione3.strategyPattern.Strategia;
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
    public int piatto;

    // Costruttore privato per impedire istanze multiple
    public Partita() {
        giocatori.addAll(SettingsSingleton.getInstance().getListaGiocatori());
        mazzoIterator.mischia();
        aggiungiGiocatori();
        Collections.shuffle(giocatori);
        if (giocatori.get(indiceScorrimento).getStrategia().getClass().equals(StrategiaMazziere.class))
            scorriGiocatori();
    }

    public void notificaOsservatore(Action action) {
        if (!osservatori.isEmpty())
            for (gameObserver observer : this.osservatori)
                observer.update(action);
    }

    public void addOsservatore(gameObserver osservatore) {
        this.osservatori.add(osservatore);
    }

    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }

    private void aggiungiGiocatori() {
        Random random = new Random();
        int rand = random.nextInt(giocatori.size()-1);

        for (Giocatore g : giocatori) {
            if (g.getNome().equals("Computer"))
                g.setStrategia(new StrategiaComputer());
            else if (g.getNome().equals(giocatori.get(rand).getNome()))
                g.setStrategia(new StrategiaMazziere());
            else
                g.setStrategia(new StrategiaGiocatore());
        }
    }

    public void pesca(){
        Giocatore giocatore = giocatori.get(indiceScorrimento);
        giocatore.aggiungiCarta(mazzoIterator.next());
        if(giocatore.getMano().getValore()>7.5) {
            notificaOsservatore(Action.busted);

        }
    }

    public void stai() {
        if (indiceScorrimento >= giocatori.size()-1){
                notificaOsservatore(Action.results);
                for (Giocatore g : giocatori)
                    g.setStato(Action.results);
        }else{
            notificaOsservatore(Action.clear);
            scorriGiocatori();
        }
    }

    public void scorriGiocatori(){
        indiceScorrimento += 1;
        indiceScorrimento %= giocatori.size()+1;
    }

    public String getManoGiocatore(){
        return giocatori.get(indiceScorrimento).getMano().cartaPescata().getImagePath();
    }

    private Strategia strategiaGiocatore(){
        return giocatori.get(indiceScorrimento).getStrategia();
    }

    public void riempiPiatto(int quota){
        this.piatto += quota;
        this.giocatori.get(indiceScorrimento).puntataDaVersare(quota);
    }

    public void setQuota(int quotaVersata) {
        Giocatore attuale = giocatori.get(indiceScorrimento);
        if (strategiaGiocatore().getClass().equals(StrategiaMazziere.class)) {
            attuale.setStato(Action.mazziere);
        } else if (strategiaGiocatore().getClass().equals(StrategiaComputer.class)) {
            riempiPiatto( piatto / giocatori.size());
            attuale.setStato(Action.computer);
        }else{
            attuale.setStato(Action.bidded);
            riempiPiatto(quotaVersata);
        }

        scorriGiocatori();
        if(indiceScorrimento>=giocatori.size()) {
            attuale.setStato(Action.bidded);
            scorriGiocatori();
            notificaOsservatore(Action.match);
        }
    }
}

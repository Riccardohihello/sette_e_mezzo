package it.uniparthenope.programmazione3.observerPattern;

import it.uniparthenope.programmazione3.game.Carta;
import it.uniparthenope.programmazione3.game.Giocatore;
import it.uniparthenope.programmazione3.game.MazzoIterator;
import it.uniparthenope.programmazione3.game.SettingsSingleton;
import it.uniparthenope.programmazione3.strategyPattern.StrategiaComputer;
import it.uniparthenope.programmazione3.strategyPattern.StrategiaGiocatore;
import it.uniparthenope.programmazione3.strategyPattern.StrategiaMazziere;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Partita  {
    private final ArrayList<gameObserver> osservatori = new ArrayList<>();
    private int indiceScorrimento = 0;

    private final ArrayList<Giocatore> giocatori = new ArrayList<>();
    MazzoIterator mazzoIterator = MazzoIterator.getInstance();
    public int piatto;



    // Costruttore privato per impedire istanze multiple
    public Partita() {
        giocatori.addAll(SettingsSingleton.getInstance().getListaGiocatori());
        mazzoIterator.mischia();
        aggiungiGiocatori();
        Collections.shuffle(giocatori);
    }
    public void notificaOsservatore(Action action) {
        if (!osservatori.isEmpty()) {
            for (gameObserver observer : this.osservatori) {
                observer.update(action);
            }
        }
    }

    public void addOsservatore(gameObserver osservatore) {
        this.osservatori.add(osservatore);
    }

    private void aggiungiGiocatori() {
        Random random = new Random();
        int rand = random.nextInt(giocatori.size()-1);

        for (Giocatore g : giocatori) {
            if(g.getNome().equals("Computer"))
                g.setStrategia(new StrategiaComputer());
            else if(g.getNome().equals(giocatori.get(rand).getNome()))
                g.setStrategia(new StrategiaMazziere());
            else
                g.setStrategia(new StrategiaGiocatore());
        }
    }

    public void pesca(){
        Giocatore giocatore = giocatori.get(indiceScorrimento);
        giocatore.getMano().addCarta(mazzoIterator.next());
        if (giocatore.getMano().getValore()>=7.5) {
            giocatore.setStato("Finita");
            scorriGiocatori();
            giocatore.getMano().cartaPescata();
            notificaOsservatore(Action.next);
            System.out.println("ciao");
        }
        else
            giocatore.setStato("In corso");
    }

    public ObservableList<Carta> getManoGiocatore(){
        return giocatori.get(indiceScorrimento).getMano().getCarte();
    }

    public String getNomeGiocatore(){
        return giocatori.get(indiceScorrimento).getNome();
    }

    public void scorriGiocatori(){
        indiceScorrimento += 1;
        indiceScorrimento %= giocatori.size();
    }

    public int gettoni_giocatore(){
        return giocatori.get(indiceScorrimento).getGettoni();
    }


    public void riempiPiatto(int quota){
        this.piatto += quota;
    }

    public void setQuota(int quotaVersata) {
        giocatori.get(indiceScorrimento).puntataDaVersare(quotaVersata);
        scorriGiocatori();
        notificaOsservatore(Action.next);
        System.out.println(indiceScorrimento);
        if(indiceScorrimento>=giocatori.size()-1) {
            notificaOsservatore(Action.match);

        }
    }
}

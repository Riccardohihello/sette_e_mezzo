package it.uniparthenope.programmazione3.memento;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import java.time.LocalDateTime;


import java.io.Serializable;
import java.util.ArrayList;


public class SettingsSingleton implements Serializable {
    private static SettingsSingleton instance;
    private int countTurni;
    private  LocalDateTime saveDateTime = LocalDateTime.now();
    private ArrayList<Giocatore> listaGiocatori;
    private ArrayList<Giocatore> winners = new ArrayList<>();
    private ArrayList<Giocatore> losers = new ArrayList<>();
    private Giocatore mazziere;

    public void setWinners(ArrayList<Giocatore> winners) {
        this.winners = winners;
    }

    public void setLosers(ArrayList<Giocatore> losers) {
        this.losers = losers;
    }

    public void setSaveDateTime() { this.saveDateTime = LocalDateTime.now();}
    public LocalDateTime getSaveDateTime() { return saveDateTime; }

    public ArrayList<Giocatore> getListaGiocatori() { return listaGiocatori; }

    public static SettingsSingleton getInstance() {
        if (instance == null) {
            instance = new SettingsSingleton();
        }
        return instance;
    }
    public static void setInstance (SettingsSingleton settings) {
        instance = settings;
    }
    public void setListaGiocatori (ArrayList<Giocatore> listaGiocatori) { this.listaGiocatori = listaGiocatori;}


    public ArrayList<Giocatore> getLosers(){
        return losers;
    }

    public ArrayList<Giocatore> getWinners(){
        return winners;
    }

    public void setMazziere(Giocatore mazziere) {
        this.mazziere = mazziere;
    }
    public Giocatore getMazziere(){
        return mazziere;
    }

    public int getCountTurni() {
        return countTurni;
    }

    public void setCountTurni() {
        this.countTurni = countTurni+1;
    }


}
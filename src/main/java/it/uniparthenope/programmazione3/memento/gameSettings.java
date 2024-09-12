package it.uniparthenope.programmazione3.memento;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import it.uniparthenope.programmazione3.strategyPattern.StrategiaMazziere;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;


public class gameSettings implements Serializable {
    private static gameSettings instance;
    private int countTurni;
    private  LocalDateTime saveDateTime = LocalDateTime.now();
    private ArrayList<Giocatore> listaGiocatori;
    private ArrayList<Giocatore> winners = new ArrayList<>();
    private ArrayList<Giocatore> losers = new ArrayList<>();

    public void setWinners(ArrayList<Giocatore> winners) {
        this.winners = winners;
    }

    public void setLosers(ArrayList<Giocatore> losers) {
        this.losers = losers;
    }

    public void setSaveDateTime() { this.saveDateTime = LocalDateTime.now();}
    public LocalDateTime getSaveDateTime() { return saveDateTime; }

    public ArrayList<Giocatore> getListaGiocatori() { return listaGiocatori; }

    public static gameSettings getSettings() {
        if (instance == null) {
            instance = new gameSettings();
        }
        return instance;
    }

    public static void updateSettings(gameSettings settings) {
        instance = settings;
    }
    public void setListaGiocatori (ArrayList<Giocatore> listaGiocatori) { this.listaGiocatori = listaGiocatori;}


    public ArrayList<Giocatore> getLosers(){
        return losers;
    }

    public ArrayList<Giocatore> getWinners(){
        return winners;
    }

    public Giocatore getMazziere() {
        for (Giocatore giocatore : listaGiocatori)
            if (giocatore.getStrategia() instanceof StrategiaMazziere)
                return giocatore;
        return null;
    }

    public int getCountTurni() {
        return countTurni;
    }

    public void setCountTurni() {
        this.countTurni = countTurni + 1;
    }
}
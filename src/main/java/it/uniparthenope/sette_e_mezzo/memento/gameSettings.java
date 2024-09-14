package it.uniparthenope.sette_e_mezzo.memento;

import it.uniparthenope.sette_e_mezzo.strategyPattern.Giocatore;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;

// classe per gestire gli elementi della partita da salvare tramite memento
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
            if (giocatore.isMazziere())
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
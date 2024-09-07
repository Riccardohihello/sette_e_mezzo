package it.uniparthenope.programmazione3.game;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import java.time.LocalDateTime;


import java.io.Serializable;
import java.util.ArrayList;


public class SettingsSingleton implements Serializable {
    private static SettingsSingleton instance;
    private int numeroTurni;
    private int countTurni;
    private  LocalDateTime saveDateTime;
    private ArrayList<Giocatore> listaGiocatori;

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

    public void setNumeroTurni(int numeroTurni) {
        this.numeroTurni = numeroTurni;
    }

    public int getNumeroTurni() {
        return numeroTurni;
    }

    public int getCountTurni() {
        return countTurni;
    }

    public void setCountTurni() {
        this.countTurni = countTurni+1;
    }

}
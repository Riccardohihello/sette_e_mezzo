package it.uniparthenope.programmazione3.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class SettingsSingleton {
    private static SettingsSingleton instance;
    private int numeroGiocatori;
    private int numeroTurni;

    private ObservableList<Giocatore> listaGiocatori = FXCollections.observableArrayList();

    public void addGiocatore(Giocatore g) {
        listaGiocatori.add(g);
    }

    public ObservableList<Giocatore> getListaGiocatori() {
        return listaGiocatori;
    }




    public static SettingsSingleton getInstance() {
        if (instance == null) {
            instance = new SettingsSingleton();
        }
        return instance;
    }

    public void setNumeroGiocatori(int numeroGiocatori) {
        this.numeroGiocatori = numeroGiocatori;
    }
    public void setNumeroTurni(int numeroTurni) {
        this.numeroTurni = numeroTurni;
    }

    public int getNumeroGiocatori() {
        return numeroGiocatori;
    }
}

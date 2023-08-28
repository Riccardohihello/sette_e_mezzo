package it.uniparthenope.programmazione3.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Random;

public class StatistichePartita {
    private int numeroGiocatori = 2;
    private int numeroTurni = 1;
    private int indiceScorrimento = 0;

    private final ArrayList<Giocatore> giocatori = new ArrayList<>();
    private Mazziere mazziere;
    private final int indiceMazziere = 0;
    public int piatto;



    // Costruttore privato per impedire istanze multiple
    private StatistichePartita() {
        sceltaMazziere();
        // Inizializza eventuali valori predefiniti
    }

    private static StatistichePartita instance;

    public static StatistichePartita getInstance() {
        if (instance == null) {
            instance = new StatistichePartita();
        }
        return instance;
    }

    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }

    // Metodi getter e setter per i valori
    public int getNumeroGiocatori() {
        return numeroGiocatori;
    }

    public void aggiornaImpostazioni(int giocatoriSelezionati, int turniSelezionati) {
        this.numeroGiocatori = giocatoriSelezionati;
        this.numeroTurni = turniSelezionati;
    }

    public void sceltaMazziere() {
        if (getGiocatori().size() > 2) {
            Random random = new Random();
            int randomIndex = random.nextInt(getGiocatori().size()-1);
            this.mazziere = new Mazziere(getGiocatori().get(randomIndex));
        }
    }

    public ObservableList<Carta> getManoGiocatoreSelezionato(){
        return giocatori.get(indiceScorrimento).getMano().getCarte();
    }
    public int getNumeroTurni() {
        return numeroTurni;
    }

    public Mazziere getMazziere() {
        return mazziere;
    }

    public void addGiocatore(String nomeGiocatore) {
        giocatori.add(new Giocatore(nomeGiocatore));
    }

    public void addComputer(){
        this.giocatori.add(Computer.getInstance());
    }

    public ObservableList<String> getNomiGiocatori() {
        ObservableList<String> nomi = FXCollections.observableArrayList();
        for (Giocatore g : giocatori) {
            nomi.add(g.getNome());
        }
        return nomi;
    }

    public void inserisciQuota (int quota) {
        this.piatto += quota;
    }

    public Giocatore scorriGiocatori(){
        int temp = indiceScorrimento % giocatori.size();
        indiceScorrimento += 1;
        return giocatori.get(temp);
    }

    public int getPiatto() {
        return piatto;
    }

    public void riempiPiatto(int quota){
        this.piatto += quota;
    }
}

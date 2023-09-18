package it.uniparthenope.programmazione3.model;

import it.uniparthenope.programmazione3.observerPattern.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Random;

public class StatistichePartita {
    private int numeroGiocatori = 2;
    private Observer osservatore;
    private int numeroTurni = 1;
    private int indiceScorrimento = 0;

    private final ArrayList<Giocatore> giocatori = new ArrayList<>();
    Mazzo mazzo = Mazzo.getInstance();
    private Mazziere mazziere;
    private final int indiceMazziere = 0;
    public int piatto;

    public void notificaOsservatore(StatistichePartita o, String label) {
        if (osservatore != null) {
            System.out.println("Mando notifica!");
            osservatore.update(this,label);
        }
    }


    // Costruttore privato per impedire istanze multiple
    private StatistichePartita() {
        sceltaMazziere();
        mazzo.mischia();
        // Inizializza eventuali valori predefiniti
        osservatore = null;
    }

    private static StatistichePartita instance;

    public static StatistichePartita getInstance() {
        if (instance == null) {
            instance = new StatistichePartita();
        }
        return instance;
    }

    public void setOsservatore(Observer osservatore) {
        this.osservatore = osservatore;
    }
    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }

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
        notificaOsservatore(this, "addGiocatore");
    }

    public ObservableList<String> getNomiGiocatori() {
        ObservableList<String> nomi = FXCollections.observableArrayList();
        for (Giocatore g : giocatori) {
            nomi.add(g.getNome());
        }
        return nomi;
    }

    public Giocatore scorriGiocatori(){
        int temp = indiceScorrimento % giocatori.size();
        indiceScorrimento += 1;
        return giocatori.get(temp);
    }

    public int getIndiceScorrimento() {
        return indiceScorrimento;
    }

    public int getPiatto() {
        return piatto;
    }

    public void riempiPiatto(int quota){
        this.piatto += quota;
    }
}

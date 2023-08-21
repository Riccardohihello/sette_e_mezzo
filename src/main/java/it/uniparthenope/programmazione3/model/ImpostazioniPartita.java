package it.uniparthenope.programmazione3.model;

import java.util.ArrayList;
import java.util.List;

public class ImpostazioniPartita {
    private int numeroGiocatori = 2;
    private int numeroTurni = 1;
    private final ArrayList<String> nomiGiocatori = new ArrayList<>();
    private static ImpostazioniPartita instance;
    private final ArrayList<Giocatore> giocatori = new ArrayList<>();
    private Mazziere mazziere;
    private int indiceMazziere = 0;


    // Costruttore privato per impedire istanze multiple
    private ImpostazioniPartita() {
        aggiungiGiocatori(nomiGiocatori);
        sceltaMazziere();
        // Inizializza eventuali valori predefiniti
    }

    public static ImpostazioniPartita getInstance() {
        if (instance == null) {
            synchronized (ImpostazioniPartita.class) {
                if (instance == null) {
                    instance = new ImpostazioniPartita();
                }
            }
        }
        return instance;
    }
    public void aggiungiGiocatori(List<String> nomi) {
        for (String nome : nomi) {
            giocatori.add(new Giocatore(nome));
            System.out.println("Ho aggiunto "+nome);
        }
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
            this.mazziere = new Mazziere(getGiocatori().get(this.indiceMazziere % getGiocatori().size()));
            indiceMazziere++;
        }
    }
    public int getNumeroTurni() {
        return numeroTurni;
    }

    public void setNumeroGiocatori(String nomeGiocatore) {
        this.nomiGiocatori.add(nomeGiocatore);
    }
    public Mazziere getMazziere() {
        return mazziere;
    }
}

package it.uniparthenope.programmazione3;

import java.util.ArrayList;
import java.util.Random;

class Turno {
    Mazzo mazzo;
    Mazziere mazziere;
    Random random = new Random();
    private int quota; //quota totale versata dai giocatori
    ArrayList<Giocatore> giocatori;

    public Turno(int indiceMazziere, String[] nomi) {
        this.mazzo = Mazzo.creaMazzo();
        giocatori = new ArrayList<>(); //creazione dei giocatori

       aggiungiGiocatori(nomi);

        for (Giocatore giocatore : giocatori) {
            System.out.print(giocatore.getNome()+", ");
        }

        sceltaMazziere(indiceMazziere);

        raccoltaQuote(giocatori);
        stampaQuota();
    }

    public void setQuota(int quota) {
        this.quota += quota;
    }

    public void raccoltaQuote(ArrayList<Giocatore> giocatori) {
        int quotaDaVersare = random.nextInt(1,11);
        for (Giocatore giocatore : giocatori) {
            setQuota(giocatore.versaQuota(quotaDaVersare));
        }
    }

    public void stampaQuota() {
        System.out.println("Quota: " + quota);
    }

    public void sceltaMazziere(int i) {
        this.mazziere = new Mazziere(giocatori.get( i % giocatori.size()));
    }

    public void aggiungiGiocatori(String[] nomi) {
        for (String nome : nomi) {
            giocatori.add(new Giocatore(nome));
        }
    }
}

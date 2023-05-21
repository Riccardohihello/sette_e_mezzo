package it.uniparthenope.programmazione3;

import java.util.ArrayList;
import java.util.Random;

class Turno {
    Mazzo mazzo;
    Mazziere mazziere;
    Random random = new Random();
    private int quota; //quota totale versata dai giocatori
    ArrayList<Giocatore> giocatori;

    public Turno(int indiceMazziere) {
        this.mazzo = Mazzo.creaMazzo();
        String[] nomi = {"Pippo","Pluto","Paperino","Topolino"};
        giocatori = new ArrayList<>(); //creazione dei giocatori

        for(int i = 0; i < Costanti.n; i++)
            giocatori.add(new Giocatore(nomi[i]));
        for (Giocatore giocatore : giocatori) {
            System.out.print(giocatore.getNome()+", ");
        }

        sceltaMazziere(indiceMazziere);

        quota = random.nextInt(1,11);
        for (Giocatore giocatore : giocatori) {
            giocatore.versaQuota(quota);
        }
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public void sceltaMazziere(int i) {
        this.mazziere = new Mazziere(giocatori.get( i % giocatori.size()));
    }
}

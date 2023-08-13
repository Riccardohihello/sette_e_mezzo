package it.uniparthenope.programmazione3.classes;

import it.uniparthenope.programmazione3.Costanti;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestoreGiocatori {
    private final ArrayList<Giocatore> giocatori = new ArrayList<>();
    private final Computer computer = new Computer();
    private Mazziere mazziere;
    private Random rand = new Random();
    private int indiceMazziere = rand.nextInt(Costanti.n);

    public GestoreGiocatori(List<String> nomiGiocatori) {
        aggiungiGiocatori(nomiGiocatori);
        sceltaMazziere();
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

    public void sceltaMazziere() {
        if (getGiocatori().size() > 2) {
            this.mazziere = new Mazziere(getGiocatori().get(this.indiceMazziere % getGiocatori().size()));
            indiceMazziere++;
        }

    }
    public Mazziere getMazziere(){
        return mazziere;
    }
    public Computer getComputer(){
        return computer;
    }
}
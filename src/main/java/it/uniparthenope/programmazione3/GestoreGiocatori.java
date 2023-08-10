package it.uniparthenope.programmazione3;

import java.util.ArrayList;
import java.util.Random;

class GestoreGiocatori {
    private final ArrayList<Giocatore> giocatori = new ArrayList<>();
    private final Computer computer = new Computer();
    private Mazziere mazziere;
    private Random rand = new Random();
    private int indiceMazziere = rand.nextInt(Costanti.n);
    public String nomiGiocatori[] = {"Giovanni", "Marco", "Francesco", "Luca"};

    public GestoreGiocatori() {
        aggiungiGiocatori(nomiGiocatori);
        sceltaMazziere();
    }
    public void aggiungiGiocatori(String[] nomi) {
        for (String nome : nomi) {
            giocatori.add(new Giocatore(nome));
        }
    }
    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }

    public void sceltaMazziere() {
        this.mazziere = new Mazziere(getGiocatori().get(this.indiceMazziere % getGiocatori().size()));
        indiceMazziere++;
    }
    public Mazziere getMazziere(){
        return mazziere;
    }
    public Computer getComputer(){
        return computer;
    }
}
package it.uniparthenope.programmazione3;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

class Turno {
    private final Random random = new Random();
    private int quota; //quota totale versata dai giocatori
    private final ArrayList<Giocatore> giocatori;

    public Turno(int indiceMazziere, String[] nomi) {
        Mazzo mazzo = Mazzo.creaMazzo();
        giocatori = new ArrayList<>(); //creazione dei giocatori

       aggiungiGiocatori(nomi);

        for (Giocatore giocatore : giocatori) {
            System.out.print(giocatore.getNome()+", ");
        }

       Mazziere mazziere = sceltaMazziere(indiceMazziere);

        raccoltaQuote(giocatori, mazziere);
        stampaQuota();
    }

    public void setQuota(int quota) {
        this.quota += quota;
    }

    public void raccoltaQuote(ArrayList<Giocatore> giocatori, Mazziere m) {
        int quotaDaVersare = random.nextInt(1,11);
        for (Giocatore giocatore : giocatori) {
            if (!Objects.equals(giocatore.getNome(), m.getNome())) // controllo che a versare la quota siano solo i giocatori e NON il mazziere
                setQuota(giocatore.versaQuota(quotaDaVersare));    //I giocatori versano la quota
        }
    }

    public void stampaQuota() {
        System.out.println("Quota: " + quota);
    }

    public Mazziere sceltaMazziere(int i) {
        return new Mazziere(giocatori.get(i % giocatori.size()));
    }

    public void aggiungiGiocatori(String[] nomi) {
        for (String nome : nomi) {
            giocatori.add(new Giocatore(nome));
        }
    }
}

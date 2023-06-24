package it.uniparthenope.programmazione3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

class Turno {
    private final Random random = new Random();
    private int piatto; //quota totale versata dai giocatori
    private final ArrayList<Giocatore> giocatori;
    private int numeroPuntate = 0;
    Computer computer;

    public Turno(int indiceMazziere, String[] nomi) {
        Mazzo mazzo = Mazzo.creaMazzo();
        giocatori = new ArrayList<>(); //creazione dei giocatori
        this.computer = Computer.getInstanza();

       aggiungiGiocatori(nomi);

        for (Giocatore giocatore : giocatori) {
            System.out.print(giocatore.getNome()+", ");
        }

       Mazziere mazziere = sceltaMazziere(indiceMazziere);

        raccoltaQuote(giocatori, mazziere);
        stampaQuotaPiatto();
        sceltaStrategie();
        mazzo.mischia();
        sfida(mazzo);
        stampaManoGiocatori();
    }


    public void setQuota(int quotaVersata) {
        this.piatto += quotaVersata;
    }

    public void raccoltaQuote(ArrayList<Giocatore> giocatori, Mazziere m) {
        int quotaDaVersare = random.nextInt(1,11);
        ArrayList<Giocatore> giocatoriShuffled = new ArrayList<>(giocatori);
        giocatoriShuffled.add(computer);
        Collections.shuffle(giocatoriShuffled);

        for (Giocatore giocatore : giocatoriShuffled) {
            if (!Objects.equals(giocatore.getNome(), m.getNome()) && !Objects.equals(giocatore.getNome(), "COMPUTER")) // controllo che a versare la piatto siano solo i giocatori e NON il mazziere o il computer
                setQuota(giocatore.versaQuota(quotaDaVersare));    //I giocatori versano nel piatto
            numeroPuntate++;
            if (Objects.equals(giocatore.getNome(), "COMPUTER"))
               setQuota(computer.quotaComputer(piatto, numeroPuntate,quotaDaVersare));
        }
    }

    public void stampaQuotaPiatto() {
        System.out.println("Quota totale piatto: " + piatto);
    }

    public void stampaManoGiocatori() {
        for (Giocatore g: giocatori)
            System.out.println( g.getNome()+ " = " + g.getMano().getValore());
    }

    public Mazziere sceltaMazziere(int i) {
        return new Mazziere(giocatori.get(i % giocatori.size()));
    }

    public void aggiungiGiocatori(String[] nomi) {
        for (String nome : nomi) {
            giocatori.add(new Giocatore(nome));
        }
    }
    public void sceltaStrategie(){
        for(Giocatore g: giocatori){
            g.setStrat(new StrategiaAggressiva());
        }
    }

    public void sfida(Mazzo m){
        int i;
        for(i=0; i<giocatori.size();i++) {
            while (giocatori.get(i).strategiaScelta()) {
                giocatori.get(i).addCarta(m.next());
                System.out.println(giocatori.get(i).getNome() + " ha aggiunto una carta");
            }
            if (giocatori.get(i).getMano().getValore() > 7.5) {
                System.out.println(giocatori.get(i).getNome() +" ha sballato");
            }
        }
    }
}

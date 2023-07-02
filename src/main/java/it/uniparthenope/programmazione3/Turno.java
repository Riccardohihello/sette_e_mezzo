package it.uniparthenope.programmazione3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

class Turno {
    private final ArrayList<Giocatore> giocatori;
    Mazziere mazziere;
    Mazzo mazzo;
    Computer computer;
    RegistroVincite vincitori;
    private final Random random = new Random();
    private int piatto; //quota totale versata dai giocatori
    int quotaDaVersare = random.nextInt(1,11);
    private int numeroPuntate = 0;

    public Turno(int indiceMazziere, String[] nomi) {
        mazzo = Mazzo.creaMazzo();
        giocatori = new ArrayList<>(); //creazione dei giocatori
        computer = Computer.getInstanza();
        vincitori= RegistroVincite.getInstance();

       aggiungiGiocatori(nomi);

        for (Giocatore giocatore : giocatori) {
            System.out.print(giocatore.getNome()+", ");
        }

       this.mazziere = sceltaMazziere(indiceMazziere);

        raccoltaQuote();
        for (Giocatore g: giocatori){
            System.out.println(g.getNome()+ " ha inizialmente " + g.gettoni);
        }
        sceltaStrategie();
        mazzo.mischia();
        match();
        mazziere.riscuoti(piatto);
        registraVincitori();
        vincitori.pagaVincite(mazziere,quotaDaVersare);
        vincitori.stampaVincitori();
        vincitori.reset();

    }


    public void setQuota(int quotaVersata) {
        this.piatto += quotaVersata;
    }

    public void raccoltaQuote() {
        ArrayList<Giocatore> giocatoriShuffled = new ArrayList<>(giocatori);
        giocatoriShuffled.add(computer);
        Collections.shuffle(giocatoriShuffled);

        for (Giocatore giocatore : giocatoriShuffled) {
            if (!Objects.equals(giocatore.getNome(), mazziere.getNome()) && !Objects.equals(giocatore.getNome(), "COMPUTER")) // controllo che a versare la piatto siano solo i giocatori e NON il mazziere o il computer
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
        mazziere.setStrat(new StrategiaDifensiva());
    }

    public void match(){
        double manoM = mazziere.sfida(mazzo);
        for (Giocatore g: giocatori) {
            if (!Objects.equals(g.getNome(), mazziere.getNome())) {
                if(g.sfida(mazzo)>manoM){
                    System.out.println(g.getNome() + " ha battuto il mazziere");
                }
            }
            else {
                System.out.println("Sono il mazziere ");
            }
        }
    }
    public void registraVincitori(){
        for (Giocatore g: giocatori){
            if(g.getMano().getValore() > mazziere.getMano().getValore() && !Objects.equals(g.getNome(), mazziere.getNome()))
                vincitori.registraVincitori(g);
        }
    }
}

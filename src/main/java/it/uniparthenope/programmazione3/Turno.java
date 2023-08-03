package it.uniparthenope.programmazione3;

import java.util.ArrayList;
import java.util.Random;

class Turno {
    public Computer computer = Computer.getInstanza();
    public RegistroVincite vincitori = RegistroVincite.getInstance();
    public ArrayList<Giocatore> giocatori = new ArrayList<>();
    private final Random random = new Random();
    public int piatto;
    public Mazzo mazzo = Mazzo.creaMazzo();
    public Mazziere mazziere;
    int numeroPuntate = 0;

    int quotaDaVersare = random.nextInt(1,11);
    private StatoTurno statoCorrente;

    public void sceltaStrategie(){
        for(Giocatore g: giocatori){
            g.setStrat(new StrategiaAggressiva());
        }
    }

    public Mazziere sceltaMazziere(int indiceMazziere) {
        return new Mazziere(giocatori.get(indiceMazziere % giocatori.size()));
    }

    public void creaMazziere(int indiceMazziere) {
       mazziere = sceltaMazziere(indiceMazziere);
    }

    public void setStatoTurno(StatoTurno stato) {
        this.statoCorrente = stato;
    }

    public void raccoltaQuote() {
        statoCorrente.raccoltaQuote(this);
    }
    public void mischiaMazzo() {
        statoCorrente.mischiaMazzo(this);
    }
    public void svolgiMatch() {
        statoCorrente.svolgiMatch(this);
    }
    public void assegnaVincite() {
        statoCorrente.assegnaVincite(this);
    }
    public void stampaRisultati() {
        statoCorrente.stampaRisultati(this);
    }

    public void setQuota(int i) {
        this.quotaDaVersare = i;
    }

    public void stampaMazziere() {
        System.out.println("il mazziere è:  " + mazziere.getNome());
    }

    public void aggiungiGiocatori(String[] nomi) {
        for (String nome : nomi) {
            giocatori.add(new Giocatore(nome));
        }
    }
}



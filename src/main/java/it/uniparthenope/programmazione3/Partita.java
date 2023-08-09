package it.uniparthenope.programmazione3;

import java.util.Random;
import java.util.Scanner;

public class Partita {
    private Random rand = new Random();
    private Scanner sc = new Scanner(System.in);
    private Observer osservatore;
    public void notificaOsservatore(String label, String args) {
        if (osservatore != null) {
            osservatore.update(label,args);
        }
    }
    public void inviaPartecipanti(Giocatore giocatori,int size) {
        if (osservatore != null) {
            osservatore.partecipanti(giocatori,size);
        }
    }
    int indiceMazziere = rand.nextInt(Costanti.n);

    public Partita(Observer osservatore) {
        this.osservatore = osservatore;
        // Inizializzazione del turno
        Turno turno = new Turno(this);


        // Imposta lo stato corrente iniziale (raccolta delle quote)
        turno.setStatoTurno(new StatoRaccoltaQuote());

        // Esegui il turno
        String[] nomiGiocatori = {"Pippo", "Pluto", "Paperino", "Topolino","Riccardo"};
        turno.aggiungiGiocatori(nomiGiocatori);
        turno.sceltaStrategie();
        turno.creaMazziere(indiceMazziere);
        turno.raccoltaQuote();
        turno.mischiaMazzo();
        turno.svolgiMatch();
        turno.assegnaVincite();
        turno.stampaRisultati();

/*        //indice del mazziere generato casualmente
        int indiceMazziere = rand.nextInt(Costanti.n);

        System.out.println("Quanti turni vuoi simulare?");

        int turni = sc.nextInt();

        creaTurni(turni,indiceMazziere, nomi);*/

    }

/*    public void creaTurni(int turni,int indiceMazziere, String[] nomi) {
        for (int i = 1; i <= turni; i++) {
            System.out.println("\nturno numero: "+i);
            new Turno(indiceMazziere, nomi);
            indiceMazziere += 1;
        }
    }*/


}

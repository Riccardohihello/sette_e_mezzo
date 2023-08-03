package it.uniparthenope.programmazione3;

import java.util.Random;
import java.util.Scanner;

public class Partita {
    private Random rand = new Random();
    private Scanner sc = new Scanner(System.in);
    int indiceMazziere = rand.nextInt(Costanti.n);

    public Partita() {
        // Inizializzazione del turno
        Turno turno = new Turno();

        // Imposta lo stato corrente iniziale (raccolta delle quote)
        turno.setStatoTurno(new StatoRaccoltaQuote());

        // Esegui il turno
        String[] nomiGiocatori = {"Pippo", "Pluto", "Paperino", "Topolino"};
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

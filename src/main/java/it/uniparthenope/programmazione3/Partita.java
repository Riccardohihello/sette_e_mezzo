package it.uniparthenope.programmazione3;

import java.util.Random;
import java.util.Scanner;

public class Partita {
    Random rand = new Random();
    Scanner sc = new Scanner(System.in);

    private Partita() {
        //indice del mazziere generato casualmente
        int indiceMazziere = rand.nextInt(Costanti.n);
        System.out.println("Quanti turni vuoi simulare?");
        int turni = sc.nextInt();
        for (int i = 1; i <= turni; i++) {
            System.out.println("\nturno numero: "+i);
            new Turno(indiceMazziere);
            indiceMazziere += 1;
        }
    }
    private static Partita instanzaPartita = null;

    public static Partita start() {
        if (instanzaPartita == null) {
            instanzaPartita = new Partita();
        }
        return instanzaPartita;
    }


}

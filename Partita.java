import java.util.Scanner;
import java.util.Random;

public class Partita {
    Random rand = new Random();
    Scanner sc = new Scanner(System.in);
    private int indiceMazziere = rand.nextInt(Costanti.n); //indice del mazziere generato casualmente

    private Partita() {
        System.out.println("Quanti turni vuoi simulare?");
        int turni = sc.nextInt();
        for (int i = 1; i <= turni; i++) {
            System.out.println("\nturno numero: "+i);
            new Turno(this.indiceMazziere);
            this.indiceMazziere += 1;
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

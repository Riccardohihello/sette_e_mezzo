import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Partita {
    Random rand = new Random();
    Scanner sc = new Scanner(System.in);
    private int indiceMazziere = rand.nextInt(Costanti.n); //indice del mazziere generato casualmente
    private Mazzo mazzo = Mazzo.creaMazzo();

    public Partita() {
        System.out.println("Quanti turni vuoi simulare?");
        int turni = sc.nextInt();
        for (int i = 1; i <= turni; i++) {
            System.out.println("\nturno numero: "+i);
            new Turno(mazzo,this.indiceMazziere);
        }
    }


}

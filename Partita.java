import java.util.ArrayList;
import java.util.Random;

class Partita {

    Random rand = new Random();

    public Partita() {
        int indiceMazziere = rand.nextInt(Costanti.n);   //indice del mazziere generato casualmente
        Mazzo mazzo = Mazzo.creaMazzo();
        String[] nomi = {"Pippo","Pluto","Paperino","Topolino"};
        ArrayList<Giocatore> giocatori = new ArrayList<>(); //sistemare la creazione dei giocatori

        for(int i = 0; i < Costanti.n; i++)
            giocatori.add(new Giocatore(nomi[i]));
        for (Giocatore giocatore : giocatori) {
            System.out.println(giocatore.getNome());
        }

       mazzo.stampa();
        new Mazziere(giocatori.get(indiceMazziere));
    }

}

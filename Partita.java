
import java.util.ArrayList;

class Partita {
    private ArrayList<Giocatore> giocatori;
    private Mazzo mazzo ;
    private Mazziere mazziere;
    private int indiceMazziere = 0;

    public Partita() {
        ArrayList<Giocatore> giocatori = new ArrayList<>(); //sistemare la creazione dei giocatori
        String[] nomi = {"Pippo","Pluto","Paperino","Topolino"};
        for(int i = 0; i < 4; i++)
            giocatori.add(new Giocatore(nomi[i]));
        for (Giocatore giocatore : giocatori) {
            System.out.println(giocatore.getNome());
        }
    }

    public void setMazziere(ArrayList<Giocatore> giocatori,int indiceMazziere,Mazzo mazzo) {
        this.mazziere = new Mazziere(giocatori.get(indiceMazziere).getNome(),mazzo);
        this.indiceMazziere++;
        System.out.println("Mazziere: " + indiceMazziere);
    }
}

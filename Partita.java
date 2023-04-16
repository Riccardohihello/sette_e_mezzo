
import java.util.ArrayList;

class Partita {
    private ArrayList<Giocatore> giocatori;
    private Mazzo mazzo ;
    private Mazziere mazziere;
    private int indiceMazziere = 0;

    public Partita() {
        ArrayList<Giocatore> giocatori = new ArrayList<>();
        giocatori.add(new Giocatore("Pippo"));
    }
    public void setMazziere(ArrayList<Giocatore> giocatori,int indiceMazziere,Mazzo mazzo) {
        this.mazziere = new Mazziere(giocatori.get(indiceMazziere).getNome(),mazzo);
        this.indiceMazziere++;
    }
}

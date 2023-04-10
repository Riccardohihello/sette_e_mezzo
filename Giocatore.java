
import java.util.ArrayList;

public class Giocatore {
    private final String nome;
    private double punteggio;
    private int gettoni;
    private ArrayList<Carta> carte_giocatore;

    public Giocatore(String nome) {
        this.nome = nome;
        this.punteggio = 0;
        this.gettoni = gettoni;
        this.carte_giocatore = new ArrayList<Carta>();
    }

    public String getNome() {

        return nome;
    }

    public double getPunteggio() {

        return punteggio;
    }

    public int getGettoni() {

        return gettoni;
    }

    public void setGettoni(int gettoni) {
        this.gettoni = gettoni;
    }
}
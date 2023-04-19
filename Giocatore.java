

public class Giocatore {
    private final String nome;
    protected int punteggio;
    protected int gettoni;

    public Giocatore(String nome) {
        this.nome = nome;
        this.punteggio = 0;
        this.gettoni = Costanti.m;
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
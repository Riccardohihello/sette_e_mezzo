public class Giocatore {
    private final String nome;
    protected int gettoni;
    private Mano mano;

    private Strategia strat;

    public void stileDiGioco() {
        if(this.getGettoni() >= Costanti.m) {
            strat.strategiaDiGioco(this.mano);
        }

    }

    public Giocatore(String nome) {
        this.nome = nome;
        this.gettoni = Costanti.m;
    }

    public String getNome() {
        return nome;
    }

    public void setGettoni(int gettoni) {
        this.gettoni = gettoni;
    }

    public int getGettoni() {
        return gettoni;
    }
}
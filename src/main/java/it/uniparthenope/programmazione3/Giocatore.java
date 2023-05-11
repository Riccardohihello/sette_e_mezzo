package it.uniparthenope.programmazione3;

public class Giocatore extends Persona {
    protected int gettoni;
    private Mano mano;

    private Strategia strat;

    public void stileDiGioco() {
            strat.strategiaDiGioco(mano);

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
package it.uniparthenope.programmazione3;

public class Giocatore extends GiocatoreAbstract {
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

}
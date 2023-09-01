package it.uniparthenope.programmazione3.model;

import it.uniparthenope.programmazione3.strategyPattern.Strategia;

public abstract class GiocatoreAbstract {
    protected String nome;
    protected String stato;
    protected Strategia strat;
    protected Mano mano = new Mano();
    protected int gettoni;
    public  boolean turno;
    {
        turno = false;
    }

    public String getNome() {
        return nome;
    }

    public Mano getMano() {
        return mano;
    }
    public String getStato() {return stato;}

    public void aggiungiCarta(Carta c) {
        this.mano.addCarta(c);
    }

    public Boolean getStrategia() {
        return strat.strategiaDiGioco(this.mano);
    }

    public void setGettoni(int gettoni) {
        this.gettoni = gettoni;
    }
    public void setStato(String stato) {this.stato = stato;}

    public int getGettoni() {
        return gettoni;
    }

    public void riscuoti(int importo) {
        gettoni += importo;
    }

}


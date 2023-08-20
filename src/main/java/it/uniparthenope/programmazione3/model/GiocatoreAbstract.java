package it.uniparthenope.programmazione3.model;

public abstract class GiocatoreAbstract {
    protected String nome;
    protected String stato;
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

    public void addCarta(Carta c) {
        this.mano.addCarta(c);
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


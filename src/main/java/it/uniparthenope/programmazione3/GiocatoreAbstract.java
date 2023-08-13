package it.uniparthenope.programmazione3;

import it.uniparthenope.programmazione3.classes.Carta;
import it.uniparthenope.programmazione3.classes.Mano;

public abstract class GiocatoreAbstract {
    protected String nome;
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

    public void addCarta(Carta c) {
        this.mano.addCarta(c);
    }

    public void setGettoni(int gettoni) {
        this.gettoni = gettoni;
    }

    public int getGettoni() {
        return gettoni;
    }

    public int versaQuota(int quotaDaVersare){
        this.gettoni -= quotaDaVersare;
        System.out.println(this.nome + " ha versato " + quotaDaVersare + " gettoni");
        return quotaDaVersare;
    }

    public void riscuoti(int importo) {
        gettoni += importo;
    }

}


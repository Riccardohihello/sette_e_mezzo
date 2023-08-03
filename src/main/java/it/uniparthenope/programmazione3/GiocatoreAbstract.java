package it.uniparthenope.programmazione3;

public abstract class GiocatoreAbstract {
    protected String nome;
    protected Mano mano = new Mano();
    protected int gettoni;
    protected Strategia strategia;

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

    public void setStrat(Strategia strat) {
        this.strategia = strat;
    }

    public boolean strategiaScelta() {
        return strategia.strategiaDiGioco(mano);
    }

    public Strategia getStrategia() {
        return strategia;
    }

    public double sfida(Mazzo m){
        while (this.strategiaScelta()) {
            this.addCarta(m.next());
        }
            return mano.getValore();
    }
}


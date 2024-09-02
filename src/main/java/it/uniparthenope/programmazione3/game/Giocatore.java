package it.uniparthenope.programmazione3.game;
import it.uniparthenope.programmazione3.observerPattern.Action;
import it.uniparthenope.programmazione3.strategyPattern.Strategia;

public class Giocatore {
    public Giocatore(String nome){
        this.nome=nome;
        this.gettoni=100;

    }
    protected String nome;
    protected Action stato;
    protected Strategia strategia;
    protected int gettoni;
    public boolean isMazziere = false;
    Mano mano = new Mano();

    public Mano getMano(){
        return mano;
    }

    public void resetMano() {
        mano = new Mano();
    }
    public void aggiungiCarta(Carta c) {
        this.mano.addCarta(c);
        if (mano.getValore()>0.1)
            stato = Action.busted;
    }

    public Boolean strat(){
        return strategia.applicaStrategia(this.mano);
    }

    public int daiGettoniStrat(Giocatore g, int gettoni){
        return this.strategia.daiGettoni(g, gettoni);
    }

    public String getNome() {
        return nome;
    }

    public Action getStato() {return stato;}

    public void setStrategia(Strategia s) {
        this.strategia = s;
    }

    public void setStato(Action stato) {
        this.stato = stato;
    }

    public int getGettoni() {
        return gettoni;
    }

    public void daiGettoni(int puntata){
        this.gettoni-=puntata;
    }

    public void riscuoti(int importo) {
        gettoni += importo;
    }

}


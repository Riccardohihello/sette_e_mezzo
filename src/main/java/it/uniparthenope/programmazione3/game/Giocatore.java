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
    Mano mano = new Mano();

    public Mano getMano(){
        return mano;
    }

    public void aggiungiCarta(Carta c) {
        this.mano.addCarta(c);
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

    public void puntataDaVersare(int quotaVersata) {
        this.strategia.daiGettoni(this,quotaVersata);
    }

    public Strategia getStrategia() {
        return strategia;
    }
}


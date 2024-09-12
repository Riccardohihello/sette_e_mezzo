package it.uniparthenope.programmazione3.strategyPattern;
import it.uniparthenope.programmazione3.game.Carta;
import it.uniparthenope.programmazione3.game.Mano;
import it.uniparthenope.programmazione3.observerPattern.Action;

import java.io.Serializable;

public class Giocatore implements Serializable {
    public Giocatore(String nome){
        this.nome=nome;
        this.gettoni=100;

    }
    private String nome;
    private Action stato;
    private Strategia strategia;
    private int gettoni;
    private int vittorie = 0;
    Mano mano = new Mano();

    public Mano getMano(){
        return mano;
    }

    public void resetMano() {
        mano = new Mano();
    }
    public void aggiungiCarta(Carta c) {
        this.mano.addCarta(c);
        if (mano.getValore()>7.5)
            stato = Action.busted;
    }

    public Boolean strat(){
        return strategia.applicaStrategia(this.mano);
    }

    public int daiGettoniStrat(int gettoni){
        return this.strategia.daiGettoni(this, gettoni);
    }

    public String getNome() {
        return nome;
    }
    public void setNome( String nome) {this.nome = nome;}

    public Action getStato() {return stato;}
    public void setStato(Action stato) {
        this.stato = stato;
    }

    public Strategia getStrategia() {return strategia;}
    public void setStrategia(Strategia s) {
        this.strategia = s;
    }

    public int getGettoni() {
        return gettoni;
    }
    public void setGettoni(int gettoni) {this.gettoni = gettoni;}

    public void daiGettoni(int puntata){ this.gettoni-=puntata;}
    public void riscuoti(int importo) { gettoni += importo; }

    public void incrementaVittorie() { this.vittorie++; }
    public int getVittorie() { return vittorie; }
}


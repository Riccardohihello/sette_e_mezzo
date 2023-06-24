package it.uniparthenope.programmazione3;

public class Giocatore extends GiocatoreAbstract {

    public boolean strategiaScelta() {
            return this.strategia.strategiaDiGioco(mano);

    }
    public void setStrat(Strategia strat) {
        this.strategia = strat;
    }

    public Giocatore(String nome) {
        this.nome = nome;
        this.gettoni = Costanti.m;
        this.mano = new Mano();
    }

}
package it.uniparthenope.programmazione3.model;


public class Mazziere extends GiocatoreAbstract {

    public Mazziere(Giocatore g) {
        this.nome = g.getNome();
        this.mano = new Mano();
        this.gettoni = g.getGettoni();
    }

    public void pagaVincite(int importo, Giocatore g)
    {
        this.gettoni -= importo;
        g.riscuoti(importo * 2);
    }
    public Carta daiCarte() {
        return Mazzo.getInstance().next();
    }
}